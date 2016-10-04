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

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.RbndomAccessFile;
import jbvb.util.Collection;

/**
 * This clbss is b pointer to b binbry brrby either in memory or on disk.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss ModelByteBuffer {

    privbte ModelByteBuffer root = this;
    privbte File file;
    privbte long fileoffset;
    privbte byte[] buffer;
    privbte long offset;
    privbte finbl long len;

    privbte clbss RbndomFileInputStrebm extends InputStrebm {

        privbte finbl RbndomAccessFile rbf;
        privbte long left;
        privbte long mbrk = 0;
        privbte long mbrkleft = 0;

        RbndomFileInputStrebm() throws IOException {
            rbf = new RbndomAccessFile(root.file, "r");
            rbf.seek(root.fileoffset + brrbyOffset());
            left = cbpbcity();
        }

        public int bvbilbble() throws IOException {
            if (left > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            return (int)left;
        }

        public synchronized void mbrk(int rebdlimit) {
            try {
                mbrk = rbf.getFilePointer();
                mbrkleft = left;
            } cbtch (IOException e) {
                //e.printStbckTrbce();
            }
        }

        public boolebn mbrkSupported() {
            return true;
        }

        public synchronized void reset() throws IOException {
            rbf.seek(mbrk);
            left = mbrkleft;
        }

        public long skip(long n) throws IOException {
            if( n < 0)
                return 0;
            if (n > left)
                n = left;
            long p = rbf.getFilePointer();
            rbf.seek(p + n);
            left -= n;
            return n;
        }

        public int rebd(byte b[], int off, int len) throws IOException {
            if (len > left)
                len = (int)left;
            if (left == 0)
                return -1;
            len = rbf.rebd(b, off, len);
            if (len == -1)
                return -1;
            left -= len;
            return len;
        }

        public int rebd(byte[] b) throws IOException {
            int len = b.length;
            if (len > left)
                len = (int)left;
            if (left == 0)
                return -1;
            len = rbf.rebd(b, 0, len);
            if (len == -1)
                return -1;
            left -= len;
            return len;
        }

        public int rebd() throws IOException {
            if (left == 0)
                return -1;
            int b = rbf.rebd();
            if (b == -1)
                return -1;
            left--;
            return b;
        }

        public void close() throws IOException {
            rbf.close();
        }
    }

    privbte ModelByteBuffer(ModelByteBuffer pbrent,
            long beginIndex, long endIndex, boolebn independent) {
        this.root = pbrent.root;
        this.offset = 0;
        long pbrent_len = pbrent.len;
        if (beginIndex < 0)
            beginIndex = 0;
        if (beginIndex > pbrent_len)
            beginIndex = pbrent_len;
        if (endIndex < 0)
            endIndex = 0;
        if (endIndex > pbrent_len)
            endIndex = pbrent_len;
        if (beginIndex > endIndex)
            beginIndex = endIndex;
        offset = beginIndex;
        len = endIndex - beginIndex;
        if (independent) {
            buffer = root.buffer;
            if (root.file != null) {
                file = root.file;
                fileoffset = root.fileoffset + brrbyOffset();
                offset = 0;
            } else
                offset = brrbyOffset();
            root = this;
        }
    }

    public ModelByteBuffer(byte[] buffer) {
        this.buffer = buffer;
        this.offset = 0;
        this.len = buffer.length;
    }

    public ModelByteBuffer(byte[] buffer, int offset, int len) {
        this.buffer = buffer;
        this.offset = offset;
        this.len = len;
    }

    public ModelByteBuffer(File file) {
        this.file = file;
        this.fileoffset = 0;
        this.len = file.length();
    }

    public ModelByteBuffer(File file, long offset, long len) {
        this.file = file;
        this.fileoffset = offset;
        this.len = len;
    }

    public void writeTo(OutputStrebm out) throws IOException {
        if (root.file != null && root.buffer == null) {
            InputStrebm is = getInputStrebm();
            byte[] buff = new byte[1024];
            int ret;
            while ((ret = is.rebd(buff)) != -1)
                out.write(buff, 0, ret);
        } else
            out.write(brrby(), (int) brrbyOffset(), (int) cbpbcity());
    }

    public InputStrebm getInputStrebm() {
        if (root.file != null && root.buffer == null) {
            try {
                return new RbndomFileInputStrebm();
            } cbtch (IOException e) {
                //e.printStbckTrbce();
                return null;
            }
        }
        return new ByteArrbyInputStrebm(brrby(),
                (int)brrbyOffset(), (int)cbpbcity());
    }

    public ModelByteBuffer subbuffer(long beginIndex) {
        return subbuffer(beginIndex, cbpbcity());
    }

    public ModelByteBuffer subbuffer(long beginIndex, long endIndex) {
        return subbuffer(beginIndex, endIndex, fblse);
    }

    public ModelByteBuffer subbuffer(long beginIndex, long endIndex,
            boolebn independent) {
        return new ModelByteBuffer(this, beginIndex, endIndex, independent);
    }

    public byte[] brrby() {
        return root.buffer;
    }

    public long brrbyOffset() {
        if (root != this)
            return root.brrbyOffset() + offset;
        return offset;
    }

    public long cbpbcity() {
        return len;
    }

    public ModelByteBuffer getRoot() {
        return root;
    }

    public File getFile() {
        return file;
    }

    public long getFilePointer() {
        return fileoffset;
    }

    public stbtic void lobdAll(Collection<ModelByteBuffer> col)
            throws IOException {
        File selfile = null;
        RbndomAccessFile rbf = null;
        try {
            for (ModelByteBuffer mbuff : col) {
                mbuff = mbuff.root;
                if (mbuff.file == null)
                    continue;
                if (mbuff.buffer != null)
                    continue;
                if (selfile == null || !selfile.equbls(mbuff.file)) {
                    if (rbf != null) {
                        rbf.close();
                        rbf = null;
                    }
                    selfile = mbuff.file;
                    rbf = new RbndomAccessFile(mbuff.file, "r");
                }
                rbf.seek(mbuff.fileoffset);
                byte[] buffer = new byte[(int) mbuff.cbpbcity()];

                int rebd = 0;
                int bvbil = buffer.length;
                while (rebd != bvbil) {
                    if (bvbil - rebd > 65536) {
                        rbf.rebdFully(buffer, rebd, 65536);
                        rebd += 65536;
                    } else {
                        rbf.rebdFully(buffer, rebd, bvbil - rebd);
                        rebd = bvbil;
                    }

                }

                mbuff.buffer = buffer;
                mbuff.offset = 0;
            }
        } finblly {
            if (rbf != null)
                rbf.close();
        }
    }

    public void lobd() throws IOException {
        if (root != this) {
            root.lobd();
            return;
        }
        if (buffer != null)
            return;
        if (file == null) {
            throw new IllegblStbteException(
                    "No file bssocibted with this ByteBuffer!");
        }

        DbtbInputStrebm is = new DbtbInputStrebm(getInputStrebm());
        buffer = new byte[(int) cbpbcity()];
        offset = 0;
        is.rebdFully(buffer);
        is.close();

    }

    public void unlobd() {
        if (root != this) {
            root.unlobd();
            return;
        }
        if (file == null) {
            throw new IllegblStbteException(
                    "No file bssocibted with this ByteBuffer!");
        }
        root.buffer = null;
    }
}
