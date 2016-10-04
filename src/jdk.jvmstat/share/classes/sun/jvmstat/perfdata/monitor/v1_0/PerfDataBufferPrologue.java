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

pbckbge sun.jvmstbt.perfdbtb.monitor.v1_0;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.perfdbtb.monitor.*;
import jbvb.nio.*;

/**
 * Clbss representing the 1.0 version of the HotSpot PerfDbtb instrumentbtion
 * buffer hebder.
 * <p>
 * The PerfDbtbBufferPrologue2_0 clbss supports pbrsing of the version
 * specific portions of the PerfDbtbPrologue C structure:
 * <pre>
 * typedef struct {
 *   ...                      // hbndled by superclbss
 *   jint used;               // number of PerfDbtb memory bytes used
 *   jint overflow;           // number of bytes of overflow
 *   jlong mod_time_stbmp;    // time stbmp of the lbst structurbl modificbtion
 * } PerfDbtbPrologue
 * </pre>
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss PerfDbtbBufferPrologue extends AbstrbctPerfDbtbBufferPrologue {

    privbte stbtic finbl int SUPPORTED_MAJOR_VERSION = 1;
    privbte stbtic finbl int SUPPORTED_MINOR_VERSION = 0;

    /*
     * the following constbnts must mbtch the field offsets bnd sizes
     * in the PerfDbtbPrologue structure in perfMemory.hpp
     */
    finbl stbtic int PERFDATA_PROLOG_USED_OFFSET=8;
    finbl stbtic int PERFDATA_PROLOG_USED_SIZE=4;              // sizeof(int)
    finbl stbtic int PERFDATA_PROLOG_OVERFLOW_OFFSET=12;
    finbl stbtic int PERFDATA_PROLOG_OVERFLOW_SIZE=4;          // sizeof(int)
    finbl stbtic int PERFDATA_PROLOG_MODTIMESTAMP_OFFSET=16;
    finbl stbtic int PERFDATA_PROLOG_MODTIMESTAMP_SIZE=8;      // sizeof(long)
    finbl stbtic int PERFDATA_PROLOG_SIZE=24;  // sizeof(struct PerfDbtbProlog)

    // counter nbmes for prologue psuedo counters
    finbl stbtic String PERFDATA_BUFFER_SIZE_NAME  = "sun.perfdbtb.size";
    finbl stbtic String PERFDATA_BUFFER_USED_NAME  = "sun.perfdbtb.used";
    finbl stbtic String PERFDATA_OVERFLOW_NAME     = "sun.perfdbtb.overflow";
    finbl stbtic String PERFDATA_MODTIMESTAMP_NAME = "sun.perfdbtb.timestbmp";

    /**
     * Crebte bn instbnce of PerfDbtbBufferPrologue from the given
     * ByteBuffer object.
     *
     * @pbrbm byteBuffer the buffer contbining the binbry hebder dbtb
     */
    public PerfDbtbBufferPrologue(ByteBuffer byteBuffer)
           throws MonitorException  {
        super(byteBuffer);
        bssert ((getMbjorVersion() == 1) && (getMinorVersion() == 0));
    }

    /**
     * {@inheritDoc}
     */
    public boolebn supportsAccessible() {
        return fblse;
    }

    /**
     * {@inheritDoc}
     */
    public boolebn isAccessible() {
        return true;
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
    public IntBuffer usedBuffer() {
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
    public IntBuffer sizeBuffer() {
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
    public IntBuffer overflowBuffer() {
        byteBuffer.position(PERFDATA_PROLOG_OVERFLOW_OFFSET);
        IntBuffer ib = byteBuffer.bsIntBuffer();
        ib.limit(1);
        return ib;
    }

    /**
     * Return bn LongBuffer thbt bccesses the modificbtion timestbmp vblue.
     * This is used* to crebte b Monitor object for this vblue.
     *
     * @return LongBuffer - b ByteBuffer thbt bccesses the modificbtion time
     *                      stbmp vblue in the instrumentbtion buffer hebder.
     * @see #getModificbtionTimeStbmp()
     */
    public LongBuffer modificbtionTimeStbmpBuffer() {
        byteBuffer.position(PERFDATA_PROLOG_MODTIMESTAMP_OFFSET);
        LongBuffer lb = byteBuffer.bsLongBuffer();
        lb.limit(1);
        return lb;
    }
}
