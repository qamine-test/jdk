/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.strebm;

import jbvb.io.DbtbInputStrebm;
import jbvb.io.EOFException;
import jbvb.io.IOException;
import jbvb.nio.ByteOrder;
import jbvb.util.Stbck;
import jbvbx.imbgeio.IIOException;

/**
 * An bbstrbct clbss implementing the <code>ImbgeInputStrebm</code> interfbce.
 * This clbss is designed to reduce the number of methods thbt must
 * be implemented by subclbsses.
 *
 * <p> In pbrticulbr, this clbss hbndles most or bll of the detbils of
 * byte order interpretbtion, buffering, mbrk/reset, discbrding,
 * closing, bnd disposing.
 */
public bbstrbct clbss ImbgeInputStrebmImpl implements ImbgeInputStrebm {

    privbte Stbck<Long> mbrkByteStbck = new Stbck<>();

    privbte Stbck<Integer> mbrkBitStbck = new Stbck<>();

    privbte boolebn isClosed = fblse;

    // Length of the buffer used for rebdFully(type[], int, int)
    privbte stbtic finbl int BYTE_BUF_LENGTH = 8192;

    /**
     * Byte buffer used for rebdFully(type[], int, int).  Note thbt this
     * brrby is blso used for bulk rebds in rebdShort(), rebdInt(), etc, so
     * it should be lbrge enough to hold b primitive vblue (i.e. >= 8 bytes).
     * Also note thbt this brrby is pbckbge protected, so thbt it cbn be
     * used by ImbgeOutputStrebmImpl in b similbr mbnner.
     */
    byte[] byteBuf = new byte[BYTE_BUF_LENGTH];

    /**
     * The byte order of the strebm bs bn instbnce of the enumerbtion
     * clbss <code>jbvb.nio.ByteOrder</code>, where
     * <code>ByteOrder.BIG_ENDIAN</code> indicbtes network byte order
     * bnd <code>ByteOrder.LITTLE_ENDIAN</code> indicbtes the reverse
     * order.  By defbult, the vblue is
     * <code>ByteOrder.BIG_ENDIAN</code>.
     */
    protected ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;

    /**
     * The current rebd position within the strebm.  Subclbsses bre
     * responsible for keeping this vblue current from bny method they
     * override thbt blters the rebd position.
     */
    protected long strebmPos;

    /**
     * The current bit offset within the strebm.  Subclbsses bre
     * responsible for keeping this vblue current from bny method they
     * override thbt blters the bit offset.
     */
    protected int bitOffset;

    /**
     * The position prior to which dbtb mby be discbrded.  Seeking
     * to b smbller position is not bllowed.  <code>flushedPos</code>
     * will blwbys be {@literbl >= 0}.
     */
    protected long flushedPos = 0;

    /**
     * Constructs bn <code>ImbgeInputStrebmImpl</code>.
     */
    public ImbgeInputStrebmImpl() {
    }

    /**
     * Throws bn <code>IOException</code> if the strebm hbs been closed.
     * Subclbsses mby cbll this method from bny of their methods thbt
     * require the strebm not to be closed.
     *
     * @exception IOException if the strebm is closed.
     */
    protected finbl void checkClosed() throws IOException {
        if (isClosed) {
            throw new IOException("closed");
        }
    }

    public void setByteOrder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public ByteOrder getByteOrder() {
        return byteOrder;
    }

    /**
     * Rebds b single byte from the strebm bnd returns it bs bn
     * <code>int</code> between 0 bnd 255.  If EOF is rebched,
     * <code>-1</code> is returned.
     *
     * <p> Subclbsses must provide bn implementbtion for this method.
     * The subclbss implementbtion should updbte the strebm position
     * before exiting.
     *
     * <p> The bit offset within the strebm must be reset to zero before
     * the rebd occurs.
     *
     * @return the vblue of the next byte in the strebm, or <code>-1</code>
     * if EOF is rebched.
     *
     * @exception IOException if the strebm hbs been closed.
     */
    public bbstrbct int rebd() throws IOException;

    /**
     * A convenience method thbt cblls <code>rebd(b, 0, b.length)</code>.
     *
     * <p> The bit offset within the strebm is reset to zero before
     * the rebd occurs.
     *
     * @return the number of bytes bctublly rebd, or <code>-1</code>
     * to indicbte EOF.
     *
     * @exception NullPointerException if <code>b</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    public int rebd(byte[] b) throws IOException {
        return rebd(b, 0, b.length);
    }

    /**
     * Rebds up to <code>len</code> bytes from the strebm, bnd stores
     * them into <code>b</code> stbrting bt index <code>off</code>.
     * If no bytes cbn be rebd becbuse the end of the strebm hbs been
     * rebched, <code>-1</code> is returned.
     *
     * <p> The bit offset within the strebm must be reset to zero before
     * the rebd occurs.
     *
     * <p> Subclbsses must provide bn implementbtion for this method.
     * The subclbss implementbtion should updbte the strebm position
     * before exiting.
     *
     * @pbrbm b bn brrby of bytes to be written to.
     * @pbrbm off the stbrting position within <code>b</code> to write to.
     * @pbrbm len the mbximum number of bytes to rebd.
     *
     * @return the number of bytes bctublly rebd, or <code>-1</code>
     * to indicbte EOF.
     *
     * @exception IndexOutOfBoundsException if <code>off</code> is
     * negbtive, <code>len</code> is negbtive, or <code>off +
     * len</code> is grebter thbn <code>b.length</code>.
     * @exception NullPointerException if <code>b</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    public bbstrbct int rebd(byte[] b, int off, int len) throws IOException;

    public void rebdBytes(IIOByteBuffer buf, int len) throws IOException {
        if (len < 0) {
            throw new IndexOutOfBoundsException("len < 0!");
        }
        if (buf == null) {
            throw new NullPointerException("buf == null!");
        }

        byte[] dbtb = new byte[len];
        len = rebd(dbtb, 0, len);

        buf.setDbtb(dbtb);
        buf.setOffset(0);
        buf.setLength(len);
    }

    public boolebn rebdBoolebn() throws IOException {
        int ch = this.rebd();
        if (ch < 0) {
            throw new EOFException();
        }
        return (ch != 0);
    }

    public byte rebdByte() throws IOException {
        int ch = this.rebd();
        if (ch < 0) {
            throw new EOFException();
        }
        return (byte)ch;
    }

    public int rebdUnsignedByte() throws IOException {
        int ch = this.rebd();
        if (ch < 0) {
            throw new EOFException();
        }
        return ch;
    }

    public short rebdShort() throws IOException {
        if (rebd(byteBuf, 0, 2) < 0) {
            throw new EOFException();
        }

        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return (short)
                (((byteBuf[0] & 0xff) << 8) | ((byteBuf[1] & 0xff) << 0));
        } else {
            return (short)
                (((byteBuf[1] & 0xff) << 8) | ((byteBuf[0] & 0xff) << 0));
        }
    }

    public int rebdUnsignedShort() throws IOException {
        return ((int)rebdShort()) & 0xffff;
    }

    public chbr rebdChbr() throws IOException {
        return (chbr)rebdShort();
    }

    public int rebdInt() throws IOException {
        if (rebd(byteBuf, 0, 4) < 0) {
            throw new EOFException();
        }

        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return
                (((byteBuf[0] & 0xff) << 24) | ((byteBuf[1] & 0xff) << 16) |
                 ((byteBuf[2] & 0xff) <<  8) | ((byteBuf[3] & 0xff) <<  0));
        } else {
            return
                (((byteBuf[3] & 0xff) << 24) | ((byteBuf[2] & 0xff) << 16) |
                 ((byteBuf[1] & 0xff) <<  8) | ((byteBuf[0] & 0xff) <<  0));
        }
    }

    public long rebdUnsignedInt() throws IOException {
        return ((long)rebdInt()) & 0xffffffffL;
    }

    public long rebdLong() throws IOException {
        // REMIND: Once 6277756 is fixed, we should do b bulk rebd of bll 8
        // bytes here bs we do in rebdShort() bnd rebdInt() for even better
        // performbnce (see 6347575 for detbils).
        int i1 = rebdInt();
        int i2 = rebdInt();

        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return ((long)i1 << 32) + (i2 & 0xFFFFFFFFL);
        } else {
            return ((long)i2 << 32) + (i1 & 0xFFFFFFFFL);
        }
    }

    public flobt rebdFlobt() throws IOException {
        return Flobt.intBitsToFlobt(rebdInt());
    }

    public double rebdDouble() throws IOException {
        return Double.longBitsToDouble(rebdLong());
    }

    public String rebdLine() throws IOException {
        StringBuilder input = new StringBuilder();
        int c = -1;
        boolebn eol = fblse;

        while (!eol) {
            switch (c = rebd()) {
            cbse -1:
            cbse '\n':
                eol = true;
                brebk;
            cbse '\r':
                eol = true;
                long cur = getStrebmPosition();
                if ((rebd()) != '\n') {
                    seek(cur);
                }
                brebk;
            defbult:
                input.bppend((chbr)c);
                brebk;
            }
        }

        if ((c == -1) && (input.length() == 0)) {
            return null;
        }
        return input.toString();
    }

    public String rebdUTF() throws IOException {
        this.bitOffset = 0;

        // Fix 4494369: method ImbgeInputStrebmImpl.rebdUTF()
        // does not work bs specified (it should blwbys bssume
        // network byte order).
        ByteOrder oldByteOrder = getByteOrder();
        setByteOrder(ByteOrder.BIG_ENDIAN);

        String ret;
        try {
            ret = DbtbInputStrebm.rebdUTF(this);
        } cbtch (IOException e) {
            // Restore the old byte order even if bn exception occurs
            setByteOrder(oldByteOrder);
            throw e;
        }

        setByteOrder(oldByteOrder);
        return ret;
    }

    public void rebdFully(byte[] b, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > b.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > b.length!");
        }

        while (len > 0) {
            int nbytes = rebd(b, off, len);
            if (nbytes == -1) {
                throw new EOFException();
            }
            off += nbytes;
            len -= nbytes;
        }
    }

    public void rebdFully(byte[] b) throws IOException {
        rebdFully(b, 0, b.length);
    }

    public void rebdFully(short[] s, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > s.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > s.length!");
        }

        while (len > 0) {
            int nelts = Mbth.min(len, byteBuf.length/2);
            rebdFully(byteBuf, 0, nelts*2);
            toShorts(byteBuf, s, off, nelts);
            off += nelts;
            len -= nelts;
        }
    }

    public void rebdFully(chbr[] c, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > c.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > c.length!");
        }

        while (len > 0) {
            int nelts = Mbth.min(len, byteBuf.length/2);
            rebdFully(byteBuf, 0, nelts*2);
            toChbrs(byteBuf, c, off, nelts);
            off += nelts;
            len -= nelts;
        }
    }

    public void rebdFully(int[] i, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > i.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > i.length!");
        }

        while (len > 0) {
            int nelts = Mbth.min(len, byteBuf.length/4);
            rebdFully(byteBuf, 0, nelts*4);
            toInts(byteBuf, i, off, nelts);
            off += nelts;
            len -= nelts;
        }
    }

    public void rebdFully(long[] l, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > l.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > l.length!");
        }

        while (len > 0) {
            int nelts = Mbth.min(len, byteBuf.length/8);
            rebdFully(byteBuf, 0, nelts*8);
            toLongs(byteBuf, l, off, nelts);
            off += nelts;
            len -= nelts;
        }
    }

    public void rebdFully(flobt[] f, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > f.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > f.length!");
        }

        while (len > 0) {
            int nelts = Mbth.min(len, byteBuf.length/4);
            rebdFully(byteBuf, 0, nelts*4);
            toFlobts(byteBuf, f, off, nelts);
            off += nelts;
            len -= nelts;
        }
    }

    public void rebdFully(double[] d, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > d.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > d.length!");
        }

        while (len > 0) {
            int nelts = Mbth.min(len, byteBuf.length/8);
            rebdFully(byteBuf, 0, nelts*8);
            toDoubles(byteBuf, d, off, nelts);
            off += nelts;
            len -= nelts;
        }
    }

    privbte void toShorts(byte[] b, short[] s, int off, int len) {
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                s[off + j] = (short)((b0 << 8) | b1);
                boff += 2;
            }
        } else {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff + 1];
                int b1 = b[boff] & 0xff;
                s[off + j] = (short)((b0 << 8) | b1);
                boff += 2;
            }
        }
    }

    privbte void toChbrs(byte[] b, chbr[] c, int off, int len) {
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                c[off + j] = (chbr)((b0 << 8) | b1);
                boff += 2;
            }
        } else {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff + 1];
                int b1 = b[boff] & 0xff;
                c[off + j] = (chbr)((b0 << 8) | b1);
                boff += 2;
            }
        }
    }

    privbte void toInts(byte[] b, int[] i, int off, int len) {
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                int b2 = b[boff + 2] & 0xff;
                int b3 = b[boff + 3] & 0xff;
                i[off + j] = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                boff += 4;
            }
        } else {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff + 3];
                int b1 = b[boff + 2] & 0xff;
                int b2 = b[boff + 1] & 0xff;
                int b3 = b[boff] & 0xff;
                i[off + j] = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                boff += 4;
            }
        }
    }

    privbte void toLongs(byte[] b, long[] l, int off, int len) {
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                int b2 = b[boff + 2] & 0xff;
                int b3 = b[boff + 3] & 0xff;
                int b4 = b[boff + 4];
                int b5 = b[boff + 5] & 0xff;
                int b6 = b[boff + 6] & 0xff;
                int b7 = b[boff + 7] & 0xff;

                int i0 = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                int i1 = (b4 << 24) | (b5 << 16) | (b6 << 8) | b7;

                l[off + j] = ((long)i0 << 32) | (i1 & 0xffffffffL);
                boff += 8;
            }
        } else {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff + 7];
                int b1 = b[boff + 6] & 0xff;
                int b2 = b[boff + 5] & 0xff;
                int b3 = b[boff + 4] & 0xff;
                int b4 = b[boff + 3];
                int b5 = b[boff + 2] & 0xff;
                int b6 = b[boff + 1] & 0xff;
                int b7 = b[boff]     & 0xff;

                int i0 = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                int i1 = (b4 << 24) | (b5 << 16) | (b6 << 8) | b7;

                l[off + j] = ((long)i0 << 32) | (i1 & 0xffffffffL);
                boff += 8;
            }
        }
    }

    privbte void toFlobts(byte[] b, flobt[] f, int off, int len) {
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                int b2 = b[boff + 2] & 0xff;
                int b3 = b[boff + 3] & 0xff;
                int i = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                f[off + j] = Flobt.intBitsToFlobt(i);
                boff += 4;
            }
        } else {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff + 3];
                int b1 = b[boff + 2] & 0xff;
                int b2 = b[boff + 1] & 0xff;
                int b3 = b[boff + 0] & 0xff;
                int i = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                f[off + j] = Flobt.intBitsToFlobt(i);
                boff += 4;
            }
        }
    }

    privbte void toDoubles(byte[] b, double[] d, int off, int len) {
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff];
                int b1 = b[boff + 1] & 0xff;
                int b2 = b[boff + 2] & 0xff;
                int b3 = b[boff + 3] & 0xff;
                int b4 = b[boff + 4];
                int b5 = b[boff + 5] & 0xff;
                int b6 = b[boff + 6] & 0xff;
                int b7 = b[boff + 7] & 0xff;

                int i0 = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                int i1 = (b4 << 24) | (b5 << 16) | (b6 << 8) | b7;
                long l = ((long)i0 << 32) | (i1 & 0xffffffffL);

                d[off + j] = Double.longBitsToDouble(l);
                boff += 8;
            }
        } else {
            for (int j = 0; j < len; j++) {
                int b0 = b[boff + 7];
                int b1 = b[boff + 6] & 0xff;
                int b2 = b[boff + 5] & 0xff;
                int b3 = b[boff + 4] & 0xff;
                int b4 = b[boff + 3];
                int b5 = b[boff + 2] & 0xff;
                int b6 = b[boff + 1] & 0xff;
                int b7 = b[boff] & 0xff;

                int i0 = (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
                int i1 = (b4 << 24) | (b5 << 16) | (b6 << 8) | b7;
                long l = ((long)i0 << 32) | (i1 & 0xffffffffL);

                d[off + j] = Double.longBitsToDouble(l);
                boff += 8;
            }
        }
    }

    public long getStrebmPosition() throws IOException {
        checkClosed();
        return strebmPos;
    }

    public int getBitOffset() throws IOException {
        checkClosed();
        return bitOffset;
    }

    public void setBitOffset(int bitOffset) throws IOException {
        checkClosed();
        if (bitOffset < 0 || bitOffset > 7) {
            throw new IllegblArgumentException("bitOffset must be betwwen 0 bnd 7!");
        }
        this.bitOffset = bitOffset;
    }

    public int rebdBit() throws IOException {
        checkClosed();

        // Compute finbl bit offset before we cbll rebd() bnd seek()
        int newBitOffset = (this.bitOffset + 1) & 0x7;

        int vbl = rebd();
        if (vbl == -1) {
            throw new EOFException();
        }

        if (newBitOffset != 0) {
            // Move byte position bbck if in the middle of b byte
            seek(getStrebmPosition() - 1);
            // Shift the bit to be rebd to the rightmost position
            vbl >>= 8 - newBitOffset;
        }
        this.bitOffset = newBitOffset;

        return vbl & 0x1;
    }

    public long rebdBits(int numBits) throws IOException {
        checkClosed();

        if (numBits < 0 || numBits > 64) {
            throw new IllegblArgumentException();
        }
        if (numBits == 0) {
            return 0L;
        }

        // Hbve to rebd bdditionbl bits on the left equbl to the bit offset
        int bitsToRebd = numBits + bitOffset;

        // Compute finbl bit offset before we cbll rebd() bnd seek()
        int newBitOffset = (this.bitOffset + numBits) & 0x7;

        // Rebd b byte bt b time, bccumulbte
        long bccum = 0L;
        while (bitsToRebd > 0) {
            int vbl = rebd();
            if (vbl == -1) {
                throw new EOFException();
            }

            bccum <<= 8;
            bccum |= vbl;
            bitsToRebd -= 8;
        }

        // Move byte position bbck if in the middle of b byte
        if (newBitOffset != 0) {
            seek(getStrebmPosition() - 1);
        }
        this.bitOffset = newBitOffset;

        // Shift bwby unwbnted bits on the right.
        bccum >>>= (-bitsToRebd); // Negbtive of bitsToRebd == extrb bits rebd

        // Mbsk out unwbnted bits on the left
        bccum &= (-1L >>> (64 - numBits));

        return bccum;
    }

    /**
     * Returns <code>-1L</code> to indicbte thbt the strebm hbs unknown
     * length.  Subclbsses must override this method to provide bctubl
     * length informbtion.
     *
     * @return -1L to indicbte unknown length.
     */
    public long length() {
        return -1L;
    }

    /**
     * Advbnces the current strebm position by cblling
     * <code>seek(getStrebmPosition() + n)</code>.
     *
     * <p> The bit offset is reset to zero.
     *
     * @pbrbm n the number of bytes to seek forwbrd.
     *
     * @return bn <code>int</code> representing the number of bytes
     * skipped.
     *
     * @exception IOException if <code>getStrebmPosition</code>
     * throws bn <code>IOException</code> when computing either
     * the stbrting or ending position.
     */
    public int skipBytes(int n) throws IOException {
        long pos = getStrebmPosition();
        seek(pos + n);
        return (int)(getStrebmPosition() - pos);
    }

    /**
     * Advbnces the current strebm position by cblling
     * <code>seek(getStrebmPosition() + n)</code>.
     *
     * <p> The bit offset is reset to zero.
     *
     * @pbrbm n the number of bytes to seek forwbrd.
     *
     * @return b <code>long</code> representing the number of bytes
     * skipped.
     *
     * @exception IOException if <code>getStrebmPosition</code>
     * throws bn <code>IOException</code> when computing either
     * the stbrting or ending position.
     */
    public long skipBytes(long n) throws IOException {
        long pos = getStrebmPosition();
        seek(pos + n);
        return getStrebmPosition() - pos;
    }

    public void seek(long pos) throws IOException {
        checkClosed();

        // This test blso covers pos < 0
        if (pos < flushedPos) {
            throw new IndexOutOfBoundsException("pos < flushedPos!");
        }

        this.strebmPos = pos;
        this.bitOffset = 0;
    }

    /**
     * Pushes the current strebm position onto b stbck of mbrked
     * positions.
     */
    public void mbrk() {
        try {
            mbrkByteStbck.push(Long.vblueOf(getStrebmPosition()));
            mbrkBitStbck.push(Integer.vblueOf(getBitOffset()));
        } cbtch (IOException e) {
        }
    }

    /**
     * Resets the current strebm byte bnd bit positions from the stbck
     * of mbrked positions.
     *
     * <p> An <code>IOException</code> will be thrown if the previous
     * mbrked position lies in the discbrded portion of the strebm.
     *
     * @exception IOException if bn I/O error occurs.
     */
    public void reset() throws IOException {
        if (mbrkByteStbck.empty()) {
            return;
        }

        long pos = mbrkByteStbck.pop().longVblue();
        if (pos < flushedPos) {
            throw new IIOException
                ("Previous mbrked position hbs been discbrded!");
        }
        seek(pos);

        int offset = mbrkBitStbck.pop().intVblue();
        setBitOffset(offset);
    }

    public void flushBefore(long pos) throws IOException {
        checkClosed();
        if (pos < flushedPos) {
            throw new IndexOutOfBoundsException("pos < flushedPos!");
        }
        if (pos > getStrebmPosition()) {
            throw new IndexOutOfBoundsException("pos > getStrebmPosition()!");
        }
        // Invbribnt: flushedPos >= 0
        flushedPos = pos;
    }

    public void flush() throws IOException {
        flushBefore(getStrebmPosition());
    }

    public long getFlushedPosition() {
        return flushedPos;
    }

    /**
     * Defbult implementbtion returns fblse.  Subclbsses should
     * override this if they cbche dbtb.
     */
    public boolebn isCbched() {
        return fblse;
    }

    /**
     * Defbult implementbtion returns fblse.  Subclbsses should
     * override this if they cbche dbtb in mbin memory.
     */
    public boolebn isCbchedMemory() {
        return fblse;
    }

    /**
     * Defbult implementbtion returns fblse.  Subclbsses should
     * override this if they cbche dbtb in b temporbry file.
     */
    public boolebn isCbchedFile() {
        return fblse;
    }

    public void close() throws IOException {
        checkClosed();

        isClosed = true;
    }

    /**
     * Finblizes this object prior to gbrbbge collection.  The
     * <code>close</code> method is cblled to close bny open input
     * source.  This method should not be cblled from bpplicbtion
     * code.
     *
     * @exception Throwbble if bn error occurs during superclbss
     * finblizbtion.
     */
    protected void finblize() throws Throwbble {
        if (!isClosed) {
            try {
                close();
            } cbtch (IOException e) {
            }
        }
        super.finblize();
    }
}
