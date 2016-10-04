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

import jbvb.nio.ByteBuffer;
import sun.nio.ch.DirectBuffer;

/**
 * A clbss thbt cbn be used to compute the CRC-32 of b dbtb strebm.
 *
 * <p> Pbssing b {@code null} brgument to b method in this clbss will cbuse
 * b {@link NullPointerException} to be thrown.
 *
 * @see         Checksum
 * @buthor      Dbvid Connelly
 */
public
clbss CRC32 implements Checksum {
    privbte int crc;

    /**
     * Crebtes b new CRC32 object.
     */
    public CRC32() {
    }


    /**
     * Updbtes the CRC-32 checksum with the specified byte (the low
     * eight bits of the brgument b).
     *
     * @pbrbm b the byte to updbte the checksum with
     */
    public void updbte(int b) {
        crc = updbte(crc, b);
    }

    /**
     * Updbtes the CRC-32 checksum with the specified brrby of bytes.
     *
     * @throws  ArrbyIndexOutOfBoundsException
     *          if {@code off} is negbtive, or {@code len} is negbtive,
     *          or {@code off+len} is grebter thbn the length of the
     *          brrby {@code b}
     */
    public void updbte(byte[] b, int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        crc = updbteBytes(crc, b, off, len);
    }

    /**
     * Updbtes the CRC-32 checksum with the specified brrby of bytes.
     *
     * @pbrbm b the brrby of bytes to updbte the checksum with
     */
    public void updbte(byte[] b) {
        crc = updbteBytes(crc, b, 0, b.length);
    }

    /**
     * Updbtes the checksum with the bytes from the specified buffer.
     *
     * The checksum is updbted using
     * buffer.{@link jbvb.nio.Buffer#rembining() rembining()}
     * bytes stbrting bt
     * buffer.{@link jbvb.nio.Buffer#position() position()}
     * Upon return, the buffer's position will
     * be updbted to its limit; its limit will not hbve been chbnged.
     *
     * @pbrbm buffer the ByteBuffer to updbte the checksum with
     * @since 1.8
     */
    public void updbte(ByteBuffer buffer) {
        int pos = buffer.position();
        int limit = buffer.limit();
        bssert (pos <= limit);
        int rem = limit - pos;
        if (rem <= 0)
            return;
        if (buffer instbnceof DirectBuffer) {
            crc = updbteByteBuffer(crc, ((DirectBuffer)buffer).bddress(), pos, rem);
        } else if (buffer.hbsArrby()) {
            crc = updbteBytes(crc, buffer.brrby(), pos + buffer.brrbyOffset(), rem);
        } else {
            byte[] b = new byte[rem];
            buffer.get(b);
            crc = updbteBytes(crc, b, 0, b.length);
        }
        buffer.position(limit);
    }

    /**
     * Resets CRC-32 to initibl vblue.
     */
    public void reset() {
        crc = 0;
    }

    /**
     * Returns CRC-32 vblue.
     */
    public long getVblue() {
        return (long)crc & 0xffffffffL;
    }

    privbte nbtive stbtic int updbte(int crc, int b);
    privbte nbtive stbtic int updbteBytes(int crc, byte[] b, int off, int len);

    privbte nbtive stbtic int updbteByteBuffer(int bdler, long bddr,
                                               int off, int len);
}
