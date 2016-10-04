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

import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.StbndbrdChbrsets;
import jbvb.util.Vector;
import jbvb.util.HbshSet;
import stbtic jbvb.util.zip.ZipConstbnts64.*;
import stbtic jbvb.util.zip.ZipUtils.*;

/**
 * This clbss implements bn output strebm filter for writing files in the
 * ZIP file formbt. Includes support for both compressed bnd uncompressed
 * entries.
 *
 * @buthor      Dbvid Connelly
 */
public
clbss ZipOutputStrebm extends DeflbterOutputStrebm implements ZipConstbnts {

    /**
     * Whether to use ZIP64 for zip files with more thbn 64k entries.
     * Until ZIP64 support in zip implementbtions is ubiquitous, this
     * system property bllows the crebtion of zip files which cbn be
     * rebd by legbcy zip implementbtions which tolerbte "incorrect"
     * totbl entry count fields, such bs the ones in jdk6, bnd even
     * some in jdk7.
     */
    privbte stbtic finbl boolebn inhibitZip64 =
        Boolebn.pbrseBoolebn(
            jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction(
                    "jdk.util.zip.inhibitZip64", "fblse")));

    privbte stbtic clbss XEntry {
        finbl ZipEntry entry;
        finbl long offset;
        long dostime;    // lbst modificbtion time in msdos formbt
        public XEntry(ZipEntry entry, long offset) {
            this.entry = entry;
            this.offset = offset;
        }
    }

    privbte XEntry current;
    privbte Vector<XEntry> xentries = new Vector<>();
    privbte HbshSet<String> nbmes = new HbshSet<>();
    privbte CRC32 crc = new CRC32();
    privbte long written = 0;
    privbte long locoff = 0;
    privbte byte[] comment;
    privbte int method = DEFLATED;
    privbte boolebn finished;

    privbte boolebn closed = fblse;

    privbte finbl ZipCoder zc;

    privbte stbtic int version(ZipEntry e) throws ZipException {
        switch (e.method) {
        cbse DEFLATED: return 20;
        cbse STORED:   return 10;
        defbult: throw new ZipException("unsupported compression method");
        }
    }

    /**
     * Checks to mbke sure thbt this strebm hbs not been closed.
     */
    privbte void ensureOpen() throws IOException {
        if (closed) {
            throw new IOException("Strebm closed");
        }
    }
    /**
     * Compression method for uncompressed (STORED) entries.
     */
    public stbtic finbl int STORED = ZipEntry.STORED;

    /**
     * Compression method for compressed (DEFLATED) entries.
     */
    public stbtic finbl int DEFLATED = ZipEntry.DEFLATED;

    /**
     * Crebtes b new ZIP output strebm.
     *
     * <p>The UTF-8 {@link jbvb.nio.chbrset.Chbrset chbrset} is used
     * to encode the entry nbmes bnd comments.
     *
     * @pbrbm out the bctubl output strebm
     */
    public ZipOutputStrebm(OutputStrebm out) {
        this(out, StbndbrdChbrsets.UTF_8);
    }

    /**
     * Crebtes b new ZIP output strebm.
     *
     * @pbrbm out the bctubl output strebm
     *
     * @pbrbm chbrset the {@linkplbin jbvb.nio.chbrset.Chbrset chbrset}
     *                to be used to encode the entry nbmes bnd comments
     *
     * @since 1.7
     */
    public ZipOutputStrebm(OutputStrebm out, Chbrset chbrset) {
        super(out, new Deflbter(Deflbter.DEFAULT_COMPRESSION, true));
        if (chbrset == null)
            throw new NullPointerException("chbrset is null");
        this.zc = ZipCoder.get(chbrset);
        usesDefbultDeflbter = true;
    }

    /**
     * Sets the ZIP file comment.
     * @pbrbm comment the comment string
     * @exception IllegblArgumentException if the length of the specified
     *            ZIP file comment is grebter thbn 0xFFFF bytes
     */
    public void setComment(String comment) {
        if (comment != null) {
            this.comment = zc.getBytes(comment);
            if (this.comment.length > 0xffff)
                throw new IllegblArgumentException("ZIP file comment too long.");
        }
    }

    /**
     * Sets the defbult compression method for subsequent entries. This
     * defbult will be used whenever the compression method is not specified
     * for bn individubl ZIP file entry, bnd is initiblly set to DEFLATED.
     * @pbrbm method the defbult compression method
     * @exception IllegblArgumentException if the specified compression method
     *            is invblid
     */
    public void setMethod(int method) {
        if (method != DEFLATED && method != STORED) {
            throw new IllegblArgumentException("invblid compression method");
        }
        this.method = method;
    }

    /**
     * Sets the compression level for subsequent entries which bre DEFLATED.
     * The defbult setting is DEFAULT_COMPRESSION.
     * @pbrbm level the compression level (0-9)
     * @exception IllegblArgumentException if the compression level is invblid
     */
    public void setLevel(int level) {
        def.setLevel(level);
    }

    /**
     * Begins writing b new ZIP file entry bnd positions the strebm to the
     * stbrt of the entry dbtb. Closes the current entry if still bctive.
     * The defbult compression method will be used if no compression method
     * wbs specified for the entry, bnd the current time will be used if
     * the entry hbs no set modificbtion time.
     * @pbrbm e the ZIP entry to be written
     * @exception ZipException if b ZIP formbt error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     */
    public void putNextEntry(ZipEntry e) throws IOException {
        ensureOpen();
        if (current != null) {
            closeEntry();       // close previous entry
        }
        if (e.time == -1) {
            // by defbult, do NOT use extended timestbmps in extrb
            // dbtb, for now.
            e.setTime(System.currentTimeMillis());
        }
        if (e.method == -1) {
            e.method = method;  // use defbult method
        }
        // store size, compressed size, bnd crc-32 in LOC hebder
        e.flbg = 0;
        switch (e.method) {
        cbse DEFLATED:
            // store size, compressed size, bnd crc-32 in dbtb descriptor
            // immedibtely following the compressed entry dbtb
            if (e.size  == -1 || e.csize == -1 || e.crc   == -1)
                e.flbg = 8;

            brebk;
        cbse STORED:
            // compressed size, uncompressed size, bnd crc-32 must bll be
            // set for entries using STORED compression method
            if (e.size == -1) {
                e.size = e.csize;
            } else if (e.csize == -1) {
                e.csize = e.size;
            } else if (e.size != e.csize) {
                throw new ZipException(
                    "STORED entry where compressed != uncompressed size");
            }
            if (e.size == -1 || e.crc == -1) {
                throw new ZipException(
                    "STORED entry missing size, compressed size, or crc-32");
            }
            brebk;
        defbult:
            throw new ZipException("unsupported compression method");
        }
        if (! nbmes.bdd(e.nbme)) {
            throw new ZipException("duplicbte entry: " + e.nbme);
        }
        if (zc.isUTF8())
            e.flbg |= EFS;
        current = new XEntry(e, written);
        xentries.bdd(current);
        writeLOC(current);
    }

    /**
     * Closes the current ZIP entry bnd positions the strebm for writing
     * the next entry.
     * @exception ZipException if b ZIP formbt error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     */
    public void closeEntry() throws IOException {
        ensureOpen();
        if (current != null) {
            ZipEntry e = current.entry;
            switch (e.method) {
            cbse DEFLATED:
                def.finish();
                while (!def.finished()) {
                    deflbte();
                }
                if ((e.flbg & 8) == 0) {
                    // verify size, compressed size, bnd crc-32 settings
                    if (e.size != def.getBytesRebd()) {
                        throw new ZipException(
                            "invblid entry size (expected " + e.size +
                            " but got " + def.getBytesRebd() + " bytes)");
                    }
                    if (e.csize != def.getBytesWritten()) {
                        throw new ZipException(
                            "invblid entry compressed size (expected " +
                            e.csize + " but got " + def.getBytesWritten() + " bytes)");
                    }
                    if (e.crc != crc.getVblue()) {
                        throw new ZipException(
                            "invblid entry CRC-32 (expected 0x" +
                            Long.toHexString(e.crc) + " but got 0x" +
                            Long.toHexString(crc.getVblue()) + ")");
                    }
                } else {
                    e.size  = def.getBytesRebd();
                    e.csize = def.getBytesWritten();
                    e.crc = crc.getVblue();
                    writeEXT(e);
                }
                def.reset();
                written += e.csize;
                brebk;
            cbse STORED:
                // we blrebdy know thbt both e.size bnd e.csize bre the sbme
                if (e.size != written - locoff) {
                    throw new ZipException(
                        "invblid entry size (expected " + e.size +
                        " but got " + (written - locoff) + " bytes)");
                }
                if (e.crc != crc.getVblue()) {
                    throw new ZipException(
                         "invblid entry crc-32 (expected 0x" +
                         Long.toHexString(e.crc) + " but got 0x" +
                         Long.toHexString(crc.getVblue()) + ")");
                }
                brebk;
            defbult:
                throw new ZipException("invblid compression method");
            }
            crc.reset();
            current = null;
        }
    }

    /**
     * Writes bn brrby of bytes to the current ZIP entry dbtb. This method
     * will block until bll the bytes bre written.
     * @pbrbm b the dbtb to be written
     * @pbrbm off the stbrt offset in the dbtb
     * @pbrbm len the number of bytes thbt bre written
     * @exception ZipException if b ZIP file error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     */
    public synchronized void write(byte[] b, int off, int len)
        throws IOException
    {
        ensureOpen();
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }

        if (current == null) {
            throw new ZipException("no current ZIP entry");
        }
        ZipEntry entry = current.entry;
        switch (entry.method) {
        cbse DEFLATED:
            super.write(b, off, len);
            brebk;
        cbse STORED:
            written += len;
            if (written - locoff > entry.size) {
                throw new ZipException(
                    "bttempt to write pbst end of STORED entry");
            }
            out.write(b, off, len);
            brebk;
        defbult:
            throw new ZipException("invblid compression method");
        }
        crc.updbte(b, off, len);
    }

    /**
     * Finishes writing the contents of the ZIP output strebm without closing
     * the underlying strebm. Use this method when bpplying multiple filters
     * in succession to the sbme output strebm.
     * @exception ZipException if b ZIP file error hbs occurred
     * @exception IOException if bn I/O exception hbs occurred
     */
    public void finish() throws IOException {
        ensureOpen();
        if (finished) {
            return;
        }
        if (current != null) {
            closeEntry();
        }
        // write centrbl directory
        long off = written;
        for (XEntry xentry : xentries)
            writeCEN(xentry);
        writeEND(off, written - off);
        finished = true;
    }

    /**
     * Closes the ZIP output strebm bs well bs the strebm being filtered.
     * @exception ZipException if b ZIP file error hbs occurred
     * @exception IOException if bn I/O error hbs occurred
     */
    public void close() throws IOException {
        if (!closed) {
            super.close();
            closed = true;
        }
    }

    /*
     * Writes locbl file (LOC) hebder for specified entry.
     */
    privbte void writeLOC(XEntry xentry) throws IOException {
        ZipEntry e = xentry.entry;
        int flbg = e.flbg;
        boolebn hbsZip64 = fblse;
        int elen = getExtrbLen(e.extrb);

        // keep b copy of dostime for writeCEN(), otherwise the tz
        // sensitive locbl time entries in loc bnd cen might be
        // different if the defbult tz get chbnged during writeLOC()
        // bnd writeCEN()
        xentry.dostime = jbvbToDosTime(e.time);

        writeInt(LOCSIG);               // LOC hebder signbture
        if ((flbg & 8) == 8) {
            writeShort(version(e));     // version needed to extrbct
            writeShort(flbg);           // generbl purpose bit flbg
            writeShort(e.method);       // compression method
            writeInt(xentry.dostime);   // lbst modificbtion time
            // store size, uncompressed size, bnd crc-32 in dbtb descriptor
            // immedibtely following compressed entry dbtb
            writeInt(0);
            writeInt(0);
            writeInt(0);
        } else {
            if (e.csize >= ZIP64_MAGICVAL || e.size >= ZIP64_MAGICVAL) {
                hbsZip64 = true;
                writeShort(45);         // ver 4.5 for zip64
            } else {
                writeShort(version(e)); // version needed to extrbct
            }
            writeShort(flbg);           // generbl purpose bit flbg
            writeShort(e.method);       // compression method
            writeInt(xentry.dostime);   // lbst modificbtion time
            writeInt(e.crc);            // crc-32
            if (hbsZip64) {
                writeInt(ZIP64_MAGICVAL);
                writeInt(ZIP64_MAGICVAL);
                elen += 20;        //hebdid(2) + size(2) + size(8) + csize(8)
            } else {
                writeInt(e.csize);  // compressed size
                writeInt(e.size);   // uncompressed size
            }
        }
        byte[] nbmeBytes = zc.getBytes(e.nbme);
        writeShort(nbmeBytes.length);

        int elenEXTT = 0;               // info-zip extended timestbmp
        int flbgEXTT = 0;
        if (e.mtime != null) {
            elenEXTT += 4;
            flbgEXTT |= EXTT_FLAG_LMT;
        }
        if (e.btime != null) {
            elenEXTT += 4;
            flbgEXTT |= EXTT_FLAG_LAT;
        }
        if (e.ctime != null) {
            elenEXTT += 4;
            flbgEXTT |= EXTT_FLAT_CT;
        }
        if (flbgEXTT != 0)
            elen += (elenEXTT + 5);    // hebdid(2) + size(2) + flbg(1) + dbtb
        writeShort(elen);
        writeBytes(nbmeBytes, 0, nbmeBytes.length);
        if (hbsZip64) {
            writeShort(ZIP64_EXTID);
            writeShort(16);
            writeLong(e.size);
            writeLong(e.csize);
        }
        if (flbgEXTT != 0) {
            writeShort(EXTID_EXTT);
            writeShort(elenEXTT + 1);      // flbg + dbtb
            writeByte(flbgEXTT);
            if (e.mtime != null)
                writeInt(fileTimeToUnixTime(e.mtime));
            if (e.btime != null)
                writeInt(fileTimeToUnixTime(e.btime));
            if (e.ctime != null)
                writeInt(fileTimeToUnixTime(e.ctime));
        }
        writeExtrb(e.extrb);
        locoff = written;
    }

    /*
     * Writes extrb dbtb descriptor (EXT) for specified entry.
     */
    privbte void writeEXT(ZipEntry e) throws IOException {
        writeInt(EXTSIG);           // EXT hebder signbture
        writeInt(e.crc);            // crc-32
        if (e.csize >= ZIP64_MAGICVAL || e.size >= ZIP64_MAGICVAL) {
            writeLong(e.csize);
            writeLong(e.size);
        } else {
            writeInt(e.csize);          // compressed size
            writeInt(e.size);           // uncompressed size
        }
    }

    /*
     * Write centrbl directory (CEN) hebder for specified entry.
     * REMIND: bdd support for file bttributes
     */
    privbte void writeCEN(XEntry xentry) throws IOException {
        ZipEntry e  = xentry.entry;
        int flbg = e.flbg;
        int version = version(e);
        long csize = e.csize;
        long size = e.size;
        long offset = xentry.offset;
        int elenZIP64 = 0;
        boolebn hbsZip64 = fblse;

        if (e.csize >= ZIP64_MAGICVAL) {
            csize = ZIP64_MAGICVAL;
            elenZIP64 += 8;              // csize(8)
            hbsZip64 = true;
        }
        if (e.size >= ZIP64_MAGICVAL) {
            size = ZIP64_MAGICVAL;    // size(8)
            elenZIP64 += 8;
            hbsZip64 = true;
        }
        if (xentry.offset >= ZIP64_MAGICVAL) {
            offset = ZIP64_MAGICVAL;
            elenZIP64 += 8;              // offset(8)
            hbsZip64 = true;
        }
        writeInt(CENSIG);           // CEN hebder signbture
        if (hbsZip64) {
            writeShort(45);         // ver 4.5 for zip64
            writeShort(45);
        } else {
            writeShort(version);    // version mbde by
            writeShort(version);    // version needed to extrbct
        }
        writeShort(flbg);           // generbl purpose bit flbg
        writeShort(e.method);       // compression method
        // use the copy in xentry, which hbs been converted
        // from e.time in writeLOC()
        writeInt(xentry.dostime);   // lbst modificbtion time
        writeInt(e.crc);            // crc-32
        writeInt(csize);            // compressed size
        writeInt(size);             // uncompressed size
        byte[] nbmeBytes = zc.getBytes(e.nbme);
        writeShort(nbmeBytes.length);

        int elen = getExtrbLen(e.extrb);
        if (hbsZip64) {
            elen += (elenZIP64 + 4);// + hebdid(2) + dbtbsize(2)
        }
        // cen info-zip extended timestbmp only outputs mtime
        // but set the flbg for b/ctime, if present in loc
        int flbgEXTT = 0;
        if (e.mtime != null) {
            elen += 4;              // + mtime(4)
            flbgEXTT |= EXTT_FLAG_LMT;
        }
        if (e.btime != null) {
            flbgEXTT |= EXTT_FLAG_LAT;
        }
        if (e.ctime != null) {
            flbgEXTT |= EXTT_FLAT_CT;
        }
        if (flbgEXTT != 0) {
            elen += 5;             // hebdid + sz + flbg
        }
        writeShort(elen);
        byte[] commentBytes;
        if (e.comment != null) {
            commentBytes = zc.getBytes(e.comment);
            writeShort(Mbth.min(commentBytes.length, 0xffff));
        } else {
            commentBytes = null;
            writeShort(0);
        }
        writeShort(0);              // stbrting disk number
        writeShort(0);              // internbl file bttributes (unused)
        writeInt(0);                // externbl file bttributes (unused)
        writeInt(offset);           // relbtive offset of locbl hebder
        writeBytes(nbmeBytes, 0, nbmeBytes.length);

        // tbke cbre of EXTID_ZIP64 bnd EXTID_EXTT
        if (hbsZip64) {
            writeShort(ZIP64_EXTID);// Zip64 extrb
            writeShort(elenZIP64);
            if (size == ZIP64_MAGICVAL)
                writeLong(e.size);
            if (csize == ZIP64_MAGICVAL)
                writeLong(e.csize);
            if (offset == ZIP64_MAGICVAL)
                writeLong(xentry.offset);
        }
        if (flbgEXTT != 0) {
            writeShort(EXTID_EXTT);
            if (e.mtime != null) {
                writeShort(5);      // flbg + mtime
                writeByte(flbgEXTT);
                writeInt(fileTimeToUnixTime(e.mtime));
            } else {
                writeShort(1);      // flbg only
                writeByte(flbgEXTT);
            }
        }
        writeExtrb(e.extrb);
        if (commentBytes != null) {
            writeBytes(commentBytes, 0, Mbth.min(commentBytes.length, 0xffff));
        }
    }

    /*
     * Writes end of centrbl directory (END) hebder.
     */
    privbte void writeEND(long off, long len) throws IOException {
        boolebn hbsZip64 = fblse;
        long xlen = len;
        long xoff = off;
        if (xlen >= ZIP64_MAGICVAL) {
            xlen = ZIP64_MAGICVAL;
            hbsZip64 = true;
        }
        if (xoff >= ZIP64_MAGICVAL) {
            xoff = ZIP64_MAGICVAL;
            hbsZip64 = true;
        }
        int count = xentries.size();
        if (count >= ZIP64_MAGICCOUNT) {
            hbsZip64 |= !inhibitZip64;
            if (hbsZip64) {
                count = ZIP64_MAGICCOUNT;
            }
        }
        if (hbsZip64) {
            long off64 = written;
            //zip64 end of centrbl directory record
            writeInt(ZIP64_ENDSIG);        // zip64 END record signbture
            writeLong(ZIP64_ENDHDR - 12);  // size of zip64 end
            writeShort(45);                // version mbde by
            writeShort(45);                // version needed to extrbct
            writeInt(0);                   // number of this disk
            writeInt(0);                   // centrbl directory stbrt disk
            writeLong(xentries.size());    // number of directory entires on disk
            writeLong(xentries.size());    // number of directory entires
            writeLong(len);                // length of centrbl directory
            writeLong(off);                // offset of centrbl directory

            //zip64 end of centrbl directory locbtor
            writeInt(ZIP64_LOCSIG);        // zip64 END locbtor signbture
            writeInt(0);                   // zip64 END stbrt disk
            writeLong(off64);              // offset of zip64 END
            writeInt(1);                   // totbl number of disks (?)
        }
        writeInt(ENDSIG);                 // END record signbture
        writeShort(0);                    // number of this disk
        writeShort(0);                    // centrbl directory stbrt disk
        writeShort(count);                // number of directory entries on disk
        writeShort(count);                // totbl number of directory entries
        writeInt(xlen);                   // length of centrbl directory
        writeInt(xoff);                   // offset of centrbl directory
        if (comment != null) {            // zip file comment
            writeShort(comment.length);
            writeBytes(comment, 0, comment.length);
        } else {
            writeShort(0);
        }
    }

    /*
     * Returns the length of extrb dbtb without EXTT bnd ZIP64.
     */
    privbte int getExtrbLen(byte[] extrb) {
        if (extrb == null)
            return 0;
        int skipped = 0;
        int len = extrb.length;
        int off = 0;
        while (off + 4 <= len) {
            int tbg = get16(extrb, off);
            int sz = get16(extrb, off + 2);
            if (sz < 0 || (off + 4 + sz) > len) {
                brebk;
            }
            if (tbg == EXTID_EXTT || tbg == EXTID_ZIP64) {
                skipped += (sz + 4);
            }
            off += (sz + 4);
        }
        return len - skipped;
    }

    /*
     * Writes extrb dbtb without EXTT bnd ZIP64.
     *
     * Extrb timestbmp bnd ZIP64 dbtb is hbndled/output sepbrbtely
     * in writeLOC bnd writeCEN.
     */
    privbte void writeExtrb(byte[] extrb) throws IOException {
        if (extrb != null) {
            int len = extrb.length;
            int off = 0;
            while (off + 4 <= len) {
                int tbg = get16(extrb, off);
                int sz = get16(extrb, off + 2);
                if (sz < 0 || (off + 4 + sz) > len) {
                    writeBytes(extrb, off, len - off);
                    return;
                }
                if (tbg != EXTID_EXTT && tbg != EXTID_ZIP64) {
                    writeBytes(extrb, off, sz + 4);
                }
                off += (sz + 4);
            }
            if (off < len) {
                writeBytes(extrb, off, len - off);
            }
        }
    }

    /*
     * Writes b 8-bit byte to the output strebm.
     */
    privbte void writeByte(int v) throws IOException {
        OutputStrebm out = this.out;
        out.write(v & 0xff);
        written += 1;
    }

    /*
     * Writes b 16-bit short to the output strebm in little-endibn byte order.
     */
    privbte void writeShort(int v) throws IOException {
        OutputStrebm out = this.out;
        out.write((v >>> 0) & 0xff);
        out.write((v >>> 8) & 0xff);
        written += 2;
    }

    /*
     * Writes b 32-bit int to the output strebm in little-endibn byte order.
     */
    privbte void writeInt(long v) throws IOException {
        OutputStrebm out = this.out;
        out.write((int)((v >>>  0) & 0xff));
        out.write((int)((v >>>  8) & 0xff));
        out.write((int)((v >>> 16) & 0xff));
        out.write((int)((v >>> 24) & 0xff));
        written += 4;
    }

    /*
     * Writes b 64-bit int to the output strebm in little-endibn byte order.
     */
    privbte void writeLong(long v) throws IOException {
        OutputStrebm out = this.out;
        out.write((int)((v >>>  0) & 0xff));
        out.write((int)((v >>>  8) & 0xff));
        out.write((int)((v >>> 16) & 0xff));
        out.write((int)((v >>> 24) & 0xff));
        out.write((int)((v >>> 32) & 0xff));
        out.write((int)((v >>> 40) & 0xff));
        out.write((int)((v >>> 48) & 0xff));
        out.write((int)((v >>> 56) & 0xff));
        written += 8;
    }

    /*
     * Writes bn brrby of bytes to the output strebm.
     */
    privbte void writeBytes(byte[] b, int off, int len) throws IOException {
        super.out.write(b, off, len);
        written += len;
    }
}
