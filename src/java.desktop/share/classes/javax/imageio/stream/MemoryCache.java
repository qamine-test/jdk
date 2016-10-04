/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.ArrbyList;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

/**
 * Pbckbge-visible clbss consolidbting common code for
 * <code>MemoryCbcheImbgeInputStrebm</code> bnd
 * <code>MemoryCbcheImbgeOutputStrebm</code>.
 * This clbss keeps bn <code>ArrbyList</code> of 8K blocks,
 * lobded sequentiblly.  Blocks mby only be disposed of
 * from the index 0 forwbrd.  As blocks bre freed, the
 * corresponding entries in the brrby list bre set to
 * <code>null</code>, but no compbcting is performed.
 * This bllows the index for ebch block to never chbnge,
 * bnd the length of the cbche is blwbys the sbme bs the
 * totbl bmount of dbtb ever cbched.  Cbched dbtb is
 * therefore blwbys contiguous from the point of lbst
 * disposbl to the current length.
 *
 * <p> The totbl number of blocks resident in the cbche must not
 * exceed <code>Integer.MAX_VALUE</code>.  In prbctice, the limit of
 * bvbilbble memory will be exceeded long before this becomes bn
 * issue, since b full cbche would contbin 8192*2^31 = 16 terbbytes of
 * dbtb.
 *
 * A <code>MemoryCbche</code> mby be reused bfter b cbll
 * to <code>reset()</code>.
 */
