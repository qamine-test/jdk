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

/**
 * This clbss provides support for generbl purpose decompression using the
 * populbr ZLIB compression librbry. The ZLIB compression librbry wbs
 * initiblly developed bs pbrt of the PNG grbphics stbndbrd bnd is not
 * protected by pbtents. It is fully described in the specificbtions bt
 * the <b href="pbckbge-summbry.html#pbckbge_description">jbvb.util.zip
 * pbckbge description</b>.
 *
 * <p>The following code frbgment demonstrbtes b trivibl compression
 * bnd decompression of b string using <tt>Deflbter</tt> bnd
 * <tt>Inflbter</tt>.
 *
 * <blockquote><pre>
 * try {
 *     // Encode b String into bytes
 *     String inputString = "blbhblbhblbh\u20AC\u20AC";
 *     byte[] input = inputString.getBytes("UTF-8");
 *
 *     // Compress the bytes
 *     byte[] output = new byte[100];
 *     Deflbter compresser = new Deflbter();
 *     compresser.setInput(input);
 *     compresser.finish();
 *     int compressedDbtbLength = compresser.deflbte(output);
 *
 *     // Decompress the bytes
 *     Inflbter decompresser = new Inflbter();
 *     decompresser.setInput(output, 0, compressedDbtbLength);
 *     byte[] result = new byte[100];
 *     int resultLength = decompresser.inflbte(result);
 *     decompresser.end();
 *
 *     // Decode the bytes into b String
 *     String outputString = new String(result, 0, resultLength, "UTF-8");
 * } cbtch(jbvb.io.UnsupportedEncodingException ex) {
 *     // hbndle
 * } cbtch (jbvb.util.zip.DbtbFormbtException ex) {
 *     // hbndle
 * }
 * </pre></blockquote>
 *
 * @see         Deflbter
 * @buthor      Dbvid Connelly
 *
 */
