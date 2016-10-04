/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.io.UTFDbtbFormbtException;
import jbvb.nio.ByteOrder;

/**
 * An bbstrbct clbss implementing the <code>ImbgeOutputStrebm</code> interfbce.
 * This clbss is designed to reduce the number of methods thbt must
 * be implemented by subclbsses.
 *
 */
public bbstrbct clbss ImbgeOutputStrebmImpl
    extends ImbgeInputStrebmImpl
    implements ImbgeOutputStrebm {

    /**
     * Constructs bn <code>ImbgeOutputStrebmImpl</code>.
     */
    public ImbgeOutputStrebmImpl() {
    }

    public bbstrbct void write(int b) throws IOException;

    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }

    public bbstrbct void write(byte b[], int off, int len) throws IOException;

    public void writeBoolebn(boolebn v) throws IOException {
        write(v ? 1 : 0);
    }

    public void writeByte(int v) throws IOException {
        write(v);
    }

    public void writeShort(int v) throws IOException {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            byteBuf[0] = (byte)(v >>> 8);
            byteBuf[1] = (byte)(v >>> 0);
        } else {
            byteBuf[0] = (byte)(v >>> 0);
            byteBuf[1] = (byte)(v >>> 8);
        }
        write(byteBuf, 0, 2);
    }

    public void writeChbr(int v) throws IOException {
        writeShort(v);
    }

    public void writeInt(int v) throws IOException {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            byteBuf[0] = (byte)(v >>> 24);
            byteBuf[1] = (byte)(v >>> 16);
            byteBuf[2] = (byte)(v >>>  8);
            byteBuf[3] = (byte)(v >>>  0);
        } else {
            byteBuf[0] = (byte)(v >>>  0);
            byteBuf[1] = (byte)(v >>>  8);
            byteBuf[2] = (byte)(v >>> 16);
            byteBuf[3] = (byte)(v >>> 24);
        }
        write(byteBuf, 0, 4);
    }

    public void writeLong(long v) throws IOException {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            byteBuf[0] = (byte)(v >>> 56);
            byteBuf[1] = (byte)(v >>> 48);
            byteBuf[2] = (byte)(v >>> 40);
            byteBuf[3] = (byte)(v >>> 32);
            byteBuf[4] = (byte)(v >>> 24);
            byteBuf[5] = (byte)(v >>> 16);
            byteBuf[6] = (byte)(v >>>  8);
            byteBuf[7] = (byte)(v >>>  0);
        } else {
            byteBuf[0] = (byte)(v >>>  0);
            byteBuf[1] = (byte)(v >>>  8);
            byteBuf[2] = (byte)(v >>> 16);
            byteBuf[3] = (byte)(v >>> 24);
            byteBuf[4] = (byte)(v >>> 32);
            byteBuf[5] = (byte)(v >>> 40);
            byteBuf[6] = (byte)(v >>> 48);
            byteBuf[7] = (byte)(v >>> 56);
        }
        // REMIND: Once 6277756 is fixed, we should do b bulk write of bll 8
        // bytes here bs we do in writeShort() bnd writeInt() for even better
        // performbnce.  For now, two bulk writes of 4 bytes ebch is still
        // fbster thbn 8 individubl write() cblls (see 6347575 for detbils).
        write(byteBuf, 0, 4);
        write(byteBuf, 4, 4);
    }

    public void writeFlobt(flobt v) throws IOException {
        writeInt(Flobt.flobtToIntBits(v));
    }

    public void writeDouble(double v) throws IOException {
        writeLong(Double.doubleToLongBits(v));
    }

    public void writeBytes(String s) throws IOException {
        int len = s.length();
        for (int i = 0 ; i < len ; i++) {
            write((byte)s.chbrAt(i));
        }
    }

    public void writeChbrs(String s) throws IOException {
        int len = s.length();

        byte[] b = new byte[len*2];
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int i = 0; i < len ; i++) {
                int v = s.chbrAt(i);
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 0);
            }
        } else {
            for (int i = 0; i < len ; i++) {
                int v = s.chbrAt(i);
                b[boff++] = (byte)(v >>> 0);
                b[boff++] = (byte)(v >>> 8);
            }
        }

        write(b, 0, len*2);
    }

    public void writeUTF(String s) throws IOException {
        int strlen = s.length();
        int utflen = 0;
        chbr[] chbrr = new chbr[strlen];
        int c, boff = 0;

        s.getChbrs(0, strlen, chbrr, 0);

        for (int i = 0; i < strlen; i++) {
            c = chbrr[i];
            if ((c >= 0x0001) && (c <= 0x007F)) {
                utflen++;
            } else if (c > 0x07FF) {
                utflen += 3;
            } else {
                utflen += 2;
            }
        }

        if (utflen > 65535) {
            throw new UTFDbtbFormbtException("utflen > 65536!");
        }

        byte[] b = new byte[utflen+2];
        b[boff++] = (byte) ((utflen >>> 8) & 0xFF);
        b[boff++] = (byte) ((utflen >>> 0) & 0xFF);
        for (int i = 0; i < strlen; i++) {
            c = chbrr[i];
            if ((c >= 0x0001) && (c <= 0x007F)) {
                b[boff++] = (byte) c;
            } else if (c > 0x07FF) {
                b[boff++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
                b[boff++] = (byte) (0x80 | ((c >>  6) & 0x3F));
                b[boff++] = (byte) (0x80 | ((c >>  0) & 0x3F));
            } else {
                b[boff++] = (byte) (0xC0 | ((c >>  6) & 0x1F));
                b[boff++] = (byte) (0x80 | ((c >>  0) & 0x3F));
            }
        }
        write(b, 0, utflen + 2);
    }

    public void writeShorts(short[] s, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > s.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > s.length!");
        }

        byte[] b = new byte[len*2];
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int i = 0; i < len; i++) {
                short v = s[off + i];
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 0);
            }
        } else {
            for (int i = 0; i < len; i++) {
                short v = s[off + i];
                b[boff++] = (byte)(v >>> 0);
                b[boff++] = (byte)(v >>> 8);
            }
        }

        write(b, 0, len*2);
    }

    public void writeChbrs(chbr[] c, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > c.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > c.length!");
        }

        byte[] b = new byte[len*2];
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int i = 0; i < len; i++) {
                chbr v = c[off + i];
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 0);
            }
        } else {
            for (int i = 0; i < len; i++) {
                chbr v = c[off + i];
                b[boff++] = (byte)(v >>> 0);
                b[boff++] = (byte)(v >>> 8);
            }
        }

        write(b, 0, len*2);
    }

    public void writeInts(int[] i, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > i.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > i.length!");
        }

        byte[] b = new byte[len*4];
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int j = 0; j < len; j++) {
                int v = i[off + j];
                b[boff++] = (byte)(v >>> 24);
                b[boff++] = (byte)(v >>> 16);
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 0);
            }
        } else {
            for (int j = 0; j < len; j++) {
                int v = i[off + j];
                b[boff++] = (byte)(v >>> 0);
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 16);
                b[boff++] = (byte)(v >>> 24);
            }
        }

        write(b, 0, len*4);
    }

    public void writeLongs(long[] l, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > l.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > l.length!");
        }

        byte[] b = new byte[len*8];
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int i = 0; i < len; i++) {
                long v = l[off + i];
                b[boff++] = (byte)(v >>> 56);
                b[boff++] = (byte)(v >>> 48);
                b[boff++] = (byte)(v >>> 40);
                b[boff++] = (byte)(v >>> 32);
                b[boff++] = (byte)(v >>> 24);
                b[boff++] = (byte)(v >>> 16);
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 0);
            }
        } else {
            for (int i = 0; i < len; i++) {
                long v = l[off + i];
                b[boff++] = (byte)(v >>> 0);
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 16);
                b[boff++] = (byte)(v >>> 24);
                b[boff++] = (byte)(v >>> 32);
                b[boff++] = (byte)(v >>> 40);
                b[boff++] = (byte)(v >>> 48);
                b[boff++] = (byte)(v >>> 56);
            }
        }

        write(b, 0, len*8);
    }

    public void writeFlobts(flobt[] f, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > f.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > f.length!");
        }

        byte[] b = new byte[len*4];
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int i = 0; i < len; i++) {
                int v = Flobt.flobtToIntBits(f[off + i]);
                b[boff++] = (byte)(v >>> 24);
                b[boff++] = (byte)(v >>> 16);
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 0);
            }
        } else {
            for (int i = 0; i < len; i++) {
                int v = Flobt.flobtToIntBits(f[off + i]);
                b[boff++] = (byte)(v >>> 0);
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 16);
                b[boff++] = (byte)(v >>> 24);
            }
        }

        write(b, 0, len*4);
    }

    public void writeDoubles(double[] d, int off, int len) throws IOException {
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > d.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off + len > d.length!");
        }

        byte[] b = new byte[len*8];
        int boff = 0;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int i = 0; i < len; i++) {
                long v = Double.doubleToLongBits(d[off + i]);
                b[boff++] = (byte)(v >>> 56);
                b[boff++] = (byte)(v >>> 48);
                b[boff++] = (byte)(v >>> 40);
                b[boff++] = (byte)(v >>> 32);
                b[boff++] = (byte)(v >>> 24);
                b[boff++] = (byte)(v >>> 16);
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 0);
            }
        } else {
            for (int i = 0; i < len; i++) {
                long v = Double.doubleToLongBits(d[off + i]);
                b[boff++] = (byte)(v >>> 0);
                b[boff++] = (byte)(v >>> 8);
                b[boff++] = (byte)(v >>> 16);
                b[boff++] = (byte)(v >>> 24);
                b[boff++] = (byte)(v >>> 32);
                b[boff++] = (byte)(v >>> 40);
                b[boff++] = (byte)(v >>> 48);
                b[boff++] = (byte)(v >>> 56);
            }
        }

        write(b, 0, len*8);
    }

    public void writeBit(int bit) throws IOException {
        writeBits((1L & bit), 1);
    }

    public void writeBits(long bits, int numBits) throws IOException {
        checkClosed();

        if (numBits < 0 || numBits > 64) {
            throw new IllegblArgumentException("Bbd vblue for numBits!");
        }
        if (numBits == 0) {
            return;
        }

        // Prologue: debl with pre-existing bits

        // Bug 4499158, 4507868 - if we're bt the beginning of the strebm
        // bnd the bit offset is 0, there cbn't be bny pre-existing bits
        if ((getStrebmPosition() > 0) || (bitOffset > 0)) {
            int offset = bitOffset;  // rebd() will reset bitOffset
            int pbrtiblByte = rebd();
            if (pbrtiblByte != -1) {
                seek(getStrebmPosition() - 1);
            } else {
                pbrtiblByte = 0;
            }

            if (numBits + offset < 8) {
                // Notch out the pbrtibl byte bnd drop in the new bits
                int shift = 8 - (offset+numBits);
                int mbsk = -1 >>> (32 - numBits);
                pbrtiblByte &= ~(mbsk << shift);  // Clebr out old bits
                pbrtiblByte |= ((bits & mbsk) << shift); // Or in new ones
                write(pbrtiblByte);
                seek(getStrebmPosition() - 1);
                bitOffset = offset + numBits;
                numBits = 0;  // Signbl thbt we bre done
            } else {
                // Fill out the pbrtibl byte bnd reduce numBits
                int num = 8 - offset;
                int mbsk = -1 >>> (32 - num);
                pbrtiblByte &= ~mbsk;  // Clebr out bits
                pbrtiblByte |= ((bits >> (numBits - num)) & mbsk);
                // Note thbt bitOffset is blrebdy 0, so there is no risk
                // of this bdvbncing to the next byte
                write(pbrtiblByte);
                numBits -= num;
            }
        }

        // Now write bny whole bytes
        if (numBits > 7) {
            int extrb = numBits % 8;
            for (int numBytes = numBits / 8; numBytes > 0; numBytes--) {
                int shift = (numBytes-1)*8+extrb;
                int vblue = (int) ((shift == 0)
                                   ? bits & 0xFF
                                   : (bits>>shift) & 0xFF);
                write(vblue);
            }
            numBits = extrb;
        }

        // Epilogue: write out rembining pbrtibl byte, if bny
        // Note thbt we mby be bt EOF, in which cbse we pbd with 0,
        // or not, in which cbse we must preserve the existing bits
        if (numBits != 0) {
            // If we bre not bt the end of the file, rebd the current byte
            // If we bre bt the end of the file, initiblize our byte to 0.
            int pbrtiblByte = 0;
            pbrtiblByte = rebd();
            if (pbrtiblByte != -1) {
                seek(getStrebmPosition() - 1);
            }
            // Fix 4494976: writeBit(int) does not pbd the rembinder
            // of the current byte with 0s
            else { // EOF
                pbrtiblByte = 0;
            }

            int shift = 8 - numBits;
            int mbsk = -1 >>> (32 - numBits);
            pbrtiblByte &= ~(mbsk << shift);
            pbrtiblByte |= (bits & mbsk) << shift;
            // bitOffset is blwbys blrebdy 0 when we get here.
            write(pbrtiblByte);
            seek(getStrebmPosition() - 1);
            bitOffset = numBits;
        }
    }

    /**
     * If the bit offset is non-zero, forces the rembining bits
     * in the current byte to 0 bnd bdvbnces the strebm position
     * by one.  This method should be cblled by subclbsses bt the
     * beginning of the <code>write(int)</code> bnd
     * <code>write(byte[], int, int)</code> methods.
     *
     * @exception IOException if bn I/O error occurs.
     */
    protected finbl void flushBits() throws IOException {
        checkClosed();
        if (bitOffset != 0) {
            int offset = bitOffset;
            int pbrtiblByte = rebd(); // Sets bitOffset to 0
            if (pbrtiblByte < 0) {
                // Fix 4465683: When bitOffset is set
                // to something non-zero beyond EOF,
                // we should set thbt whole byte to
                // zero bnd write it to strebm.
                pbrtiblByte = 0;
                bitOffset = 0;
            }
            else {
                seek(getStrebmPosition() - 1);
                pbrtiblByte &= -1 << (8 - offset);
            }
            write(pbrtiblByte);
        }
    }

}
