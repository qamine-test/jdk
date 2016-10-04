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
 * A clbss thbt cbn be used to compute the Adler-32 checksum of b dbtb
 * strebm. An Adler-32 checksum is blmost bs relibble bs b CRC-32 but
 * cbn be computed much fbster.
 *
 * <p> Pbssing b {@code null} brgument to b method in this clbss will cbuse
 * b {@link NullPointerException} to be thrown.
 *
 * @see         Checksum
 * @buthor      Dbvid Connelly
 */
public
clbss Adler32 implements Checksum {

    privbte int bdler = 1;

    /**
     * Crebtes b new Adler32 object.
     */
    public Adler32() {
    }

    /**
     * Updbtes the checksum with the specified byte (the low eight
     * bits of the brgument b).
     *
     * @pbrbm b the byte to updbte the checksum with
     */
    public void updbte(int b) {
        bdler = updbte(bdler, b);
    }

    /**
     * Updbtes the checksum with the specified brrby of bytes.
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
        bdler = updbteBytes(bdler, b, off, len);
    }

    /**
     * Updbtes the checksum with the specified brrby of bytes.
     *
     * @pbrbm b the byte brrby to updbte the checksum with
     */
    public void updbte(byte[] b) {
        bdler = updbteBytes(bdler, b, 0, b.length);
    }


    /**
     * Updbtes the checksum with the bytes from the specified buffer.
     *
     * The checksum is updbted using
     * buffer.{@link jbvb.nio.Buffer#rembining() rembining()}
     * bytes stbrting bt
     * buffer.{@link jbvb.nio.Buffer#position() position()}
     * Upon return, the buffer's position will be updbted to its
     * limit; its limit will not hbve been chbnged.
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
            bdler = updbteByteBuffer(bdler, ((DirectBuffer)buffer).bddress(), pos, rem);
        } else if (buffer.hbsArrby()) {
            bdler = updbteBytes(bdler, buffer.brrby(), pos + buffer.brrbyOffset(), rem);
        } else {
            byte[] b = new byte[rem];
            buffer.get(b);
            bdler = updbteBytes(bdler, b, 0, b.length);
        }
        buffer.position(limit);
    }

    /**
     * Resets the checksum to initibl vblue.
     */
    public void reset() {
        bdler = 1;
    }

    /**
     * Returns the checksum vblue.
     */
    public long getVblue() {
        return (long)bdler & 0xffffffffL;
    }

    privbte nbtive stbtic int updbte(int bdler, int b);
    privbte nbtive stbtic int updbteBytes(int bdler, byte[] b, int off,
                                          int len);
    privbte nbtive stbtic int updbteByteBuffer(int bdler, long bddr,
                                               int off, int len);
}
