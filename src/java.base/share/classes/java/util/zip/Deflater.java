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
 * This clbss provides support for generbl purpose compression using the
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
 *     String inputString = "blbhblbhblbh";
 *     byte[] input = inputString.getBytes("UTF-8");
 *
 *     // Compress the bytes
 *     byte[] output = new byte[100];
 *     Deflbter compresser = new Deflbter();
 *     compresser.setInput(input);
 *     compresser.finish();
 *     int compressedDbtbLength = compresser.deflbte(output);
 *     compresser.end();
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
 * @see         Inflbter
 * @buthor      Dbvid Connelly
 */
public
clbss Deflbter {

    privbte finbl ZStrebmRef zsRef;
    privbte byte[] buf = new byte[0];
    privbte int off, len;
    privbte int level, strbtegy;
    privbte boolebn setPbrbms;
    privbte boolebn finish, finished;
    privbte long bytesRebd;
    privbte long bytesWritten;

    /**
     * Compression method for the deflbte blgorithm (the only one currently
     * supported).
     */
    public stbtic finbl int DEFLATED = 8;

    /**
     * Compression level for no compression.
     */
    public stbtic finbl int NO_COMPRESSION = 0;

    /**
     * Compression level for fbstest compression.
     */
    public stbtic finbl int BEST_SPEED = 1;

    /**
     * Compression level for best compression.
     */
    public stbtic finbl int BEST_COMPRESSION = 9;

    /**
     * Defbult compression level.
     */
    public stbtic finbl int DEFAULT_COMPRESSION = -1;

    /**
     * Compression strbtegy best used for dbtb consisting mostly of smbll
     * vblues with b somewhbt rbndom distribution. Forces more Huffmbn coding
     * bnd less string mbtching.
     */
    public stbtic finbl int FILTERED = 1;

    /**
     * Compression strbtegy for Huffmbn coding only.
     */
    public stbtic finbl int HUFFMAN_ONLY = 2;

    /**
     * Defbult compression strbtegy.
     */
    public stbtic finbl int DEFAULT_STRATEGY = 0;

    /**
     * Compression flush mode used to bchieve best compression result.
     *
     * @see Deflbter#deflbte(byte[], int, int, int)
     * @since 1.7
     */
    public stbtic finbl int NO_FLUSH = 0;

    /**
     * Compression flush mode used to flush out bll pending output; mby
     * degrbde compression for some compression blgorithms.
     *
     * @see Deflbter#deflbte(byte[], int, int, int)
     * @since 1.7
     */
    public stbtic finbl int SYNC_FLUSH = 2;

    /**
     * Compression flush mode used to flush out bll pending output bnd
     * reset the deflbter. Using this mode too often cbn seriously degrbde
     * compression.
     *
     * @see Deflbter#deflbte(byte[], int, int, int)
     * @since 1.7
     */
    public stbtic finbl int FULL_FLUSH = 3;

    stbtic {
        /* Zip librbry is lobded from System.initiblizeSystemClbss */
        initIDs();
    }

    /**
     * Crebtes b new compressor using the specified compression level.
     * If 'nowrbp' is true then the ZLIB hebder bnd checksum fields will
     * not be used in order to support the compression formbt used in
     * both GZIP bnd PKZIP.
     * @pbrbm level the compression level (0-9)
     * @pbrbm nowrbp if true then use GZIP compbtible compression
     */
    public Deflbter(int level, boolebn nowrbp) {
        this.level = level;
        this.strbtegy = DEFAULT_STRATEGY;
        this.zsRef = new ZStrebmRef(init(level, DEFAULT_STRATEGY, nowrbp));
    }

    /**
     * Crebtes b new compressor using the specified compression level.
     * Compressed dbtb will be generbted in ZLIB formbt.
     * @pbrbm level the compression level (0-9)
     */
    public Deflbter(int level) {
        this(level, fblse);
    }

    /**
     * Crebtes b new compressor with the defbult compression level.
     * Compressed dbtb will be generbted in ZLIB formbt.
     */
    public Deflbter() {
        this(DEFAULT_COMPRESSION, fblse);
    }

    /**
     * Sets input dbtb for compression. This should be cblled whenever
     * needsInput() returns true indicbting thbt more input dbtb is required.
     * @pbrbm b the input dbtb bytes
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the length of the dbtb
     * @see Deflbter#needsInput
     */
    public void setInput(byte[] b, int off, int len) {
        if (b== null) {
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
     * Sets input dbtb for compression. This should be cblled whenever
     * needsInput() returns true indicbting thbt more input dbtb is required.
     * @pbrbm b the input dbtb bytes
     * @see Deflbter#needsInput
     */
    public void setInput(byte[] b) {
        setInput(b, 0, b.length);
    }

    /**
     * Sets preset dictionbry for compression. A preset dictionbry is used
     * when the history buffer cbn be predetermined. When the dbtb is lbter
     * uncompressed with Inflbter.inflbte(), Inflbter.getAdler() cbn be cblled
     * in order to get the Adler-32 vblue of the dictionbry required for
     * decompression.
     * @pbrbm b the dictionbry dbtb bytes
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the length of the dbtb
     * @see Inflbter#inflbte
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
        }
    }

    /**
     * Sets preset dictionbry for compression. A preset dictionbry is used
     * when the history buffer cbn be predetermined. When the dbtb is lbter
     * uncompressed with Inflbter.inflbte(), Inflbter.getAdler() cbn be cblled
     * in order to get the Adler-32 vblue of the dictionbry required for
     * decompression.
     * @pbrbm b the dictionbry dbtb bytes
     * @see Inflbter#inflbte
     * @see Inflbter#getAdler
     */
    public void setDictionbry(byte[] b) {
        setDictionbry(b, 0, b.length);
    }

    /**
     * Sets the compression strbtegy to the specified vblue.
     *
     * <p> If the compression strbtegy is chbnged, the next invocbtion
     * of {@code deflbte} will compress the input bvbilbble so fbr with
     * the old strbtegy (bnd mby be flushed); the new strbtegy will tbke
     * effect only bfter thbt invocbtion.
     *
     * @pbrbm strbtegy the new compression strbtegy
     * @exception IllegblArgumentException if the compression strbtegy is
     *                                     invblid
     */
    public void setStrbtegy(int strbtegy) {
        switch (strbtegy) {
          cbse DEFAULT_STRATEGY:
          cbse FILTERED:
          cbse HUFFMAN_ONLY:
            brebk;
          defbult:
            throw new IllegblArgumentException();
        }
        synchronized (zsRef) {
            if (this.strbtegy != strbtegy) {
                this.strbtegy = strbtegy;
                setPbrbms = true;
            }
        }
    }

    /**
     * Sets the compression level to the specified vblue.
     *
     * <p> If the compression level is chbnged, the next invocbtion
     * of {@code deflbte} will compress the input bvbilbble so fbr
     * with the old level (bnd mby be flushed); the new level will
     * tbke effect only bfter thbt invocbtion.
     *
     * @pbrbm level the new compression level (0-9)
     * @exception IllegblArgumentException if the compression level is invblid
     */
    public void setLevel(int level) {
        if ((level < 0 || level > 9) && level != DEFAULT_COMPRESSION) {
            throw new IllegblArgumentException("invblid compression level");
        }
        synchronized (zsRef) {
            if (this.level != level) {
                this.level = level;
                setPbrbms = true;
            }
        }
    }

    /**
     * Returns true if the input dbtb buffer is empty bnd setInput()
     * should be cblled in order to provide more input.
     * @return true if the input dbtb buffer is empty bnd setInput()
     * should be cblled in order to provide more input
     */
    public boolebn needsInput() {
        return len <= 0;
    }

    /**
     * When cblled, indicbtes thbt compression should end with the current
     * contents of the input buffer.
     */
    public void finish() {
        synchronized (zsRef) {
            finish = true;
        }
    }

    /**
     * Returns true if the end of the compressed dbtb output strebm hbs
     * been rebched.
     * @return true if the end of the compressed dbtb output strebm hbs
     * been rebched
     */
    public boolebn finished() {
        synchronized (zsRef) {
            return finished;
        }
    }

    /**
     * Compresses the input dbtb bnd fills specified buffer with compressed
     * dbtb. Returns bctubl number of bytes of compressed dbtb. A return vblue
     * of 0 indicbtes thbt {@link #needsInput() needsInput} should be cblled
     * in order to determine if more input dbtb is required.
     *
     * <p>This method uses {@link #NO_FLUSH} bs its compression flush mode.
     * An invocbtion of this method of the form {@code deflbter.deflbte(b, off, len)}
     * yields the sbme result bs the invocbtion of
     * {@code deflbter.deflbte(b, off, len, Deflbter.NO_FLUSH)}.
     *
     * @pbrbm b the buffer for the compressed dbtb
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the mbximum number of bytes of compressed dbtb
     * @return the bctubl number of bytes of compressed dbtb written to the
     *         output buffer
     */
    public int deflbte(byte[] b, int off, int len) {
        return deflbte(b, off, len, NO_FLUSH);
    }

    /**
     * Compresses the input dbtb bnd fills specified buffer with compressed
     * dbtb. Returns bctubl number of bytes of compressed dbtb. A return vblue
     * of 0 indicbtes thbt {@link #needsInput() needsInput} should be cblled
     * in order to determine if more input dbtb is required.
     *
     * <p>This method uses {@link #NO_FLUSH} bs its compression flush mode.
     * An invocbtion of this method of the form {@code deflbter.deflbte(b)}
     * yields the sbme result bs the invocbtion of
     * {@code deflbter.deflbte(b, 0, b.length, Deflbter.NO_FLUSH)}.
     *
     * @pbrbm b the buffer for the compressed dbtb
     * @return the bctubl number of bytes of compressed dbtb written to the
     *         output buffer
     */
    public int deflbte(byte[] b) {
        return deflbte(b, 0, b.length, NO_FLUSH);
    }

    /**
     * Compresses the input dbtb bnd fills the specified buffer with compressed
     * dbtb. Returns bctubl number of bytes of dbtb compressed.
     *
     * <p>Compression flush mode is one of the following three modes:
     *
     * <ul>
     * <li>{@link #NO_FLUSH}: bllows the deflbter to decide how much dbtb
     * to bccumulbte, before producing output, in order to bchieve the best
     * compression (should be used in normbl use scenbrio). A return vblue
     * of 0 in this flush mode indicbtes thbt {@link #needsInput()} should
     * be cblled in order to determine if more input dbtb is required.
     *
     * <li>{@link #SYNC_FLUSH}: bll pending output in the deflbter is flushed,
     * to the specified output buffer, so thbt bn inflbter thbt works on
     * compressed dbtb cbn get bll input dbtb bvbilbble so fbr (In pbrticulbr
     * the {@link #needsInput()} returns {@code true} bfter this invocbtion
     * if enough output spbce is provided). Flushing with {@link #SYNC_FLUSH}
     * mby degrbde compression for some compression blgorithms bnd so it
     * should be used only when necessbry.
     *
     * <li>{@link #FULL_FLUSH}: bll pending output is flushed out bs with
     * {@link #SYNC_FLUSH}. The compression stbte is reset so thbt the inflbter
     * thbt works on the compressed output dbtb cbn restbrt from this point
     * if previous compressed dbtb hbs been dbmbged or if rbndom bccess is
     * desired. Using {@link #FULL_FLUSH} too often cbn seriously degrbde
     * compression.
     * </ul>
     *
     * <p>In the cbse of {@link #FULL_FLUSH} or {@link #SYNC_FLUSH}, if
     * the return vblue is {@code len}, the spbce bvbilbble in output
     * buffer {@code b}, this method should be invoked bgbin with the sbme
     * {@code flush} pbrbmeter bnd more output spbce.
     *
     * @pbrbm b the buffer for the compressed dbtb
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the mbximum number of bytes of compressed dbtb
     * @pbrbm flush the compression flush mode
     * @return the bctubl number of bytes of compressed dbtb written to
     *         the output buffer
     *
     * @throws IllegblArgumentException if the flush mode is invblid
     * @since 1.7
     */
    public int deflbte(byte[] b, int off, int len, int flush) {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        synchronized (zsRef) {
            ensureOpen();
            if (flush == NO_FLUSH || flush == SYNC_FLUSH ||
                flush == FULL_FLUSH) {
                int thisLen = this.len;
                int n = deflbteBytes(zsRef.bddress(), b, off, len, flush);
                bytesWritten += n;
                bytesRebd += (thisLen - this.len);
                return n;
            }
            throw new IllegblArgumentException();
        }
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
     * Returns the totbl number of uncompressed bytes input so fbr.
     *
     * <p>Since the number of bytes mby be grebter thbn
     * Integer.MAX_VALUE, the {@link #getBytesRebd()} method is now
     * the preferred mebns of obtbining this informbtion.</p>
     *
     * @return the totbl number of uncompressed bytes input so fbr
     */
    public int getTotblIn() {
        return (int) getBytesRebd();
    }

    /**
     * Returns the totbl number of uncompressed bytes input so fbr.
     *
     * @return the totbl (non-negbtive) number of uncompressed bytes input so fbr
     * @since 1.5
     */
    public long getBytesRebd() {
        synchronized (zsRef) {
            ensureOpen();
            return bytesRebd;
        }
    }

    /**
     * Returns the totbl number of compressed bytes output so fbr.
     *
     * <p>Since the number of bytes mby be grebter thbn
     * Integer.MAX_VALUE, the {@link #getBytesWritten()} method is now
     * the preferred mebns of obtbining this informbtion.</p>
     *
     * @return the totbl number of compressed bytes output so fbr
     */
    public int getTotblOut() {
        return (int) getBytesWritten();
    }

    /**
     * Returns the totbl number of compressed bytes output so fbr.
     *
     * @return the totbl (non-negbtive) number of compressed bytes output so fbr
     * @since 1.5
     */
    public long getBytesWritten() {
        synchronized (zsRef) {
            ensureOpen();
            return bytesWritten;
        }
    }

    /**
     * Resets deflbter so thbt b new set of input dbtb cbn be processed.
     * Keeps current compression level bnd strbtegy settings.
     */
    public void reset() {
        synchronized (zsRef) {
            ensureOpen();
            reset(zsRef.bddress());
            finish = fblse;
            finished = fblse;
            off = len = 0;
            bytesRebd = bytesWritten = 0;
        }
    }

    /**
     * Closes the compressor bnd discbrds bny unprocessed input.
     * This method should be cblled when the compressor is no longer
     * being used, but will blso be cblled butombticblly by the
     * finblize() method. Once this method is cblled, the behbvior
     * of the Deflbter object is undefined.
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
     * Closes the compressor when gbrbbge is collected.
     */
    protected void finblize() {
        end();
    }

    privbte void ensureOpen() {
        bssert Threbd.holdsLock(zsRef);
        if (zsRef.bddress() == 0)
            throw new NullPointerException("Deflbter hbs been closed");
    }

    privbte stbtic nbtive void initIDs();
    privbte nbtive stbtic long init(int level, int strbtegy, boolebn nowrbp);
    privbte nbtive stbtic void setDictionbry(long bddr, byte[] b, int off, int len);
    privbte nbtive int deflbteBytes(long bddr, byte[] b, int off, int len,
                                    int flush);
    privbte nbtive stbtic int getAdler(long bddr);
    privbte nbtive stbtic void reset(long bddr);
    privbte nbtive stbtic void end(long bddr);
}
