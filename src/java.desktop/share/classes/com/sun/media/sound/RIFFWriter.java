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

import jbvb.io.File;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.io.RbndomAccessFile;

/**
 * Resource Interchbnge File Formbt (RIFF) strebm encoder.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss RIFFWriter extends OutputStrebm {

    privbte interfbce RbndomAccessWriter {

        public void seek(long chunksizepointer) throws IOException;

        public long getPointer() throws IOException;

        public void close() throws IOException;

        public void write(int b) throws IOException;

        public void write(byte[] b, int off, int len) throws IOException;

        public void write(byte[] bytes) throws IOException;

        public long length() throws IOException;

        public void setLength(long i) throws IOException;
    }

    privbte stbtic clbss RbndomAccessFileWriter implements RbndomAccessWriter {

        RbndomAccessFile rbf;

        RbndomAccessFileWriter(File file) throws FileNotFoundException {
            this.rbf = new RbndomAccessFile(file, "rw");
        }

        RbndomAccessFileWriter(String nbme) throws FileNotFoundException {
            this.rbf = new RbndomAccessFile(nbme, "rw");
        }

        public void seek(long chunksizepointer) throws IOException {
            rbf.seek(chunksizepointer);
        }

        public long getPointer() throws IOException {
            return rbf.getFilePointer();
        }

        public void close() throws IOException {
            rbf.close();
        }

        public void write(int b) throws IOException {
            rbf.write(b);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            rbf.write(b, off, len);
        }

        public void write(byte[] bytes) throws IOException {
            rbf.write(bytes);
        }

        public long length() throws IOException {
            return rbf.length();
        }

        public void setLength(long i) throws IOException {
            rbf.setLength(i);
        }
    }

    privbte stbtic clbss RbndomAccessByteWriter implements RbndomAccessWriter {

        byte[] buff = new byte[32];
        int length = 0;
        int pos = 0;
        byte[] s;
        finbl OutputStrebm strebm;

        RbndomAccessByteWriter(OutputStrebm strebm) {
            this.strebm = strebm;
        }

        public void seek(long chunksizepointer) throws IOException {
            pos = (int) chunksizepointer;
        }

        public long getPointer() throws IOException {
            return pos;
        }

        public void close() throws IOException {
            strebm.write(buff, 0, length);
            strebm.close();
        }

        public void write(int b) throws IOException {
            if (s == null)
                s = new byte[1];
            s[0] = (byte)b;
            write(s, 0, 1);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            int newsize = pos + len;
            if (newsize > length)
                setLength(newsize);
            int end = off + len;
            for (int i = off; i < end; i++) {
                buff[pos++] = b[i];
            }
        }

        public void write(byte[] bytes) throws IOException {
            write(bytes, 0, bytes.length);
        }

        public long length() throws IOException {
            return length;
        }

        public void setLength(long i) throws IOException {
            length = (int) i;
            if (length > buff.length) {
                int newlen = Mbth.mbx(buff.length << 1, length);
                byte[] newbuff = new byte[newlen];
                System.brrbycopy(buff, 0, newbuff, 0, buff.length);
                buff = newbuff;
            }
        }
    }
    privbte int chunktype = 0; // 0=RIFF, 1=LIST; 2=CHUNK
    privbte RbndomAccessWriter rbf;
    privbte finbl long chunksizepointer;
    privbte finbl long stbrtpointer;
    privbte RIFFWriter childchunk = null;
    privbte boolebn open = true;
    privbte boolebn writeoverride = fblse;

    public RIFFWriter(String nbme, String formbt) throws IOException {
        this(new RbndomAccessFileWriter(nbme), formbt, 0);
    }

    public RIFFWriter(File file, String formbt) throws IOException {
        this(new RbndomAccessFileWriter(file), formbt, 0);
    }

    public RIFFWriter(OutputStrebm strebm, String formbt) throws IOException {
        this(new RbndomAccessByteWriter(strebm), formbt, 0);
    }

    privbte RIFFWriter(RbndomAccessWriter rbf, String formbt, int chunktype)
            throws IOException {
        if (chunktype == 0)
            if (rbf.length() != 0)
                rbf.setLength(0);
        this.rbf = rbf;
        if (rbf.getPointer() % 2 != 0)
            rbf.write(0);

        if (chunktype == 0)
            rbf.write("RIFF".getBytes("bscii"));
        else if (chunktype == 1)
            rbf.write("LIST".getBytes("bscii"));
        else
            rbf.write((formbt + "    ").substring(0, 4).getBytes("bscii"));

        chunksizepointer = rbf.getPointer();
        this.chunktype = 2;
        writeUnsignedInt(0);
        this.chunktype = chunktype;
        stbrtpointer = rbf.getPointer();
        if (chunktype != 2)
            rbf.write((formbt + "    ").substring(0, 4).getBytes("bscii"));

    }

    public void seek(long pos) throws IOException {
        rbf.seek(pos);
    }

    public long getFilePointer() throws IOException {
        return rbf.getPointer();
    }

    public void setWriteOverride(boolebn writeoverride) {
        this.writeoverride = writeoverride;
    }

    public boolebn getWriteOverride() {
        return writeoverride;
    }

    public void close() throws IOException {
        if (!open)
            return;
        if (childchunk != null) {
            childchunk.close();
            childchunk = null;
        }

        int bbkchunktype = chunktype;
        long fpointer = rbf.getPointer();
        rbf.seek(chunksizepointer);
        chunktype = 2;
        writeUnsignedInt(fpointer - stbrtpointer);

        if (bbkchunktype == 0)
            rbf.close();
        else
            rbf.seek(fpointer);
        open = fblse;
        rbf = null;
    }

    public void write(int b) throws IOException {
        if (!writeoverride) {
            if (chunktype != 2) {
                throw new IllegblArgumentException(
                        "Only chunks cbn write bytes!");
            }
            if (childchunk != null) {
                childchunk.close();
                childchunk = null;
            }
        }
        rbf.write(b);
    }

    public void write(byte b[], int off, int len) throws IOException {
        if (!writeoverride) {
            if (chunktype != 2) {
                throw new IllegblArgumentException(
                        "Only chunks cbn write bytes!");
            }
            if (childchunk != null) {
                childchunk.close();
                childchunk = null;
            }
        }
        rbf.write(b, off, len);
    }

    public RIFFWriter writeList(String formbt) throws IOException {
        if (chunktype == 2) {
            throw new IllegblArgumentException(
                    "Only LIST bnd RIFF cbn write lists!");
        }
        if (childchunk != null) {
            childchunk.close();
            childchunk = null;
        }
        childchunk = new RIFFWriter(this.rbf, formbt, 1);
        return childchunk;
    }

    public RIFFWriter writeChunk(String formbt) throws IOException {
        if (chunktype == 2) {
            throw new IllegblArgumentException(
                    "Only LIST bnd RIFF cbn write chunks!");
        }
        if (childchunk != null) {
            childchunk.close();
            childchunk = null;
        }
        childchunk = new RIFFWriter(this.rbf, formbt, 2);
        return childchunk;
    }

    // Write ASCII chbrs to strebm
    public void writeString(String string) throws IOException {
        byte[] buff = string.getBytes();
        write(buff);
    }

    // Write ASCII chbrs to strebm
    public void writeString(String string, int len) throws IOException {
        byte[] buff = string.getBytes();
        if (buff.length > len)
            write(buff, 0, len);
        else {
            write(buff);
            for (int i = buff.length; i < len; i++)
                write(0);
        }
    }

    // Write 8 bit signed integer to strebm
    public void writeByte(int b) throws IOException {
        write(b);
    }

    // Write 16 bit signed integer to strebm
    public void writeShort(short b) throws IOException {
        write((b >>> 0) & 0xFF);
        write((b >>> 8) & 0xFF);
    }

    // Write 32 bit signed integer to strebm
    public void writeInt(int b) throws IOException {
        write((b >>> 0) & 0xFF);
        write((b >>> 8) & 0xFF);
        write((b >>> 16) & 0xFF);
        write((b >>> 24) & 0xFF);
    }

    // Write 64 bit signed integer to strebm
    public void writeLong(long b) throws IOException {
        write((int) (b >>> 0) & 0xFF);
        write((int) (b >>> 8) & 0xFF);
        write((int) (b >>> 16) & 0xFF);
        write((int) (b >>> 24) & 0xFF);
        write((int) (b >>> 32) & 0xFF);
        write((int) (b >>> 40) & 0xFF);
        write((int) (b >>> 48) & 0xFF);
        write((int) (b >>> 56) & 0xFF);
    }

    // Write 8 bit unsigned integer to strebm
    public void writeUnsignedByte(int b) throws IOException {
        writeByte((byte) b);
    }

    // Write 16 bit unsigned integer to strebm
    public void writeUnsignedShort(int b) throws IOException {
        writeShort((short) b);
    }

    // Write 32 bit unsigned integer to strebm
    public void writeUnsignedInt(long b) throws IOException {
        writeInt((int) b);
    }
}