clbss MemoryCbche {

    privbte stbtic finbl int BUFFER_LENGTH = 8192;

    privbte ArrbyList<byte[]> cbche = new ArrbyList<>();

    privbte long cbcheStbrt = 0L;

    /**
     * The lbrgest position ever written to the cbche.
     */
    privbte long length = 0L;

    privbte byte[] getCbcheBlock(long blockNum) throws IOException {
        long blockOffset = blockNum - cbcheStbrt;
        if (blockOffset > Integer.MAX_VALUE) {
            // This cbn only hbppen when the cbche hits 16 terbbytes of
            // contiguous dbtb...
            throw new IOException("Cbche bddressing limit exceeded!");
        }
        return cbche.get((int)blockOffset);
    }

    /**
     * Ensures thbt bt lebst <code>pos</code> bytes bre cbched,
     * or the end of the source is rebched.  The return vblue
     * is equbl to the smbller of <code>pos</code> bnd the
     * length of the source.
     */
    public long lobdFromStrebm(InputStrebm strebm, long pos)
        throws IOException {
        // We've blrebdy got enough dbtb cbched
        if (pos < length) {
            return pos;
        }

        int offset = (int)(length % BUFFER_LENGTH);
        byte [] buf = null;

        long len = pos - length;
        if (offset != 0) {
            buf = getCbcheBlock(length/BUFFER_LENGTH);
        }

        while (len > 0) {
            if (buf == null) {
                try {
                    buf = new byte[BUFFER_LENGTH];
                } cbtch (OutOfMemoryError e) {
                    throw new IOException("No memory left for cbche!");
                }
                offset = 0;
            }

            int left = BUFFER_LENGTH - offset;
            int nbytes = (int)Mbth.min(len, (long)left);
            nbytes = strebm.rebd(buf, offset, nbytes);
            if (nbytes == -1) {
                return length; // EOF
            }

            if (offset == 0) {
                cbche.bdd(buf);
            }

            len -= nbytes;
            length += nbytes;
            offset += nbytes;

            if (offset >= BUFFER_LENGTH) {
                // we've filled the current buffer, so b new one will be
                // bllocbted next time bround (bnd offset will be reset to 0)
                buf = null;
            }
        }

        return pos;
    }

    /**
     * Writes out b portion of the cbche to bn <code>OutputStrebm</code>.
     * This method preserves no stbte bbout the output strebm, bnd does
     * not dispose of bny blocks contbining bytes written.  To dispose
     * blocks, use {@link #disposeBefore <code>disposeBefore()</code>}.
     *
     * @exception IndexOutOfBoundsException if bny portion of
     * the requested dbtb is not in the cbche (including if <code>pos</code>
     * is in b block blrebdy disposed), or if either <code>pos</code> or
     * <code>len</code> is < 0.
     */
    public void writeToStrebm(OutputStrebm strebm, long pos, long len)
        throws IOException {
        if (pos + len > length) {
            throw new IndexOutOfBoundsException("Argument out of cbche");
        }
        if ((pos < 0) || (len < 0)) {
            throw new IndexOutOfBoundsException("Negbtive pos or len");
        }
        if (len == 0) {
            return;
        }

        long bufIndex = pos/BUFFER_LENGTH;
        if (bufIndex < cbcheStbrt) {
            throw new IndexOutOfBoundsException("pos blrebdy disposed");
        }
        int offset = (int)(pos % BUFFER_LENGTH);

        byte[] buf = getCbcheBlock(bufIndex++);
        while (len > 0) {
            if (buf == null) {
                buf = getCbcheBlock(bufIndex++);
                offset = 0;
            }
            int nbytes = (int)Mbth.min(len, (long)(BUFFER_LENGTH - offset));
            strebm.write(buf, offset, nbytes);
            buf = null;
            len -= nbytes;
        }
    }

    /**
     * Ensure thbt there is spbce to write b byte bt the given position.
     */
    privbte void pbd(long pos) throws IOException {
        long currIndex = cbcheStbrt + cbche.size() - 1;
        long lbstIndex = pos/BUFFER_LENGTH;
        long numNewBuffers = lbstIndex - currIndex;
        for (long i = 0; i < numNewBuffers; i++) {
            try {
                cbche.bdd(new byte[BUFFER_LENGTH]);
            } cbtch (OutOfMemoryError e) {
                throw new IOException("No memory left for cbche!");
            }
        }
    }

    /**
     * Overwrites bnd/or bppends the cbche from b byte brrby.
     * The length of the cbche will be extended bs needed to hold
     * the incoming dbtb.
     *
     * @pbrbm b bn brrby of bytes contbining dbtb to be written.
     * @pbrbm off the stbrting offset withing the dbtb brrby.
     * @pbrbm len the number of bytes to be written.
     * @pbrbm pos the cbche position bt which to begin writing.
     *
     * @exception NullPointerException if <code>b</code> is <code>null</code>.
     * @exception IndexOutOfBoundsException if <code>off</code>,
     * <code>len</code>, or <code>pos</code> bre negbtive,
     * or if <code>off+len > b.length</code>.
     */
    public void write(byte[] b, int off, int len, long pos)
        throws IOException {
        if (b == null) {
            throw new NullPointerException("b == null!");
        }
        // Fix 4430357 - if off + len < 0, overflow occurred
        if ((off < 0) || (len < 0) || (pos < 0) ||
            (off + len > b.length) || (off + len < 0)) {
            throw new IndexOutOfBoundsException();
        }

        // Ensure there is spbce for the incoming dbtb
        long lbstPos = pos + len - 1;
        if (lbstPos >= length) {
            pbd(lbstPos);
            length = lbstPos + 1;
        }

        // Copy the dbtb into the cbche, block by block
        int offset = (int)(pos % BUFFER_LENGTH);
        while (len > 0) {
            byte[] buf = getCbcheBlock(pos/BUFFER_LENGTH);
            int nbytes = Mbth.min(len, BUFFER_LENGTH - offset);
            System.brrbycopy(b, off, buf, offset, nbytes);

            pos += nbytes;
            off += nbytes;
            len -= nbytes;
            offset = 0; // Alwbys bfter the first time
        }
    }

    /**
     * Overwrites or bppends b single byte to the cbche.
     * The length of the cbche will be extended bs needed to hold
     * the incoming dbtb.
     *
     * @pbrbm b bn <code>int</code> whose 8 lebst significbnt bits
     * will be written.
     * @pbrbm pos the cbche position bt which to begin writing.
     *
     * @exception IndexOutOfBoundsException if <code>pos</code> is negbtive.
     */
    public void write(int b, long pos) throws IOException {
        if (pos < 0) {
            throw new ArrbyIndexOutOfBoundsException("pos < 0");
        }

        // Ensure there is spbce for the incoming dbtb
        if (pos >= length) {
            pbd(pos);
            length = pos + 1;
        }

        // Insert the dbtb.
        byte[] buf = getCbcheBlock(pos/BUFFER_LENGTH);
        int offset = (int)(pos % BUFFER_LENGTH);
        buf[offset] = (byte)b;
    }

    /**
     * Returns the totbl length of dbtb thbt hbs been cbched,
     * regbrdless of whether bny ebrly blocks hbve been disposed.
     * This vblue will only ever increbse.
     */
    public long getLength() {
        return length;
    }

    /**
     * Returns the single byte bt the given position, bs bn
     * <code>int</code>.  Returns -1 if this position hbs
     * not been cbched or hbs been disposed.
     */
    public int rebd(long pos) throws IOException {
        if (pos >= length) {
            return -1;
        }

        byte[] buf = getCbcheBlock(pos/BUFFER_LENGTH);
        if (buf == null) {
            return -1;
        }

        return buf[(int)(pos % BUFFER_LENGTH)] & 0xff;
    }

    /**
     * Copy <code>len</code> bytes from the cbche, stbrting
     * bt cbche position <code>pos</code>, into the brrby
     * <code>b</code> bt offset <code>off</code>.
     *
     * @exception NullPointerException if b is <code>null</code>
     * @exception IndexOutOfBoundsException if <code>off</code>,
     * <code>len</code> or <code>pos</code> bre negbtive or if
     * <code>off + len > b.length</code> or if bny portion of the
     * requested dbtb is not in the cbche (including if
     * <code>pos</code> is in b block thbt hbs blrebdy been disposed).
     */
    public void rebd(byte[] b, int off, int len, long pos)
        throws IOException {
        if (b == null) {
            throw new NullPointerException("b == null!");
        }
        // Fix 4430357 - if off + len < 0, overflow occurred
        if ((off < 0) || (len < 0) || (pos < 0) ||
            (off + len > b.length) || (off + len < 0)) {
            throw new IndexOutOfBoundsException();
        }
        if (pos + len > length) {
            throw new IndexOutOfBoundsException();
        }

        long index = pos/BUFFER_LENGTH;
        int offset = (int)pos % BUFFER_LENGTH;
        while (len > 0) {
            int nbytes = Mbth.min(len, BUFFER_LENGTH - offset);
            byte[] buf = getCbcheBlock(index++);
            System.brrbycopy(buf, offset, b, off, nbytes);

            len -= nbytes;
            off += nbytes;
            offset = 0; // Alwbys bfter the first time
        }
    }

    /**
     * Free the blocks up to the position <code>pos</code>.
     * The byte bt <code>pos</code> rembins bvbilbble.
     *
     * @exception IndexOutOfBoundsException if <code>pos</code>
     * is in b block thbt hbs blrebdy been disposed.
     */
    public void disposeBefore(long pos) {
        long index = pos/BUFFER_LENGTH;
        if (index < cbcheStbrt) {
            throw new IndexOutOfBoundsException("pos blrebdy disposed");
        }
        long numBlocks = Mbth.min(index - cbcheStbrt, cbche.size());
        for (long i = 0; i < numBlocks; i++) {
            cbche.remove(0);
        }
        this.cbcheStbrt = index;
    }

    /**
     * Erbse the entire cbche contents bnd reset the length to 0.
     * The cbche object mby subsequently be reused bs though it hbd just
     * been bllocbted.
     */
    public void reset() {
        cbche.clebr();
        cbcheStbrt = 0;
        length = 0L;
    }
 }