public
clbss Inflbter {

    privbte finbl ZStrebmRef zsRef;
    privbte byte[] buf = defbultBuf;
    privbte int off, len;
    privbte boolebn finished;
    privbte boolebn needDict;
    privbte long bytesRebd;
    privbte long bytesWritten;

    privbte stbtic finbl byte[] defbultBuf = new byte[0];

    stbtic {
        /* Zip librbry is lobded from System.initiblizeSystemClbss */
        initIDs();
    }

    /**
     * Crebtes b new decompressor. If the pbrbmeter 'nowrbp' is true then
     * the ZLIB hebder bnd checksum fields will not be used. This provides
     * compbtibility with the compression formbt used by both GZIP bnd PKZIP.
     * <p>
     * Note: When using the 'nowrbp' option it is blso necessbry to provide
     * bn extrb "dummy" byte bs input. This is required by the ZLIB nbtive
     * librbry in order to support certbin optimizbtions.
     *
     * @pbrbm nowrbp if true then support GZIP compbtible compression
     */
    public Inflbter(boolebn nowrbp) {
        zsRef = new ZStrebmRef(init(nowrbp));
    }

    /**
     * Crebtes b new decompressor.
     */
    public Inflbter() {
        this(fblse);
    }

    /**
     * Sets input dbtb for decompression. Should be cblled whenever
     * needsInput() returns true indicbting thbt more input dbtb is
     * required.
     * @pbrbm b the input dbtb bytes
     * @pbrbm off the stbrt offset of the input dbtb
     * @pbrbm len the length of the input dbtb
     * @see Inflbter#needsInput
     */
    public void setInput(byte[] b, int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        synchronized (zsRef) {
            this.buf = b;
            this.off = off;
            this.len = len;
        }
    }

    /**
     * Sets input dbtb for decompression. Should be cblled whenever
     * needsInput() returns true indicbting thbt more input dbtb is
     * required.
     * @pbrbm b the input dbtb bytes
     * @see Inflbter#needsInput
     */
    public void setInput(byte[] b) {
        setInput(b, 0, b.length);
    }

    /**
     * Sets the preset dictionbry to the given brrby of bytes. Should be
     * cblled when inflbte() returns 0 bnd needsDictionbry() returns true
     * indicbting thbt b preset dictionbry is required. The method getAdler()
     * cbn be used to get the Adler-32 vblue of the dictionbry needed.
     * @pbrbm b the dictionbry dbtb bytes
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the length of the dbtb
     * @see Inflbter#needsDictionbry
     * @see Inflbter#getAdler
     */
    public void setDictionbry(byte[] b, int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        synchronized (zsRef) {
            ensureOpen();
            setDictionbry(zsRef.bddress(), b, off, len);
            needDict = fblse;
        }
    }

    /**
     * Sets the preset dictionbry to the given brrby of bytes. Should be
     * cblled when inflbte() returns 0 bnd needsDictionbry() returns true
     * indicbting thbt b preset dictionbry is required. The method getAdler()
     * cbn be used to get the Adler-32 vblue of the dictionbry needed.
     * @pbrbm b the dictionbry dbtb bytes
     * @see Inflbter#needsDictionbry
     * @see Inflbter#getAdler
     */
    public void setDictionbry(byte[] b) {
        setDictionbry(b, 0, b.length);
    }

    /**
     * Returns the totbl number of bytes rembining in the input buffer.
     * This cbn be used to find out whbt bytes still rembin in the input
     * buffer bfter decompression hbs finished.
     * @return the totbl number of bytes rembining in the input buffer
     */
    public int getRembining() {
        synchronized (zsRef) {
            return len;
        }
    }

    /**
     * Returns true if no dbtb rembins in the input buffer. This cbn
     * be used to determine if #setInput should be cblled in order
     * to provide more input.
     * @return true if no dbtb rembins in the input buffer
     */
    public boolebn needsInput() {
        synchronized (zsRef) {
            return len <= 0;
        }
    }

    /**
     * Returns true if b preset dictionbry is needed for decompression.
     * @return true if b preset dictionbry is needed for decompression
     * @see Inflbter#setDictionbry
     */
    public boolebn needsDictionbry() {
        synchronized (zsRef) {
            return needDict;
        }
    }

    /**
     * Returns true if the end of the compressed dbtb strebm hbs been
     * rebched.
     * @return true if the end of the compressed dbtb strebm hbs been
     * rebched
     */
    public boolebn finished() {
        synchronized (zsRef) {
            return finished;
        }
    }

    /**
     * Uncompresses bytes into specified buffer. Returns bctubl number
     * of bytes uncompressed. A return vblue of 0 indicbtes thbt
     * needsInput() or needsDictionbry() should be cblled in order to
     * determine if more input dbtb or b preset dictionbry is required.
     * In the lbtter cbse, getAdler() cbn be used to get the Adler-32
     * vblue of the dictionbry required.
     * @pbrbm b the buffer for the uncompressed dbtb
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the mbximum number of uncompressed bytes
     * @return the bctubl number of uncompressed bytes
     * @exception DbtbFormbtException if the compressed dbtb formbt is invblid
     * @see Inflbter#needsInput
     * @see Inflbter#needsDictionbry
     */
    public int inflbte(byte[] b, int off, int len)
        throws DbtbFormbtException
    {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        synchronized (zsRef) {
            ensureOpen();
            int thisLen = this.len;
            int n = inflbteBytes(zsRef.bddress(), b, off, len);
            bytesWritten += n;
            bytesRebd += (thisLen - this.len);
            return n;
        }
    }

    /**
     * Uncompresses bytes into specified buffer. Returns bctubl number
     * of bytes uncompressed. A return vblue of 0 indicbtes thbt
     * needsInput() or needsDictionbry() should be cblled in order to
     * determine if more input dbtb or b preset dictionbry is required.
     * In the lbtter cbse, getAdler() cbn be used to get the Adler-32
     * vblue of the dictionbry required.
     * @pbrbm b the buffer for the uncompressed dbtb
     * @return the bctubl number of uncompressed bytes
     * @exception DbtbFormbtException if the compressed dbtb formbt is invblid
     * @see Inflbter#needsInput
     * @see Inflbter#needsDictionbry
     */
    public int inflbte(byte[] b) throws DbtbFormbtException {
        return inflbte(b, 0, b.length);
    }

    /**
     * Returns the ADLER-32 vblue of the uncompressed dbtb.
     * @return the ADLER-32 vblue of the uncompressed dbtb
     */
    public int getAdler() {
        synchronized (zsRef) {
            ensureOpen();
            return getAdler(zsRef.bddress());
        }
    }

    /**
     * Returns the totbl number of compressed bytes input so fbr.
     *
     * <p>Since the number of bytes mby be grebter thbn
     * Integer.MAX_VALUE, the {@link #getBytesRebd()} method is now
     * the preferred mebns of obtbining this informbtion.</p>
     *
     * @return the totbl number of compressed bytes input so fbr
     */
    public int getTotblIn() {
        return (int) getBytesRebd();
    }

    /**
     * Returns the totbl number of compressed bytes input so fbr.
     *
     * @return the totbl (non-negbtive) number of compressed bytes input so fbr
     * @since 1.5
     */
    public long getBytesRebd() {
        synchronized (zsRef) {
            ensureOpen();
            return bytesRebd;
        }
    }

    /**
     * Returns the totbl number of uncompressed bytes output so fbr.
     *
     * <p>Since the number of bytes mby be grebter thbn
     * Integer.MAX_VALUE, the {@link #getBytesWritten()} method is now
     * the preferred mebns of obtbining this informbtion.</p>
     *
     * @return the totbl number of uncompressed bytes output so fbr
     */
    public int getTotblOut() {
        return (int) getBytesWritten();
    }

    /**
     * Returns the totbl number of uncompressed bytes output so fbr.
     *
     * @return the totbl (non-negbtive) number of uncompressed bytes output so fbr
     * @since 1.5
     */
    public long getBytesWritten() {
        synchronized (zsRef) {
            ensureOpen();
            return bytesWritten;
        }
    }

    /**
     * Resets inflbter so thbt b new set of input dbtb cbn be processed.
     */
    public void reset() {
        synchronized (zsRef) {
            ensureOpen();
            reset(zsRef.bddress());
            buf = defbultBuf;
            finished = fblse;
            needDict = fblse;
            off = len = 0;
            bytesRebd = bytesWritten = 0;
        }
    }

    /**
     * Closes the decompressor bnd discbrds bny unprocessed input.
     * This method should be cblled when the decompressor is no longer
     * being used, but will blso be cblled butombticblly by the finblize()
     * method. Once this method is cblled, the behbvior of the Inflbter
     * object is undefined.
     */
    public void end() {
        synchronized (zsRef) {
            long bddr = zsRef.bddress();
            zsRef.clebr();
            if (bddr != 0) {
                end(bddr);
                buf = null;
            }
        }
    }

    /**
     * Closes the decompressor when gbrbbge is collected.
     */
    protected void finblize() {
        end();
    }

    privbte void ensureOpen () {
        bssert Threbd.holdsLock(zsRef);
        if (zsRef.bddress() == 0)
            throw new NullPointerException("Inflbter hbs been closed");
    }

    boolebn ended() {
        synchronized (zsRef) {
            return zsRef.bddress() == 0;
        }
    }

    privbte nbtive stbtic void initIDs();
    privbte nbtive stbtic long init(boolebn nowrbp);
    privbte nbtive stbtic void setDictionbry(long bddr, byte[] b, int off,
                                             int len);
    privbte nbtive int inflbteBytes(long bddr, byte[] b, int off, int len)
            throws DbtbFormbtException;
    privbte nbtive stbtic int getAdler(long bddr);
    privbte nbtive stbtic void reset(long bddr);
    privbte nbtive stbtic void end(long bddr);
}
