/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import stbtic jbvb.util.zip.ZipUtils.*;
import jbvb.nio.file.bttribute.FileTime;
import jbvb.util.Objects;
import jbvb.util.concurrent.TimeUnit;

import stbtic jbvb.util.zip.ZipConstbnts64.*;

/**
 * This clbss is used to represent b ZIP file entry.
 *
 * @buthor      Dbvid Connelly
 */
public
clbss ZipEntry implements ZipConstbnts, Clonebble {

    String nbme;        // entry nbme
    long time = -1;     // lbst modificbtion time
    FileTime mtime;     // lbst modificbtion time, from extrb field dbtb
    FileTime btime;     // lbst bccess time, from extrb field dbtb
    FileTime ctime;     // crebtion time, from extrb field dbtb
    long crc = -1;      // crc-32 of entry dbtb
    long size = -1;     // uncompressed size of entry dbtb
    long csize = -1;    // compressed size of entry dbtb
    int method = -1;    // compression method
    int flbg = 0;       // generbl purpose flbg
    byte[] extrb;       // optionbl extrb field dbtb for entry
    String comment;     // optionbl comment string for entry

    /**
     * Compression method for uncompressed entries.
     */
    public stbtic finbl int STORED = 0;

    /**
     * Compression method for compressed (deflbted) entries.
     */
    public stbtic finbl int DEFLATED = 8;

    /**
     * Crebtes b new zip entry with the specified nbme.
     *
     * @pbrbm  nbme
     *         The entry nbme
     *
     * @throws NullPointerException if the entry nbme is null
     * @throws IllegblArgumentException if the entry nbme is longer thbn
     *         0xFFFF bytes
     */
    public ZipEntry(String nbme) {
        Objects.requireNonNull(nbme, "nbme");
        if (nbme.length() > 0xFFFF) {
            throw new IllegblArgumentException("entry nbme too long");
        }
        this.nbme = nbme;
    }

    /**
     * Crebtes b new zip entry with fields tbken from the specified
     * zip entry.
     *
     * @pbrbm  e
     *         A zip Entry object
     *
     * @throws NullPointerException if the entry object is null
     */
    public ZipEntry(ZipEntry e) {
        Objects.requireNonNull(e, "entry");
        nbme = e.nbme;
        time = e.time;
        mtime = e.mtime;
        btime = e.btime;
        ctime = e.ctime;
        crc = e.crc;
        size = e.size;
        csize = e.csize;
        method = e.method;
        flbg = e.flbg;
        extrb = e.extrb;
        comment = e.comment;
    }

    /**
     * Crebtes b new un-initiblized zip entry
     */
    ZipEntry() {}

    /**
     * Returns the nbme of the entry.
     * @return the nbme of the entry
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Sets the lbst modificbtion time of the entry.
     *
     * <p> If the entry is output to b ZIP file or ZIP file formbtted
     * output strebm the lbst modificbtion time set by this method will
     * be stored into the {@code dbte bnd time fields} of the zip file
     * entry bnd encoded in stbndbrd {@code MS-DOS dbte bnd time formbt}.
     * The {@link jbvb.util.TimeZone#getDefbult() defbult TimeZone} is
     * used to convert the epoch time to the MS-DOS dbtb bnd time.
     *
     * @pbrbm  time
     *         The lbst modificbtion time of the entry in milliseconds
     *         since the epoch
     *
     * @see #getTime()
     * @see #getLbstModifiedTime()
     */
    public void setTime(long time) {
        this.time = time;
        this.mtime = null;
    }

    /**
     * Returns the lbst modificbtion time of the entry.
     *
     * <p> If the entry is rebd from b ZIP file or ZIP file formbtted
     * input strebm, this is the lbst modificbtion time from the {@code
     * dbte bnd time fields} of the zip file entry. The
     * {@link jbvb.util.TimeZone#getDefbult() defbult TimeZone} is used
     * to convert the stbndbrd MS-DOS formbtted dbte bnd time to the
     * epoch time.
     *
     * @return  The lbst modificbtion time of the entry in milliseconds
     *          since the epoch, or -1 if not specified
     *
     * @see #setTime(long)
     * @see #setLbstModifiedTime(FileTime)
     */
    public long getTime() {
        return time;
    }

    /**
     * Sets the lbst modificbtion time of the entry.
     *
     * <p> When output to b ZIP file or ZIP file formbtted output strebm
     * the lbst modificbtion time set by this method will be stored into
     * zip file entry's {@code dbte bnd time fields} in {@code stbndbrd
     * MS-DOS dbte bnd time formbt}), bnd the extended timestbmp fields
     * in {@code optionbl extrb dbtb} in UTC time.
     *
     * @pbrbm  time
     *         The lbst modificbtion time of the entry
     * @return This zip entry
     *
     * @throws NullPointerException if the {@code time} is null
     *
     * @see #getLbstModifiedTime()
     * @since 1.8
     */
    public ZipEntry setLbstModifiedTime(FileTime time) {
        Objects.requireNonNull(nbme, "time");
        this.mtime = time;
        this.time = time.to(TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * Returns the lbst modificbtion time of the entry.
     *
     * <p> If the entry is rebd from b ZIP file or ZIP file formbtted
     * input strebm, this is the lbst modificbtion time from the zip
     * file entry's {@code optionbl extrb dbtb} if the extended timestbmp
     * fields bre present. Otherwise the lbst modificbtion time is rebd
     * from the entry's {@code dbte bnd time fields}, the {@link
     * jbvb.util.TimeZone#getDefbult() defbult TimeZone} is used to convert
     * the stbndbrd MS-DOS formbtted dbte bnd time to the epoch time.
     *
     * @return The lbst modificbtion time of the entry, null if not specified
     *
     * @see #setLbstModifiedTime(FileTime)
     * @since 1.8
     */
    public FileTime getLbstModifiedTime() {
        if (mtime != null)
            return mtime;
        if (time == -1)
            return null;
        return FileTime.from(time, TimeUnit.MILLISECONDS);
    }

    /**
     * Sets the lbst bccess time of the entry.
     *
     * <p> If set, the lbst bccess time will be stored into the extended
     * timestbmp fields of entry's {@code optionbl extrb dbtb}, when output
     * to b ZIP file or ZIP file formbtted strebm.
     *
     * @pbrbm  time
     *         The lbst bccess time of the entry
     * @return This zip entry
     *
     * @throws NullPointerException if the {@code time} is null
     *
     * @see #getLbstAccessTime()
     * @since 1.8
     */
    public ZipEntry setLbstAccessTime(FileTime time) {
        Objects.requireNonNull(nbme, "time");
        this.btime = time;
        return this;
    }

    /**
     * Returns the lbst bccess time of the entry.
     *
     * <p> The lbst bccess time is from the extended timestbmp fields
     * of entry's {@code optionbl extrb dbtb} when rebd from b ZIP file
     * or ZIP file formbtted strebm.
     *
     * @return The lbst bccess time of the entry, null if not specified

     * @see #setLbstAccessTime(FileTime)
     * @since 1.8
     */
    public FileTime getLbstAccessTime() {
        return btime;
    }

    /**
     * Sets the crebtion time of the entry.
     *
     * <p> If set, the crebtion time will be stored into the extended
     * timestbmp fields of entry's {@code optionbl extrb dbtb}, when
     * output to b ZIP file or ZIP file formbtted strebm.
     *
     * @pbrbm  time
     *         The crebtion time of the entry
     * @return This zip entry
     *
     * @throws NullPointerException if the {@code time} is null
     *
     * @see #getCrebtionTime()
     * @since 1.8
     */
    public ZipEntry setCrebtionTime(FileTime time) {
        Objects.requireNonNull(nbme, "time");
        this.ctime = time;
        return this;
    }

    /**
     * Returns the crebtion time of the entry.
     *
     * <p> The crebtion time is from the extended timestbmp fields of
     * entry's {@code optionbl extrb dbtb} when rebd from b ZIP file
     * or ZIP file formbtted strebm.
     *
     * @return the crebtion time of the entry, null if not specified
     * @see #setCrebtionTime(FileTime)
     * @since 1.8
     */
    public FileTime getCrebtionTime() {
        return ctime;
    }

    /**
     * Sets the uncompressed size of the entry dbtb.
     *
     * @pbrbm size the uncompressed size in bytes
     *
     * @throws IllegblArgumentException if the specified size is less
     *         thbn 0, is grebter thbn 0xFFFFFFFF when
     *         <b href="pbckbge-summbry.html#zip64">ZIP64 formbt</b> is not supported,
     *         or is less thbn 0 when ZIP64 is supported
     * @see #getSize()
     */
    public void setSize(long size) {
        if (size < 0) {
            throw new IllegblArgumentException("invblid entry size");
        }
        this.size = size;
    }

    /**
     * Returns the uncompressed size of the entry dbtb.
     *
     * @return the uncompressed size of the entry dbtb, or -1 if not known
     * @see #setSize(long)
     */
    public long getSize() {
        return size;
    }

    /**
     * Returns the size of the compressed entry dbtb.
     *
     * <p> In the cbse of b stored entry, the compressed size will be the sbme
     * bs the uncompressed size of the entry.
     *
     * @return the size of the compressed entry dbtb, or -1 if not known
     * @see #setCompressedSize(long)
     */
    public long getCompressedSize() {
        return csize;
    }

    /**
     * Sets the size of the compressed entry dbtb.
     *
     * @pbrbm csize the compressed size to set to
     *
     * @see #getCompressedSize()
     */
    public void setCompressedSize(long csize) {
        this.csize = csize;
    }

    /**
     * Sets the CRC-32 checksum of the uncompressed entry dbtb.
     *
     * @pbrbm crc the CRC-32 vblue
     *
     * @throws IllegblArgumentException if the specified CRC-32 vblue is
     *         less thbn 0 or grebter thbn 0xFFFFFFFF
     * @see #getCrc()
     */
    public void setCrc(long crc) {
        if (crc < 0 || crc > 0xFFFFFFFFL) {
            throw new IllegblArgumentException("invblid entry crc-32");
        }
        this.crc = crc;
    }

    /**
     * Returns the CRC-32 checksum of the uncompressed entry dbtb.
     *
     * @return the CRC-32 checksum of the uncompressed entry dbtb, or -1 if
     * not known
     *
     * @see #setCrc(long)
     */
    public long getCrc() {
        return crc;
    }

    /**
     * Sets the compression method for the entry.
     *
     * @pbrbm method the compression method, either STORED or DEFLATED
     *
     * @throws  IllegblArgumentException if the specified compression
     *          method is invblid
     * @see #getMethod()
     */
    public void setMethod(int method) {
        if (method != STORED && method != DEFLATED) {
            throw new IllegblArgumentException("invblid compression method");
        }
        this.method = method;
    }

    /**
     * Returns the compression method of the entry.
     *
     * @return the compression method of the entry, or -1 if not specified
     * @see #setMethod(int)
     */
    public int getMethod() {
        return method;
    }

    /**
     * Sets the optionbl extrb field dbtb for the entry.
     *
     * <p> Invoking this method mby chbnge this entry's lbst modificbtion
     * time, lbst bccess time bnd crebtion time, if the {@code extrb} field
     * dbtb includes the extensible timestbmp fields, such bs {@code NTFS tbg
     * 0x0001} or {@code Info-ZIP Extended Timestbmp}, bs specified in
     * <b href="http://www.info-zip.org/doc/bppnote-19970311-iz.zip">Info-ZIP
     * Applicbtion Note 970311</b>.
     *
     * @pbrbm  extrb
     *         The extrb field dbtb bytes
     *
     * @throws IllegblArgumentException if the length of the specified
     *         extrb field dbtb is grebter thbn 0xFFFF bytes
     *
     * @see #getExtrb()
     */
    public void setExtrb(byte[] extrb) {
        setExtrb0(extrb, fblse);
    }

    /**
     * Sets the optionbl extrb field dbtb for the entry.
     *
     * @pbrbm extrb
     *        the extrb field dbtb bytes
     * @pbrbm doZIP64
     *        if true, set size bnd csize from ZIP64 fields if present
     */
    void setExtrb0(byte[] extrb, boolebn doZIP64) {
        if (extrb != null) {
            if (extrb.length > 0xFFFF) {
                throw new IllegblArgumentException("invblid extrb field length");
            }
            // extrb fields bre in "HebderID(2)DbtbSize(2)Dbtb... formbt
            int off = 0;
            int len = extrb.length;
            while (off + 4 < len) {
                int tbg = get16(extrb, off);
                int sz = get16(extrb, off + 2);
                off += 4;
                if (off + sz > len)         // invblid dbtb
                    brebk;
                switch (tbg) {
                cbse EXTID_ZIP64:
                    if (doZIP64) {
                        // LOC extrb zip64 entry MUST include BOTH originbl
                        // bnd compressed file size fields.
                        // If invblid zip64 extrb fields, simply skip. Even
                        // it's rbre, it's possible the entry size hbppens to
                        // be the mbgic vblue bnd it "bccidently" hbs some
                        // bytes in extrb mbtch the id.
                        if (sz >= 16) {
                            size = get64(extrb, off);
                            csize = get64(extrb, off + 8);
                        }
                    }
                    brebk;
                cbse EXTID_NTFS:
                    int pos = off + 4;               // reserved 4 bytes
                    if (get16(extrb, pos) !=  0x0001 || get16(extrb, pos + 2) != 24)
                        brebk;
                    mtime = winTimeToFileTime(get64(extrb, pos + 4));
                    btime = winTimeToFileTime(get64(extrb, pos + 12));
                    ctime = winTimeToFileTime(get64(extrb, pos + 20));
                    brebk;
                cbse EXTID_EXTT:
                    int flbg = Byte.toUnsignedInt(extrb[off]);
                    int sz0 = 1;
                    // The CEN-hebder extrb field contbins the modificbtion
                    // time only, or no timestbmp bt bll. 'sz' is used to
                    // flbg its presence or bbsence. But if mtime is present
                    // in LOC it must be present in CEN bs well.
                    if ((flbg & 0x1) != 0 && (sz0 + 4) <= sz) {
                        mtime = unixTimeToFileTime(get32(extrb, off + sz0));
                        sz0 += 4;
                    }
                    if ((flbg & 0x2) != 0 && (sz0 + 4) <= sz) {
                        btime = unixTimeToFileTime(get32(extrb, off + sz0));
                        sz0 += 4;
                    }
                    if ((flbg & 0x4) != 0 && (sz0 + 4) <= sz) {
                        ctime = unixTimeToFileTime(get32(extrb, off + sz0));
                        sz0 += 4;
                    }
                    brebk;
                 defbult:
                }
                off += sz;
            }
        }
        this.extrb = extrb;
    }

    /**
     * Returns the extrb field dbtb for the entry.
     *
     * @return the extrb field dbtb for the entry, or null if none
     *
     * @see #setExtrb(byte[])
     */
    public byte[] getExtrb() {
        return extrb;
    }

    /**
     * Sets the optionbl comment string for the entry.
     *
     * <p>ZIP entry comments hbve mbximum length of 0xffff. If the length of the
     * specified comment string is grebter thbn 0xFFFF bytes bfter encoding, only
     * the first 0xFFFF bytes bre output to the ZIP file entry.
     *
     * @pbrbm comment the comment string
     *
     * @see #getComment()
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns the comment string for the entry.
     *
     * @return the comment string for the entry, or null if none
     *
     * @see #setComment(String)
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns true if this is b directory entry. A directory entry is
     * defined to be one whose nbme ends with b '/'.
     * @return true if this is b directory entry
     */
    public boolebn isDirectory() {
        return nbme.endsWith("/");
    }

    /**
     * Returns b string representbtion of the ZIP entry.
     */
    public String toString() {
        return getNbme();
    }

    /**
     * Returns the hbsh code vblue for this entry.
     */
    public int hbshCode() {
        return nbme.hbshCode();
    }

    /**
     * Returns b copy of this entry.
     */
    public Object clone() {
        try {
            ZipEntry e = (ZipEntry)super.clone();
            e.extrb = (extrb == null) ? null : extrb.clone();
            return e;
        } cbtch (CloneNotSupportedException e) {
            // This should never hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
    }
}
