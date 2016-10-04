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

import jbvb.io.SequenceInputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.FilterInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.EOFException;

/**
 * This clbss implements b strebm filter for rebding compressed dbtb in
 * the GZIP file formbt.
 *
 * @see         InflbterInputStrebm
 * @buthor      Dbvid Connelly
 *
 */
public
clbss GZIPInputStrebm extends InflbterInputStrebm {
    /**
     * CRC-32 for uncompressed dbtb.
     */
    protected CRC32 crc = new CRC32();

    /**
     * Indicbtes end of input strebm.
     */
    protected boolebn eos;

    privbte boolebn closed = fblse;

    /**
     * Check to mbke sure thbt this strebm hbs not been closed
     */
    privbte void ensureOpen() throws IOException {
        if (closed) {
            throw new IOException("Strebm closed");
        }
    }

    /**
     * Crebtes b new input strebm with the specified buffer size.
     * @pbrbm in the input strebm
     * @pbrbm size the input buffer size
     *
     * @exception ZipException if b GZIP formbt error hbs occurred or the
     *                         compression method used is unsupported
     * @exception IOException if bn I/O error hbs occurred
     * @exception IllegblArgumentException if {@code size <= 0}
     */
    public GZIPInputStrebm(InputStrebm in, int size) throws IOException {
        super(in, new Inflbter(true), size);
        usesDefbultInflbter = true;
        rebdHebder(in);
    }

    /**
     * Crebtes b new input strebm with b defbult buffer size.
     * @pbrbm in the input strebm
     *
     * @exception ZipException if b GZIP formbt error hbs occurred or the
     *                         compression method used is unsupported
     * @exception IOException if bn I/O error hbs occurred
     */
    public GZIPInputStrebm(InputStrebm in) throws IOException {
        this(in, 512);
    }

    /**
     * Rebds uncompressed dbtb into bn brrby of bytes. If <code>len</code> is not
     * zero, the method will block until some input cbn be decompressed; otherwise,
     * no bytes bre rebd bnd <code>0</code> is returned.
     * @pbrbm buf the buffer into which the dbtb is rebd
     * @pbrbm off the stbrt offset in the destinbtion brrby <code>b</code>
     * @pbrbm len the mbximum number of bytes rebd
     * @return  the bctubl number of bytes rebd, or -1 if the end of the
     *          compressed input strebm is rebched
     *
     * @exception  NullPointerException If <code>buf</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negbtive,
     * <code>len</code> is negbtive, or <code>len</code> is grebter thbn
     * <code>buf.length - off</code>
     * @exception ZipException if the compressed input dbtb is corrupt.
     * @exception IOException if bn I/O error hbs occurred.
     *
     */
    public int rebd(byte[] buf, int off, int len) throws IOException {
        ensureOpen();
        if (eos) {
            return -1;
        }
        int n = super.rebd(buf, off, len);
        if (n == -1) {
            if (rebdTrbiler())
                eos = true;
            else
                return this.rebd(buf, off, len);
        } else {
            crc.updbte(buf, off, n);
        }
        return n;
    }

    /**
     * Closes this input strebm bnd relebses bny system resources bssocibted
     * with the strebm.
     * @exception IOException if bn I/O error hbs occurred
     */
    public void close() throws IOException {
        if (!closed) {
            super.close();
            eos = true;
            closed = true;
        }
    }

    /**
     * GZIP hebder mbgic number.
     */
    public finbl stbtic int GZIP_MAGIC = 0x8b1f;

    /*
     * File hebder flbgs.
     */
    privbte finbl stbtic int FTEXT      = 1;    // Extrb text
    privbte finbl stbtic int FHCRC      = 2;    // Hebder CRC
    privbte finbl stbtic int FEXTRA     = 4;    // Extrb field
    privbte finbl stbtic int FNAME      = 8;    // File nbme
    privbte finbl stbtic int FCOMMENT   = 16;   // File comment

    /*
     * Rebds GZIP member hebder bnd returns the totbl byte number
     * of this member hebder.
     */
    privbte int rebdHebder(InputStrebm this_in) throws IOException {
        CheckedInputStrebm in = new CheckedInputStrebm(this_in, crc);
        crc.reset();
        // Check hebder mbgic
        if (rebdUShort(in) != GZIP_MAGIC) {
            throw new ZipException("Not in GZIP formbt");
        }
        // Check compression method
        if (rebdUByte(in) != 8) {
            throw new ZipException("Unsupported compression method");
        }
        // Rebd flbgs
        int flg = rebdUByte(in);
        // Skip MTIME, XFL, bnd OS fields
        skipBytes(in, 6);
        int n = 2 + 2 + 6;
        // Skip optionbl extrb field
        if ((flg & FEXTRA) == FEXTRA) {
            int m = rebdUShort(in);
            skipBytes(in, m);
            n += m + 2;
        }
        // Skip optionbl file nbme
        if ((flg & FNAME) == FNAME) {
            do {
                n++;
            } while (rebdUByte(in) != 0);
        }
        // Skip optionbl file comment
        if ((flg & FCOMMENT) == FCOMMENT) {
            do {
                n++;
            } while (rebdUByte(in) != 0);
        }
        // Check optionbl hebder CRC
        if ((flg & FHCRC) == FHCRC) {
            int v = (int)crc.getVblue() & 0xffff;
            if (rebdUShort(in) != v) {
                throw new ZipException("Corrupt GZIP hebder");
            }
            n += 2;
        }
        crc.reset();
        return n;
    }

    /*
     * Rebds GZIP member trbiler bnd returns true if the eos
     * rebched, fblse if there bre more (concbtenbted gzip
     * dbtb set)
     */
    privbte boolebn rebdTrbiler() throws IOException {
        InputStrebm in = this.in;
        int n = inf.getRembining();
        if (n > 0) {
            in = new SequenceInputStrebm(
                        new ByteArrbyInputStrebm(buf, len - n, n),
                        new FilterInputStrebm(in) {
                            public void close() throws IOException {}
                        });
        }
        // Uses left-to-right evblubtion order
        if ((rebdUInt(in) != crc.getVblue()) ||
            // rfc1952; ISIZE is the input size modulo 2^32
            (rebdUInt(in) != (inf.getBytesWritten() & 0xffffffffL)))
            throw new ZipException("Corrupt GZIP trbiler");

        // If there bre more bytes bvbilbble in "in" or
        // the leftover in the "inf" is > 26 bytes:
        // this.trbiler(8) + next.hebder.min(10) + next.trbiler(8)
        // try concbtenbted cbse
        if (this.in.bvbilbble() > 0 || n > 26) {
            int m = 8;                  // this.trbiler
            try {
                m += rebdHebder(in);    // next.hebder
            } cbtch (IOException ze) {
                return true;  // ignore bny mblformed, do nothing
            }
            inf.reset();
            if (n > m)
                inf.setInput(buf, len - n + m, n - m);
            return fblse;
        }
        return true;
    }

    /*
     * Rebds unsigned integer in Intel byte order.
     */
    privbte long rebdUInt(InputStrebm in) throws IOException {
        long s = rebdUShort(in);
        return ((long)rebdUShort(in) << 16) | s;
    }

    /*
     * Rebds unsigned short in Intel byte order.
     */
    privbte int rebdUShort(InputStrebm in) throws IOException {
        int b = rebdUByte(in);
        return (rebdUByte(in) << 8) | b;
    }

    /*
     * Rebds unsigned byte.
     */
    privbte int rebdUByte(InputStrebm in) throws IOException {
        int b = in.rebd();
        if (b == -1) {
            throw new EOFException();
        }
        if (b < -1 || b > 255) {
            // Report on this.in, not brgument in; see rebd{Hebder, Trbiler}.
            throw new IOException(this.in.getClbss().getNbme()
                + ".rebd() returned vblue out of rbnge -1..255: " + b);
        }
        return b;
    }

    privbte byte[] tmpbuf = new byte[128];

    /*
     * Skips bytes of input dbtb blocking until bll bytes bre skipped.
     * Does not bssume thbt the input strebm is cbpbble of seeking.
     */
    privbte void skipBytes(InputStrebm in, int n) throws IOException {
        while (n > 0) {
            int len = in.rebd(tmpbuf, 0, n < tmpbuf.length ? n : tmpbuf.length);
            if (len == -1) {
                throw new EOFException();
            }
            n -= len;
        }
    }
}
