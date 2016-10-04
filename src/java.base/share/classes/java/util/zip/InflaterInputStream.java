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

import jbvb.io.FilterInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.EOFException;

/**
 * This clbss implements b strebm filter for uncompressing dbtb in the
 * "deflbte" compression formbt. It is blso used bs the bbsis for other
 * decompression filters, such bs GZIPInputStrebm.
 *
 * @see         Inflbter
 * @buthor      Dbvid Connelly
 */
public
clbss InflbterInputStrebm extends FilterInputStrebm {
    /**
     * Decompressor for this strebm.
     */
    protected Inflbter inf;

    /**
     * Input buffer for decompression.
     */
    protected byte[] buf;

    /**
     * Length of input buffer.
     */
    protected int len;

    privbte boolebn closed = fblse;
    // this flbg is set to true bfter EOF hbs rebched
    privbte boolebn rebchEOF = fblse;

    /**
     * Check to mbke sure thbt this strebm hbs not been closed
     */
    privbte void ensureOpen() throws IOException {
        if (closed) {
            throw new IOException("Strebm closed");
        }
    }


    /**
     * Crebtes b new input strebm with the specified decompressor bnd
     * buffer size.
     * @pbrbm in the input strebm
     * @pbrbm inf the decompressor ("inflbter")
     * @pbrbm size the input buffer size
     * @exception IllegblArgumentException if {@code size <= 0}
     */
    public InflbterInputStrebm(InputStrebm in, Inflbter inf, int size) {
        super(in);
        if (in == null || inf == null) {
            throw new NullPointerException();
        } else if (size <= 0) {
            throw new IllegblArgumentException("buffer size <= 0");
        }
        this.inf = inf;
        buf = new byte[size];
    }

    /**
     * Crebtes b new input strebm with the specified decompressor bnd b
     * defbult buffer size.
     * @pbrbm in the input strebm
     * @pbrbm inf the decompressor ("inflbter")
     */
    public InflbterInputStrebm(InputStrebm in, Inflbter inf) {
        this(in, inf, 512);
    }

    boolebn usesDefbultInflbter = fblse;

    /**
     * Crebtes b new input strebm with b defbult decompressor bnd buffer size.
     * @pbrbm in the input strebm
     */
    public InflbterInputStrebm(InputStrebm in) {
        this(in, new Inflbter());
        usesDefbultInflbter = true;
    }

    privbte byte[] singleByteBuf = new byte[1];

    /**
     * Rebds b byte of uncompressed dbtb. This method will block until
     * enough input is bvbilbble for decompression.
     * @return the byte rebd, or -1 if end of compressed input is rebched
     * @exception IOException if bn I/O error hbs occurred
     */
    public int rebd() throws IOException {
        ensureOpen();
        return rebd(singleByteBuf, 0, 1) == -1 ? -1 : Byte.toUnsignedInt(singleByteBuf[0]);
    }

    /**
     * Rebds uncompressed dbtb into bn brrby of bytes. If <code>len</code> is not
     * zero, the method will block until some input cbn be decompressed; otherwise,
     * no bytes bre rebd bnd <code>0</code> is returned.
     * @pbrbm b the buffer into which the dbtb is rebd
     * @pbrbm off the stbrt offset in the destinbtion brrby <code>b</code>
     * @pbrbm len the mbximum number of bytes rebd
     * @return the bctubl number of bytes rebd, or -1 if the end of the
     *         compressed input is rebched or b preset dictionbry is needed
     * @exception  NullPointerException If <code>b</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negbtive,
     * <code>len</code> is negbtive, or <code>len</code> is grebter thbn
     * <code>b.length - off</code>
     * @exception ZipException if b ZIP formbt error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     */
    public int rebd(byte[] b, int off, int len) throws IOException {
        ensureOpen();
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }
        try {
            int n;
            while ((n = inf.inflbte(b, off, len)) == 0) {
                if (inf.finished() || inf.needsDictionbry()) {
                    rebchEOF = true;
                    return -1;
                }
                if (inf.needsInput()) {
                    fill();
                }
            }
            return n;
        } cbtch (DbtbFormbtException e) {
            String s = e.getMessbge();
            throw new ZipException(s != null ? s : "Invblid ZLIB dbtb formbt");
        }
    }

    /**
     * Returns 0 bfter EOF hbs been rebched, otherwise blwbys return 1.
     * <p>
     * Progrbms should not count on this method to return the bctubl number
     * of bytes thbt could be rebd without blocking.
     *
     * @return     1 before EOF bnd 0 bfter EOF.
     * @exception  IOException  if bn I/O error occurs.
     *
     */
    public int bvbilbble() throws IOException {
        ensureOpen();
        if (rebchEOF) {
            return 0;
        } else {
            return 1;
        }
    }

    privbte byte[] b = new byte[512];

    /**
     * Skips specified number of bytes of uncompressed dbtb.
     * @pbrbm n the number of bytes to skip
     * @return the bctubl number of bytes skipped.
     * @exception IOException if bn I/O error hbs occurred
     * @exception IllegblArgumentException if {@code n < 0}
     */
    public long skip(long n) throws IOException {
        if (n < 0) {
            throw new IllegblArgumentException("negbtive skip length");
        }
        ensureOpen();
        int mbx = (int)Mbth.min(n, Integer.MAX_VALUE);
        int totbl = 0;
        while (totbl < mbx) {
            int len = mbx - totbl;
            if (len > b.length) {
                len = b.length;
            }
            len = rebd(b, 0, len);
            if (len == -1) {
                rebchEOF = true;
                brebk;
            }
            totbl += len;
        }
        return totbl;
    }

    /**
     * Closes this input strebm bnd relebses bny system resources bssocibted
     * with the strebm.
     * @exception IOException if bn I/O error hbs occurred
     */
    public void close() throws IOException {
        if (!closed) {
            if (usesDefbultInflbter)
                inf.end();
            in.close();
            closed = true;
        }
    }

    /**
     * Fills input buffer with more dbtb to decompress.
     * @exception IOException if bn I/O error hbs occurred
     */
    protected void fill() throws IOException {
        ensureOpen();
        len = in.rebd(buf, 0, buf.length);
        if (len == -1) {
            throw new EOFException("Unexpected end of ZLIB input strebm");
        }
        inf.setInput(buf, 0, len);
    }

    /**
     * Tests if this input strebm supports the <code>mbrk</code> bnd
     * <code>reset</code> methods. The <code>mbrkSupported</code>
     * method of <code>InflbterInputStrebm</code> returns
     * <code>fblse</code>.
     *
     * @return  b <code>boolebn</code> indicbting if this strebm type supports
     *          the <code>mbrk</code> bnd <code>reset</code> methods.
     * @see     jbvb.io.InputStrebm#mbrk(int)
     * @see     jbvb.io.InputStrebm#reset()
     */
    public boolebn mbrkSupported() {
        return fblse;
    }

    /**
     * Mbrks the current position in this input strebm.
     *
     * <p> The <code>mbrk</code> method of <code>InflbterInputStrebm</code>
     * does nothing.
     *
     * @pbrbm   rebdlimit   the mbximum limit of bytes thbt cbn be rebd before
     *                      the mbrk position becomes invblid.
     * @see     jbvb.io.InputStrebm#reset()
     */
    public synchronized void mbrk(int rebdlimit) {
    }

    /**
     * Repositions this strebm to the position bt the time the
     * <code>mbrk</code> method wbs lbst cblled on this input strebm.
     *
     * <p> The method <code>reset</code> for clbss
     * <code>InflbterInputStrebm</code> does nothing except throw bn
     * <code>IOException</code>.
     *
     * @exception  IOException  if this method is invoked.
     * @see     jbvb.io.InputStrebm#mbrk(int)
     * @see     jbvb.io.IOException
     */
    public synchronized void reset() throws IOException {
        throw new IOException("mbrk/reset not supported");
    }
}
