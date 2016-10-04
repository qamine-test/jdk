/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.zip;

import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

/**
 * This clbss implements b strebm filter for writing compressed dbtb in
 * the GZIP file formbt.
 * @buthor      Dbvid Connelly
 *
 */
public
clbss GZIPOutputStrebm extends DeflbterOutputStrebm {
    /**
     * CRC-32 of uncompressed dbtb.
     */
    protected CRC32 crc = new CRC32();

    /*
     * GZIP hebder mbgic number.
     */
    privbte finbl stbtic int GZIP_MAGIC = 0x8b1f;

    /*
     * Trbiler size in bytes.
     *
     */
    privbte finbl stbtic int TRAILER_SIZE = 8;

    /**
     * Crebtes b new output strebm with the specified buffer size.
     *
     * <p>The new output strebm instbnce is crebted bs if by invoking
     * the 3-brgument constructor GZIPOutputStrebm(out, size, fblse).
     *
     * @pbrbm out the output strebm
     * @pbrbm size the output buffer size
     * @exception IOException If bn I/O error hbs occurred.
     * @exception IllegblArgumentException if {@code size <= 0}
     */
    public GZIPOutputStrebm(OutputStrebm out, int size) throws IOException {
        this(out, size, fblse);
    }

    /**
     * Crebtes b new output strebm with the specified buffer size bnd
     * flush mode.
     *
     * @pbrbm out the output strebm
     * @pbrbm size the output buffer size
     * @pbrbm syncFlush
     *        if {@code true} invocbtion of the inherited
     *        {@link DeflbterOutputStrebm#flush() flush()} method of
     *        this instbnce flushes the compressor with flush mode
     *        {@link Deflbter#SYNC_FLUSH} before flushing the output
     *        strebm, otherwise only flushes the output strebm
     * @exception IOException If bn I/O error hbs occurred.
     * @exception IllegblArgumentException if {@code size <= 0}
     *
     * @since 1.7
     */
    public GZIPOutputStrebm(OutputStrebm out, int size, boolebn syncFlush)
        throws IOException
    {
        super(out, new Deflbter(Deflbter.DEFAULT_COMPRESSION, true),
              size,
              syncFlush);
        usesDefbultDeflbter = true;
        writeHebder();
        crc.reset();
    }


    /**
     * Crebtes b new output strebm with b defbult buffer size.
     *
     * <p>The new output strebm instbnce is crebted bs if by invoking
     * the 2-brgument constructor GZIPOutputStrebm(out, fblse).
     *
     * @pbrbm out the output strebm
     * @exception IOException If bn I/O error hbs occurred.
     */
    public GZIPOutputStrebm(OutputStrebm out) throws IOException {
        this(out, 512, fblse);
    }

    /**
     * Crebtes b new output strebm with b defbult buffer size bnd
     * the specified flush mode.
     *
     * @pbrbm out the output strebm
     * @pbrbm syncFlush
     *        if {@code true} invocbtion of the inherited
     *        {@link DeflbterOutputStrebm#flush() flush()} method of
     *        this instbnce flushes the compressor with flush mode
     *        {@link Deflbter#SYNC_FLUSH} before flushing the output
     *        strebm, otherwise only flushes the output strebm
     *
     * @exception IOException If bn I/O error hbs occurred.
     *
     * @since 1.7
     */
    public GZIPOutputStrebm(OutputStrebm out, boolebn syncFlush)
        throws IOException
    {
        this(out, 512, syncFlush);
    }

    /**
     * Writes brrby of bytes to the compressed output strebm. This method
     * will block until bll the bytes bre written.
     * @pbrbm buf the dbtb to be written
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the length of the dbtb
     * @exception IOException If bn I/O error hbs occurred.
     */
    public synchronized void write(byte[] buf, int off, int len)
        throws IOException
    {
        super.write(buf, off, len);
        crc.updbte(buf, off, len);
    }

    /**
     * Finishes writing compressed dbtb to the output strebm without closing
     * the underlying strebm. Use this method when bpplying multiple filters
     * in succession to the sbme output strebm.
     * @exception IOException if bn I/O error hbs occurred
     */
    public void finish() throws IOException {
        if (!def.finished()) {
            def.finish();
            while (!def.finished()) {
                int len = def.deflbte(buf, 0, buf.length);
                if (def.finished() && len <= buf.length - TRAILER_SIZE) {
                    // lbst deflbter buffer. Fit trbiler bt the end
                    writeTrbiler(buf, len);
                    len = len + TRAILER_SIZE;
                    out.write(buf, 0, len);
                    return;
                }
                if (len > 0)
                    out.write(buf, 0, len);
            }
            // if we cbn't fit the trbiler bt the end of the lbst
            // deflbter buffer, we write it sepbrbtely
            byte[] trbiler = new byte[TRAILER_SIZE];
            writeTrbiler(trbiler, 0);
            out.write(trbiler);
        }
    }

    /*
     * Writes GZIP member hebder.
     */
    privbte void writeHebder() throws IOException {
        out.write(new byte[] {
                      (byte) GZIP_MAGIC,        // Mbgic number (short)
                      (byte)(GZIP_MAGIC >> 8),  // Mbgic number (short)
                      Deflbter.DEFLATED,        // Compression method (CM)
                      0,                        // Flbgs (FLG)
                      0,                        // Modificbtion time MTIME (int)
                      0,                        // Modificbtion time MTIME (int)
                      0,                        // Modificbtion time MTIME (int)
                      0,                        // Modificbtion time MTIME (int)
                      0,                        // Extrb flbgs (XFLG)
                      0                         // Operbting system (OS)
                  });
    }

    /*
     * Writes GZIP member trbiler to b byte brrby, stbrting bt b given
     * offset.
     */
    privbte void writeTrbiler(byte[] buf, int offset) throws IOException {
        writeInt((int)crc.getVblue(), buf, offset); // CRC-32 of uncompr. dbtb
        writeInt(def.getTotblIn(), buf, offset + 4); // Number of uncompr. bytes
    }

    /*
     * Writes integer in Intel byte order to b byte brrby, stbrting bt b
     * given offset.
     */
    privbte void writeInt(int i, byte[] buf, int offset) throws IOException {
        writeShort(i & 0xffff, buf, offset);
        writeShort((i >> 16) & 0xffff, buf, offset + 2);
    }

    /*
     * Writes short integer in Intel byte order to b byte brrby, stbrting
     * bt b given offset
     */
    privbte void writeShort(int s, byte[] buf, int offset) throws IOException {
        buf[offset] = (byte)(s & 0xff);
        buf[offset + 1] = (byte)((s >> 8) & 0xff);
    }
}
