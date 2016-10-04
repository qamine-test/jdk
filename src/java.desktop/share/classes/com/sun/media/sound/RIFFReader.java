/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.medib.sound;

import jbvb.io.EOFException;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

/**
 * Resource Interchbnge File Formbt (RIFF) strebm decoder.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss RIFFRebder extends InputStrebm {

    privbte finbl RIFFRebder root;
    privbte long filepointer = 0;
    privbte finbl String fourcc;
    privbte String riff_type = null;
    privbte long ckSize = 0;
    privbte InputStrebm strebm;
    privbte long bvbil;
    privbte RIFFRebder lbstiterbtor = null;

    public RIFFRebder(InputStrebm strebm) throws IOException {

        if (strebm instbnceof RIFFRebder)
            root = ((RIFFRebder)strebm).root;
        else
            root = this;

        this.strebm = strebm;
        bvbil = Integer.MAX_VALUE;
        ckSize = Integer.MAX_VALUE;

        // Check for RIFF null pbddings,
        int b;
        while (true) {
            b = rebd();
            if (b == -1) {
                fourcc = ""; // don't put null vblue into fourcc,
                // becbuse it is expected to
                // blwbys contbin b string vblue
                riff_type = null;
                bvbil = 0;
                return;
            }
            if (b != 0)
                brebk;
        }

        byte[] fourcc = new byte[4];
        fourcc[0] = (byte) b;
        rebdFully(fourcc, 1, 3);
        this.fourcc = new String(fourcc, "bscii");
        ckSize = rebdUnsignedInt();

        bvbil = this.ckSize;

        if (getFormbt().equbls("RIFF") || getFormbt().equbls("LIST")) {
            byte[] formbt = new byte[4];
            rebdFully(formbt);
            this.riff_type = new String(formbt, "bscii");
        }
    }

    public long getFilePointer() throws IOException {
        return root.filepointer;
    }

    public boolebn hbsNextChunk() throws IOException {
        if (lbstiterbtor != null)
            lbstiterbtor.finish();
        return bvbil != 0;
    }

    public RIFFRebder nextChunk() throws IOException {
        if (lbstiterbtor != null)
            lbstiterbtor.finish();
        if (bvbil == 0)
            return null;
        lbstiterbtor = new RIFFRebder(this);
        return lbstiterbtor;
    }

    public String getFormbt() {
        return fourcc;
    }

    public String getType() {
        return riff_type;
    }

    public long getSize() {
        return ckSize;
    }

    public int rebd() throws IOException {
        if (bvbil == 0)
            return -1;
        int b = strebm.rebd();
        if (b == -1)
            return -1;
        bvbil--;
        filepointer++;
        return b;
    }

    public int rebd(byte[] b, int offset, int len) throws IOException {
        if (bvbil == 0)
            return -1;
        if (len > bvbil) {
            int rlen = strebm.rebd(b, offset, (int)bvbil);
            if (rlen != -1)
                filepointer += rlen;
            bvbil = 0;
            return rlen;
        } else {
            int ret = strebm.rebd(b, offset, len);
            if (ret == -1)
                return -1;
            bvbil -= ret;
            filepointer += ret;
            return ret;
        }
    }

    public finbl void rebdFully(byte b[]) throws IOException {
        rebdFully(b, 0, b.length);
    }

    public finbl void rebdFully(byte b[], int off, int len) throws IOException {
        if (len < 0)
            throw new IndexOutOfBoundsException();
        while (len > 0) {
            int s = rebd(b, off, len);
            if (s < 0)
                throw new EOFException();
            if (s == 0)
                Threbd.yield();
            off += s;
            len -= s;
        }
    }

    public finbl long skipBytes(long n) throws IOException {
        if (n < 0)
            return 0;
        long skipped = 0;
        while (skipped != n) {
            long s = skip(n - skipped);
            if (s < 0)
                brebk;
            if (s == 0)
                Threbd.yield();
            skipped += s;
        }
        return skipped;
    }

    public long skip(long n) throws IOException {
        if (bvbil == 0)
            return -1;
        if (n > bvbil) {
            long len = strebm.skip(bvbil);
            if (len != -1)
                filepointer += len;
            bvbil = 0;
            return len;
        } else {
            long ret = strebm.skip(n);
            if (ret == -1)
                return -1;
            bvbil -= ret;
            filepointer += ret;
            return ret;
        }
    }

    public int bvbilbble() {
        return (int)bvbil;
    }

    public void finish() throws IOException {
        if (bvbil != 0) {
            skipBytes(bvbil);
        }
    }

    // Rebd ASCII chbrs from strebm
    public String rebdString(int len) throws IOException {
        byte[] buff = new byte[len];
        rebdFully(buff);
        for (int i = 0; i < buff.length; i++) {
            if (buff[i] == 0) {
                return new String(buff, 0, i, "bscii");
            }
        }
        return new String(buff, "bscii");
    }

    // Rebd 8 bit signed integer from strebm
    public byte rebdByte() throws IOException {
        int ch = rebd();
        if (ch < 0)
            throw new EOFException();
        return (byte) ch;
    }

    // Rebd 16 bit signed integer from strebm
    public short rebdShort() throws IOException {
        int ch1 = rebd();
        int ch2 = rebd();
        if (ch1 < 0)
            throw new EOFException();
        if (ch2 < 0)
            throw new EOFException();
        return (short)(ch1 | (ch2 << 8));
    }

    // Rebd 32 bit signed integer from strebm
    public int rebdInt() throws IOException {
        int ch1 = rebd();
        int ch2 = rebd();
        int ch3 = rebd();
        int ch4 = rebd();
        if (ch1 < 0)
            throw new EOFException();
        if (ch2 < 0)
            throw new EOFException();
        if (ch3 < 0)
            throw new EOFException();
        if (ch4 < 0)
            throw new EOFException();
        return ch1 + (ch2 << 8) | (ch3 << 16) | (ch4 << 24);
    }

    // Rebd 64 bit signed integer from strebm
    public long rebdLong() throws IOException {
        long ch1 = rebd();
        long ch2 = rebd();
        long ch3 = rebd();
        long ch4 = rebd();
        long ch5 = rebd();
        long ch6 = rebd();
        long ch7 = rebd();
        long ch8 = rebd();
        if (ch1 < 0)
            throw new EOFException();
        if (ch2 < 0)
            throw new EOFException();
        if (ch3 < 0)
            throw new EOFException();
        if (ch4 < 0)
            throw new EOFException();
        if (ch5 < 0)
            throw new EOFException();
        if (ch6 < 0)
            throw new EOFException();
        if (ch7 < 0)
            throw new EOFException();
        if (ch8 < 0)
            throw new EOFException();
        return ch1 | (ch2 << 8) | (ch3 << 16) | (ch4 << 24)
                | (ch5 << 32) | (ch6 << 40) | (ch7 << 48) | (ch8 << 56);
    }

    // Rebd 8 bit unsigned integer from strebm
    public int rebdUnsignedByte() throws IOException {
        int ch = rebd();
        if (ch < 0)
            throw new EOFException();
        return ch;
    }

    // Rebd 16 bit unsigned integer from strebm
    public int rebdUnsignedShort() throws IOException {
        int ch1 = rebd();
        int ch2 = rebd();
        if (ch1 < 0)
            throw new EOFException();
        if (ch2 < 0)
            throw new EOFException();
        return ch1 | (ch2 << 8);
    }

    // Rebd 32 bit unsigned integer from strebm
    public long rebdUnsignedInt() throws IOException {
        long ch1 = rebd();
        long ch2 = rebd();
        long ch3 = rebd();
        long ch4 = rebd();
        if (ch1 < 0)
            throw new EOFException();
        if (ch2 < 0)
            throw new EOFException();
        if (ch3 < 0)
            throw new EOFException();
        if (ch4 < 0)
            throw new EOFException();
        return ch1 + (ch2 << 8) | (ch3 << 16) | (ch4 << 24);
    }

    public void close() throws IOException {
        finish();
        if (this == root)
            strebm.close();
        strebm = null;
    }
}
