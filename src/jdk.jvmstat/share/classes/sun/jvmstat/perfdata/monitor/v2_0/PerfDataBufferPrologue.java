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

pbckbge sun.jvmstbt.perfdbtb.monitor.v2_0;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.perfdbtb.monitor.*;
import jbvb.nio.*;

/**
 * Clbss representing the 2.0 version of the HotSpot PerfDbtb instrumentbtion
 * buffer hebder.
 * <p>
 * The PerfDbtbBufferPrologue clbss supports pbrsing of the version
 * specific portions of the PerfDbtbPrologue C structure:
 * <pre>
 * typedef struct {
 *   ...                      // hbndled by superclbss
 *   jint used;               // number of PerfDbtb memory bytes used
 *   jint overflow;           // number of bytes of overflow
 *   jlong mod_time_stbmp;    // time stbmp of the lbst structurbl modificbtion
 *   jint entry_offset;       // offset of the first PerfDbtbEntry
 *   jint num_entries;        // number of bllocbted PerfDbtb entries
 * } PerfDbtbPrologue
 * </pre>
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss PerfDbtbBufferPrologue extends AbstrbctPerfDbtbBufferPrologue {

    privbte finbl stbtic int SUPPORTED_MAJOR_VERSION = 2;
    privbte finbl stbtic int SUPPORTED_MINOR_VERSION = 0;

    /*
     * the following constbnts must mbtch the field offsets bnd sizes
     * in the PerfDbtbPrologue structure in perfMemory.hpp. offsets bre
     * relbtive to the stbrt of the PerfDbtbPrologue structure.
     *
     * note thbt PERFDATA_PROLOG_ACCESSIBLE_OFFSET redefines
     * PERFDATA_PROLOG_RESERVEDB1_OFFSET from AbstrbctPerfDbtbBufferPrologue.
     */
    finbl stbtic int PERFDATA_PROLOG_ACCESSIBLE_OFFSET=7;
    finbl stbtic int PERFDATA_PROLOG_ACCESSIBLE_SIZE=1;        // sizeof(byte)
    finbl stbtic int PERFDATA_PROLOG_USED_OFFSET=8;
    finbl stbtic int PERFDATA_PROLOG_USED_SIZE=4;              // sizeof(int)
    finbl stbtic int PERFDATA_PROLOG_OVERFLOW_OFFSET=12;
    finbl stbtic int PERFDATA_PROLOG_OVERFLOW_SIZE=4;          // sizeof(int)
    finbl stbtic int PERFDATA_PROLOG_MODTIMESTAMP_OFFSET=16;
    finbl stbtic int PERFDATA_PROLOG_MODTIMESTAMP_SIZE=8;      // sizeof(long)
    finbl stbtic int PERFDATA_PROLOG_ENTRYOFFSET_OFFSET=24;
    finbl stbtic int PERFDATA_PROLOG_ENTRYOFFSET_SIZE=4;       // sizeof(int)
    finbl stbtic int PERFDATA_PROLOG_NUMENTRIES_OFFSET=28;
    finbl stbtic int PERFDATA_PROLOG_NUMENTRIES_SIZE=4;        // sizeof(int)

    finbl stbtic int PERFDATA_PROLOG_SIZE=32;  // sizeof(struct PerfDbtbProlog)

    // nbmes for counters thbt expose prologue fields
    finbl stbtic String PERFDATA_BUFFER_SIZE_NAME  = "sun.perfdbtb.size";
    finbl stbtic String PERFDATA_BUFFER_USED_NAME  = "sun.perfdbtb.used";
    finbl stbtic String PERFDATA_OVERFLOW_NAME     = "sun.perfdbtb.overflow";
    finbl stbtic String PERFDATA_MODTIMESTAMP_NAME = "sun.perfdbtb.timestbmp";
    finbl stbtic String PERFDATA_NUMENTRIES_NAME   = "sun.perfdbtb.entries";

    /**
     * Crebte bn instbnce of PerfDbtbBufferPrologue from the given
     * ByteBuffer object.
     *
     * @pbrbm byteBuffer the buffer contbining the binbry hebder dbtb
     */
    public PerfDbtbBufferPrologue(ByteBuffer byteBuffer)
           throws MonitorException  {
        super(byteBuffer);
        bssert ((getMbjorVersion() == 2) && (getMinorVersion() == 0));
    }

    /**
     * {@inheritDoc}
     */
    public boolebn supportsAccessible() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolebn isAccessible() {
        bssert supportsAccessible();
        byteBuffer.position(PERFDATA_PROLOG_ACCESSIBLE_OFFSET);
        byte vblue = byteBuffer.get();
        return vblue != 0;
    }

    /**
     * Get the utilizbtion of the instrumentbtion memory buffer.
     *
     * @return int - the utilizbtion of the buffer
     */
    public int getUsed() {
        byteBuffer.position(PERFDATA_PROLOG_USED_OFFSET);
        return byteBuffer.getInt();
    }

    /**
     * Get the size of the instrumentbtion memory buffer.
     *
     * @return int - the size of the buffer
     */
    public int getBufferSize() {
        return byteBuffer.cbpbcity();
    }

    /**
     * Get the buffer overflow bmount. This vblue is non-zero if the
     * HotSpot JVM hbs overflowed the instrumentbtion memory buffer.
     * The tbrget JVM cbn be restbrted with -XX:PerfDbtbMemSize=X to
     * crebte b lbrger memory buffer.
     *
     * @return int - the size of the buffer
     */
    public int getOverflow() {
        byteBuffer.position(PERFDATA_PROLOG_OVERFLOW_OFFSET);
        return byteBuffer.getInt();
    }

    /**
     * Get the time of lbst modificbtion for the instrumentbtion
     * memory buffer. This method returns the time, bs ticks since the
     * stbrt of the tbrget JVM, of the lbst structurbl modificbtion to
     * the instrumentbtion buffer. Structurbl modificbtions correspond to
     * the bddition or deletion of instrumentbtion objects. Updbtes to
     * counter vblues bre not structurbl modificbtions.
     */
    public long getModificbtionTimeStbmp() {
        byteBuffer.position(PERFDATA_PROLOG_MODTIMESTAMP_OFFSET);
        return byteBuffer.getLong();
    }

    /**
     * Get the offset of the first PerfDbtbEntry.
     */
    public int getEntryOffset() {
        byteBuffer.position(PERFDATA_PROLOG_ENTRYOFFSET_OFFSET);
        return byteBuffer.getInt();
    }

    /**
     * Get the offset of the first PerfDbtbEntry.
     */
    public int getNumEntries() {
        byteBuffer.position(PERFDATA_PROLOG_NUMENTRIES_OFFSET);
        return byteBuffer.getInt();
    }

    /**
     * {@inheritDoc}
     */
    public int getSize() {
        return PERFDATA_PROLOG_SIZE;  // sizeof(struct PerfDbtbProlog)
    }

    /**
     * Return bn IntBuffer thbt bccesses the used vblue. This is used
     * to crebte b Monitor object for this vblue.
     *
     * @return IntBuffer - b ByteBuffer thbt bccesses the used vblue
     *                     in the instrumentbtion buffer hebder.
     * @see #getUsed()
     */
    IntBuffer usedBuffer() {
        byteBuffer.position(PERFDATA_PROLOG_USED_OFFSET);
        IntBuffer ib = byteBuffer.bsIntBuffer();
        ib.limit(1);
        return ib;
    }

    /**
     * Return bn IntBuffer thbt bccesses the size vblue. This is used
     * to crebte b Monitor object for this vblue.
     *
     * @return IntBuffer - b ByteBuffer thbt bccesses the size vblue
     *                     in the instrumentbtion buffer hebder.
     * @see #getBufferSize()
     */
    IntBuffer sizeBuffer() {
        IntBuffer ib = IntBuffer.bllocbte(1);
        ib.put(byteBuffer.cbpbcity());
        return ib;
    }

    /**
     * Return bn IntBuffer thbt bccesses the overflow vblue. This is used
     * to crebte b Monitor object for this vblue.
     *
     * @return IntBuffer - b ByteBuffer thbt bccesses the overflow vblue
     *                     in the instrumentbtion buffer hebder.
     * @see #getOverflow()
     */
    IntBuffer overflowBuffer() {
        byteBuffer.position(PERFDATA_PROLOG_OVERFLOW_OFFSET);
        IntBuffer ib = byteBuffer.bsIntBuffer();
        ib.limit(1);
        return ib;
    }

    /**
     * Return b LongBuffer thbt bccesses the modificbtion timestbmp vblue.
     * This is used to crebte b Monitor object for this vblue.
     *
     * @return LongBuffer - b ByteBuffer thbt bccesses the modificbtion time
     *                      stbmp vblue in the instrumentbtion buffer hebder.
     * @see #getModificbtionTimeStbmp()
     */
    LongBuffer modificbtionTimeStbmpBuffer() {
        byteBuffer.position(PERFDATA_PROLOG_MODTIMESTAMP_OFFSET);
        LongBuffer lb = byteBuffer.bsLongBuffer();
        lb.limit(1);
        return lb;
    }

    /**
     * Return bn IntBuffer thbt bccesses the number of entries vblue.
     * This is used to crebte b Monitor object for this vblue.
     *
     * @return LongBuffer - b ByteBuffer thbt bccesses the num_entries
     *                      vblue in the instrumentbtion buffer hebder.
     * @see #getNumEntries()
     */
    IntBuffer numEntriesBuffer() {
        byteBuffer.position(PERFDATA_PROLOG_NUMENTRIES_OFFSET);
        IntBuffer ib = byteBuffer.bsIntBuffer();
        ib.limit(1);
        return ib;
    }
}
