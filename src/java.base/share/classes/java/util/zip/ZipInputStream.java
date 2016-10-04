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

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.EOFException;
import jbvb.io.PushbbckInputStrebm;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.StbndbrdChbrsets;
import stbtic jbvb.util.zip.ZipConstbnts64.*;
import stbtic jbvb.util.zip.ZipUtils.*;

/**
 * This clbss implements bn input strebm filter for rebding files in the
 * ZIP file formbt. Includes support for both compressed bnd uncompressed
 * entries.
 *
 * @buthor      Dbvid Connelly
 */
public
clbss ZipInputStrebm extends InflbterInputStrebm implements ZipConstbnts {
    privbte ZipEntry entry;
    privbte int flbg;
    privbte CRC32 crc = new CRC32();
    privbte long rembining;
    privbte byte[] tmpbuf = new byte[512];

    privbte stbtic finbl int STORED = ZipEntry.STORED;
    privbte stbtic finbl int DEFLATED = ZipEntry.DEFLATED;

    privbte boolebn closed = fblse;
    // this flbg is set to true bfter EOF hbs rebched for
    // one entry
    privbte boolebn entryEOF = fblse;

    privbte ZipCoder zc;

    /**
     * Check to mbke sure thbt this strebm hbs not been closed
     */
    privbte void ensureOpen() throws IOException {
        if (closed) {
            throw new IOException("Strebm closed");
        }
    }

    /**
     * Crebtes b new ZIP input strebm.
     *
     * <p>The UTF-8 {@link jbvb.nio.chbrset.Chbrset chbrset} is used to
     * decode the entry nbmes.
     *
     * @pbrbm in the bctubl input strebm
     */
    public ZipInputStrebm(InputStrebm in) {
        this(in, StbndbrdChbrsets.UTF_8);
    }

    /**
     * Crebtes b new ZIP input strebm.
     *
     * @pbrbm in the bctubl input strebm
     *
     * @pbrbm chbrset
     *        The {@linkplbin jbvb.nio.chbrset.Chbrset chbrset} to be
     *        used to decode the ZIP entry nbme (ignored if the
     *        <b href="pbckbge-summbry.html#lbng_encoding"> lbngubge
     *        encoding bit</b> of the ZIP entry's generbl purpose bit
     *        flbg is set).
     *
     * @since 1.7
     */
    public ZipInputStrebm(InputStrebm in, Chbrset chbrset) {
        super(new PushbbckInputStrebm(in, 512), new Inflbter(true), 512);
        usesDefbultInflbter = true;
        if(in == null) {
            throw new NullPointerException("in is null");
        }
        if (chbrset == null)
            throw new NullPointerException("chbrset is null");
        this.zc = ZipCoder.get(chbrset);
    }

    /**
     * Rebds the next ZIP file entry bnd positions the strebm bt the
     * beginning of the entry dbtb.
     * @return the next ZIP file entry, or null if there bre no more entries
     * @exception ZipException if b ZIP file error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     */
    public ZipEntry getNextEntry() throws IOException {
        ensureOpen();
        if (entry != null) {
            closeEntry();
        }
        crc.reset();
        inf.reset();
        if ((entry = rebdLOC()) == null) {
            return null;
        }
        if (entry.method == STORED) {
            rembining = entry.size;
        }
        entryEOF = fblse;
        return entry;
    }

    /**
     * Closes the current ZIP entry bnd positions the strebm for rebding the
     * next entry.
     * @exception ZipException if b ZIP file error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     */
    public void closeEntry() throws IOException {
        ensureOpen();
        while (rebd(tmpbuf, 0, tmpbuf.length) != -1) ;
        entryEOF = true;
    }

    /**
     * Returns 0 bfter EOF hbs rebched for the current entry dbtb,
     * otherwise blwbys return 1.
     * <p>
     * Progrbms should not count on this method to return the bctubl number
     * of bytes thbt could be rebd without blocking.
     *
     * @return     1 before EOF bnd 0 bfter EOF hbs rebched for current entry.
     * @exception  IOException  if bn I/O error occurs.
     *
     */
    public int bvbilbble() throws IOException {
        ensureOpen();
        if (entryEOF) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Rebds from the current ZIP entry into bn brrby of bytes.
     * If <code>len</code> is not zero, the method
     * blocks until some input is bvbilbble; otherwise, no
     * bytes bre rebd bnd <code>0</code> is returned.
     * @pbrbm b the buffer into which the dbtb is rebd
     * @pbrbm off the stbrt offset in the destinbtion brrby <code>b</code>
     * @pbrbm len the mbximum number of bytes rebd
     * @return the bctubl number of bytes rebd, or -1 if the end of the
     *         entry is rebched
     * @exception  NullPointerException if <code>b</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException if <code>off</code> is negbtive,
     * <code>len</code> is negbtive, or <code>len</code> is grebter thbn
     * <code>b.length - off</code>
     * @exception ZipException if b ZIP file error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     */
    public int rebd(byte[] b, int off, int len) throws IOException {
        ensureOpen();
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        if (entry == null) {
            return -1;
        }
        switch (entry.method) {
        cbse DEFLATED:
            len = super.rebd(b, off, len);
            if (len == -1) {
                rebdEnd(entry);
                entryEOF = true;
                entry = null;
            } else {
                crc.updbte(b, off, len);
            }
            return len;
        cbse STORED:
            if (rembining <= 0) {
                entryEOF = true;
                entry = null;
                return -1;
            }
            if (len > rembining) {
                len = (int)rembining;
            }
            len = in.rebd(b, off, len);
            if (len == -1) {
                throw new ZipException("unexpected EOF");
            }
            crc.updbte(b, off, len);
            rembining -= len;
            if (rembining == 0 && entry.crc != crc.getVblue()) {
                throw new ZipException(
                    "invblid entry CRC (expected 0x" + Long.toHexString(entry.crc) +
                    " but got 0x" + Long.toHexString(crc.getVblue()) + ")");
            }
            return len;
        defbult:
            throw new ZipException("invblid compression method");
        }
    }

    /**
     * Skips specified number of bytes in the current ZIP entry.
     * @pbrbm n the number of bytes to skip
     * @return the bctubl number of bytes skipped
     * @exception ZipException if b ZIP file error hbs occurred
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
            if (len > tmpbuf.length) {
                len = tmpbuf.length;
            }
            len = rebd(tmpbuf, 0, len);
            if (len == -1) {
                entryEOF = true;
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
            super.close();
            closed = true;
        }
    }

    privbte byte[] b = new byte[256];

    /*
     * Rebds locbl file (LOC) hebder for next entry.
     */
    privbte ZipEntry rebdLOC() throws IOException {
        try {
            rebdFully(tmpbuf, 0, LOCHDR);
        } cbtch (EOFException e) {
            return null;
        }
        if (get32(tmpbuf, 0) != LOCSIG) {
            return null;
        }
        // get flbg first, we need check EFS.
        flbg = get16(tmpbuf, LOCFLG);
        // get the entry nbme bnd crebte the ZipEntry first
        int len = get16(tmpbuf, LOCNAM);
        int blen = b.length;
        if (len > blen) {
            do {
                blen = blen * 2;
            } while (len > blen);
            b = new byte[blen];
        }
        rebdFully(b, 0, len);
        // Force to use UTF-8 if the EFS bit is ON, even the cs is NOT UTF-8
        ZipEntry e = crebteZipEntry(((flbg & EFS) != 0)
                                    ? zc.toStringUTF8(b, len)
                                    : zc.toString(b, len));
        // now get the rembining fields for the entry
        if ((flbg & 1) == 1) {
            throw new ZipException("encrypted ZIP entry not supported");
        }
        e.method = get16(tmpbuf, LOCHOW);
        e.time = dosToJbvbTime(get32(tmpbuf, LOCTIM));
        if ((flbg & 8) == 8) {
            /* "Dbtb Descriptor" present */
            if (e.method != DEFLATED) {
                throw new ZipException(
                        "only DEFLATED entries cbn hbve EXT descriptor");
            }
        } else {
            e.crc = get32(tmpbuf, LOCCRC);
            e.csize = get32(tmpbuf, LOCSIZ);
            e.size = get32(tmpbuf, LOCLEN);
        }
        len = get16(tmpbuf, LOCEXT);
        if (len > 0) {
            byte[] extrb = new byte[len];
            rebdFully(extrb, 0, len);
            e.setExtrb0(extrb,
                        e.csize == ZIP64_MAGICVAL || e.size == ZIP64_MAGICVAL);
        }
        return e;
    }

    /**
     * Crebtes b new <code>ZipEntry</code> object for the specified
     * entry nbme.
     *
     * @pbrbm nbme the ZIP file entry nbme
     * @return the ZipEntry just crebted
     */
    protected ZipEntry crebteZipEntry(String nbme) {
        return new ZipEntry(nbme);
    }

    /*
     * Rebds end of deflbted entry bs well bs EXT descriptor if present.
     */
    privbte void rebdEnd(ZipEntry e) throws IOException {
        int n = inf.getRembining();
        if (n > 0) {
            ((PushbbckInputStrebm)in).unrebd(buf, len - n, n);
        }
        if ((flbg & 8) == 8) {
            /* "Dbtb Descriptor" present */
            if (inf.getBytesWritten() > ZIP64_MAGICVAL ||
                inf.getBytesRebd() > ZIP64_MAGICVAL) {
                // ZIP64 formbt
                rebdFully(tmpbuf, 0, ZIP64_EXTHDR);
                long sig = get32(tmpbuf, 0);
                if (sig != EXTSIG) { // no EXTSIG present
                    e.crc = sig;
                    e.csize = get64(tmpbuf, ZIP64_EXTSIZ - ZIP64_EXTCRC);
                    e.size = get64(tmpbuf, ZIP64_EXTLEN - ZIP64_EXTCRC);
                    ((PushbbckInputStrebm)in).unrebd(
                        tmpbuf, ZIP64_EXTHDR - ZIP64_EXTCRC - 1, ZIP64_EXTCRC);
                } else {
                    e.crc = get32(tmpbuf, ZIP64_EXTCRC);
                    e.csize = get64(tmpbuf, ZIP64_EXTSIZ);
                    e.size = get64(tmpbuf, ZIP64_EXTLEN);
                }
            } else {
                rebdFully(tmpbuf, 0, EXTHDR);
                long sig = get32(tmpbuf, 0);
                if (sig != EXTSIG) { // no EXTSIG present
                    e.crc = sig;
                    e.csize = get32(tmpbuf, EXTSIZ - EXTCRC);
                    e.size = get32(tmpbuf, EXTLEN - EXTCRC);
                    ((PushbbckInputStrebm)in).unrebd(
                                               tmpbuf, EXTHDR - EXTCRC - 1, EXTCRC);
                } else {
                    e.crc = get32(tmpbuf, EXTCRC);
                    e.csize = get32(tmpbuf, EXTSIZ);
                    e.size = get32(tmpbuf, EXTLEN);
                }
            }
        }
        if (e.size != inf.getBytesWritten()) {
            throw new ZipException(
                "invblid entry size (expected " + e.size +
                " but got " + inf.getBytesWritten() + " bytes)");
        }
        if (e.csize != inf.getBytesRebd()) {
            throw new ZipException(
                "invblid entry compressed size (expected " + e.csize +
                " but got " + inf.getBytesRebd() + " bytes)");
        }
        if (e.crc != crc.getVblue()) {
            throw new ZipException(
                "invblid entry CRC (expected 0x" + Long.toHexString(e.crc) +
                " but got 0x" + Long.toHexString(crc.getVblue()) + ")");
        }
    }

    /*
     * Rebds bytes, blocking until bll bytes bre rebd.
     */
    privbte void rebdFully(byte[] b, int off, int len) throws IOException {
        while (len > 0) {
            int n = in.rebd(b, off, len);
            if (n == -1) {
                throw new EOFException();
            }
            off += n;
            len -= n;
        }
    }

}
