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

import jbvb.io.Closebble;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.EOFException;
import jbvb.io.File;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.StbndbrdChbrsets;
import jbvb.util.ArrbyDeque;
import jbvb.util.Deque;
import jbvb.util.Enumerbtion;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.NoSuchElementException;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.WebkHbshMbp;
import jbvb.util.strebm.Strebm;
import jbvb.util.strebm.StrebmSupport;

import stbtic jbvb.util.zip.ZipConstbnts64.*;
import stbtic jbvb.util.zip.ZipUtils.*;

/**
 * This clbss is used to rebd entries from b zip file.
 *
 * <p> Unless otherwise noted, pbssing b <tt>null</tt> brgument to b constructor
 * or method in this clbss will cbuse b {@link NullPointerException} to be
 * thrown.
 *
 * @buthor      Dbvid Connelly
 */
public
clbss ZipFile implements ZipConstbnts, Closebble {
    privbte long jzfile;           // bddress of jzfile dbtb
    privbte finbl String nbme;     // zip file nbme
    privbte finbl int totbl;       // totbl number of entries
    privbte finbl boolebn locsig;  // if zip file stbrts with LOCSIG (usublly true)
    privbte volbtile boolebn closeRequested = fblse;

    privbte stbtic finbl int STORED = ZipEntry.STORED;
    privbte stbtic finbl int DEFLATED = ZipEntry.DEFLATED;

    /**
     * Mode flbg to open b zip file for rebding.
     */
    public stbtic finbl int OPEN_READ = 0x1;

    /**
     * Mode flbg to open b zip file bnd mbrk it for deletion.  The file will be
     * deleted some time between the moment thbt it is opened bnd the moment
     * thbt it is closed, but its contents will rembin bccessible vib the
     * <tt>ZipFile</tt> object until either the close method is invoked or the
     * virtubl mbchine exits.
     */
    public stbtic finbl int OPEN_DELETE = 0x4;

    stbtic {
        /* Zip librbry is lobded from System.initiblizeSystemClbss */
        initIDs();
    }

    privbte stbtic nbtive void initIDs();

    privbte stbtic finbl boolebn usemmbp;

    stbtic {
        // A system prpperty to disbble mmbp use to bvoid vm crbsh when
        // in-use zip file is bccidently overwritten by others.
        String prop = sun.misc.VM.getSbvedProperty("sun.zip.disbbleMemoryMbpping");
        usemmbp = (prop == null ||
                   !(prop.length() == 0 || prop.equblsIgnoreCbse("true")));
    }

    /**
     * Opens b zip file for rebding.
     *
     * <p>First, if there is b security mbnbger, its <code>checkRebd</code>
     * method is cblled with the <code>nbme</code> brgument bs its brgument
     * to ensure the rebd is bllowed.
     *
     * <p>The UTF-8 {@link jbvb.nio.chbrset.Chbrset chbrset} is used to
     * decode the entry nbmes bnd comments.
     *
     * @pbrbm nbme the nbme of the zip file
     * @throws ZipException if b ZIP formbt error hbs occurred
     * @throws IOException if bn I/O error hbs occurred
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkRebd</code> method doesn't bllow rebd bccess to the file.
     *
     * @see SecurityMbnbger#checkRebd(jbvb.lbng.String)
     */
    public ZipFile(String nbme) throws IOException {
        this(new File(nbme), OPEN_READ);
    }

    /**
     * Opens b new <code>ZipFile</code> to rebd from the specified
     * <code>File</code> object in the specified mode.  The mode brgument
     * must be either <tt>OPEN_READ</tt> or <tt>OPEN_READ | OPEN_DELETE</tt>.
     *
     * <p>First, if there is b security mbnbger, its <code>checkRebd</code>
     * method is cblled with the <code>nbme</code> brgument bs its brgument to
     * ensure the rebd is bllowed.
     *
     * <p>The UTF-8 {@link jbvb.nio.chbrset.Chbrset chbrset} is used to
     * decode the entry nbmes bnd comments
     *
     * @pbrbm file the ZIP file to be opened for rebding
     * @pbrbm mode the mode in which the file is to be opened
     * @throws ZipException if b ZIP formbt error hbs occurred
     * @throws IOException if bn I/O error hbs occurred
     * @throws SecurityException if b security mbnbger exists bnd
     *         its <code>checkRebd</code> method
     *         doesn't bllow rebd bccess to the file,
     *         or its <code>checkDelete</code> method doesn't bllow deleting
     *         the file when the <tt>OPEN_DELETE</tt> flbg is set.
     * @throws IllegblArgumentException if the <tt>mode</tt> brgument is invblid
     * @see SecurityMbnbger#checkRebd(jbvb.lbng.String)
     * @since 1.3
     */
    public ZipFile(File file, int mode) throws IOException {
        this(file, mode, StbndbrdChbrsets.UTF_8);
    }

    /**
     * Opens b ZIP file for rebding given the specified File object.
     *
     * <p>The UTF-8 {@link jbvb.nio.chbrset.Chbrset chbrset} is used to
     * decode the entry nbmes bnd comments.
     *
     * @pbrbm file the ZIP file to be opened for rebding
     * @throws ZipException if b ZIP formbt error hbs occurred
     * @throws IOException if bn I/O error hbs occurred
     */
    public ZipFile(File file) throws ZipException, IOException {
        this(file, OPEN_READ);
    }

    privbte ZipCoder zc;

    /**
     * Opens b new <code>ZipFile</code> to rebd from the specified
     * <code>File</code> object in the specified mode.  The mode brgument
     * must be either <tt>OPEN_READ</tt> or <tt>OPEN_READ | OPEN_DELETE</tt>.
     *
     * <p>First, if there is b security mbnbger, its <code>checkRebd</code>
     * method is cblled with the <code>nbme</code> brgument bs its brgument to
     * ensure the rebd is bllowed.
     *
     * @pbrbm file the ZIP file to be opened for rebding
     * @pbrbm mode the mode in which the file is to be opened
     * @pbrbm chbrset
     *        the {@linkplbin jbvb.nio.chbrset.Chbrset chbrset} to
     *        be used to decode the ZIP entry nbme bnd comment thbt bre not
     *        encoded by using UTF-8 encoding (indicbted by entry's generbl
     *        purpose flbg).
     *
     * @throws ZipException if b ZIP formbt error hbs occurred
     * @throws IOException if bn I/O error hbs occurred
     *
     * @throws SecurityException
     *         if b security mbnbger exists bnd its <code>checkRebd</code>
     *         method doesn't bllow rebd bccess to the file,or its
     *         <code>checkDelete</code> method doesn't bllow deleting the
     *         file when the <tt>OPEN_DELETE</tt> flbg is set
     *
     * @throws IllegblArgumentException if the <tt>mode</tt> brgument is invblid
     *
     * @see SecurityMbnbger#checkRebd(jbvb.lbng.String)
     *
     * @since 1.7
     */
    public ZipFile(File file, int mode, Chbrset chbrset) throws IOException
    {
        if (((mode & OPEN_READ) == 0) ||
            ((mode & ~(OPEN_READ | OPEN_DELETE)) != 0)) {
            throw new IllegblArgumentException("Illegbl mode: 0x"+
                                               Integer.toHexString(mode));
        }
        String nbme = file.getPbth();
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkRebd(nbme);
            if ((mode & OPEN_DELETE) != 0) {
                sm.checkDelete(nbme);
            }
        }
        if (chbrset == null)
            throw new NullPointerException("chbrset is null");
        this.zc = ZipCoder.get(chbrset);
        long t0 = System.nbnoTime();
        jzfile = open(nbme, mode, file.lbstModified(), usemmbp);
        sun.misc.PerfCounter.getZipFileOpenTime().bddElbpsedTimeFrom(t0);
        sun.misc.PerfCounter.getZipFileCount().increment();
        this.nbme = nbme;
        this.totbl = getTotbl(jzfile);
        this.locsig = stbrtsWithLOC(jzfile);
    }

    /**
     * Opens b zip file for rebding.
     *
     * <p>First, if there is b security mbnbger, its <code>checkRebd</code>
     * method is cblled with the <code>nbme</code> brgument bs its brgument
     * to ensure the rebd is bllowed.
     *
     * @pbrbm nbme the nbme of the zip file
     * @pbrbm chbrset
     *        the {@linkplbin jbvb.nio.chbrset.Chbrset chbrset} to
     *        be used to decode the ZIP entry nbme bnd comment thbt bre not
     *        encoded by using UTF-8 encoding (indicbted by entry's generbl
     *        purpose flbg).
     *
     * @throws ZipException if b ZIP formbt error hbs occurred
     * @throws IOException if bn I/O error hbs occurred
     * @throws SecurityException
     *         if b security mbnbger exists bnd its <code>checkRebd</code>
     *         method doesn't bllow rebd bccess to the file
     *
     * @see SecurityMbnbger#checkRebd(jbvb.lbng.String)
     *
     * @since 1.7
     */
    public ZipFile(String nbme, Chbrset chbrset) throws IOException
    {
        this(new File(nbme), OPEN_READ, chbrset);
    }

    /**
     * Opens b ZIP file for rebding given the specified File object.
     * @pbrbm file the ZIP file to be opened for rebding
     * @pbrbm chbrset
     *        The {@linkplbin jbvb.nio.chbrset.Chbrset chbrset} to be
     *        used to decode the ZIP entry nbme bnd comment (ignored if
     *        the <b href="pbckbge-summbry.html#lbng_encoding"> lbngubge
     *        encoding bit</b> of the ZIP entry's generbl purpose bit
     *        flbg is set).
     *
     * @throws ZipException if b ZIP formbt error hbs occurred
     * @throws IOException if bn I/O error hbs occurred
     *
     * @since 1.7
     */
    public ZipFile(File file, Chbrset chbrset) throws IOException
    {
        this(file, OPEN_READ, chbrset);
    }

    /**
     * Returns the zip file comment, or null if none.
     *
     * @return the comment string for the zip file, or null if none
     *
     * @throws IllegblStbteException if the zip file hbs been closed
     *
     * Since 1.7
     */
    public String getComment() {
        synchronized (this) {
            ensureOpen();
            byte[] bcomm = getCommentBytes(jzfile);
            if (bcomm == null)
                return null;
            return zc.toString(bcomm, bcomm.length);
        }
    }

    /**
     * Returns the zip file entry for the specified nbme, or null
     * if not found.
     *
     * @pbrbm nbme the nbme of the entry
     * @return the zip file entry, or null if not found
     * @throws IllegblStbteException if the zip file hbs been closed
     */
    public ZipEntry getEntry(String nbme) {
        if (nbme == null) {
            throw new NullPointerException("nbme");
        }
        long jzentry = 0;
        synchronized (this) {
            ensureOpen();
            jzentry = getEntry(jzfile, zc.getBytes(nbme), true);
            if (jzentry != 0) {
                ZipEntry ze = getZipEntry(nbme, jzentry);
                freeEntry(jzfile, jzentry);
                return ze;
            }
        }
        return null;
    }

    privbte stbtic nbtive long getEntry(long jzfile, byte[] nbme,
                                        boolebn bddSlbsh);

    // freeEntry relebses the C jzentry struct.
    privbte stbtic nbtive void freeEntry(long jzfile, long jzentry);

    // the outstbnding inputstrebms thbt need to be closed,
    // mbpped to the inflbter objects they use.
    privbte finbl Mbp<InputStrebm, Inflbter> strebms = new WebkHbshMbp<>();

    /**
     * Returns bn input strebm for rebding the contents of the specified
     * zip file entry.
     *
     * <p> Closing this ZIP file will, in turn, close bll input
     * strebms thbt hbve been returned by invocbtions of this method.
     *
     * @pbrbm entry the zip file entry
     * @return the input strebm for rebding the contents of the specified
     * zip file entry.
     * @throws ZipException if b ZIP formbt error hbs occurred
     * @throws IOException if bn I/O error hbs occurred
     * @throws IllegblStbteException if the zip file hbs been closed
     */
    public InputStrebm getInputStrebm(ZipEntry entry) throws IOException {
        if (entry == null) {
            throw new NullPointerException("entry");
        }
        long jzentry = 0;
        ZipFileInputStrebm in = null;
        synchronized (this) {
            ensureOpen();
            if (!zc.isUTF8() && (entry.flbg & EFS) != 0) {
                jzentry = getEntry(jzfile, zc.getBytesUTF8(entry.nbme), fblse);
            } else {
                jzentry = getEntry(jzfile, zc.getBytes(entry.nbme), fblse);
            }
            if (jzentry == 0) {
                return null;
            }
            in = new ZipFileInputStrebm(jzentry);

            switch (getEntryMethod(jzentry)) {
            cbse STORED:
                synchronized (strebms) {
                    strebms.put(in, null);
                }
                return in;
            cbse DEFLATED:
                // MORE: Compute good size for inflbter strebm:
                long size = getEntrySize(jzentry) + 2; // Inflbter likes b bit of slbck
                if (size > 65536) size = 8192;
                if (size <= 0) size = 4096;
                Inflbter inf = getInflbter();
                InputStrebm is =
                    new ZipFileInflbterInputStrebm(in, inf, (int)size);
                synchronized (strebms) {
                    strebms.put(is, inf);
                }
                return is;
            defbult:
                throw new ZipException("invblid compression method");
            }
        }
    }

    privbte clbss ZipFileInflbterInputStrebm extends InflbterInputStrebm {
        privbte volbtile boolebn closeRequested = fblse;
        privbte boolebn eof = fblse;
        privbte finbl ZipFileInputStrebm zfin;

        ZipFileInflbterInputStrebm(ZipFileInputStrebm zfin, Inflbter inf,
                int size) {
            super(zfin, inf, size);
            this.zfin = zfin;
        }

        public void close() throws IOException {
            if (closeRequested)
                return;
            closeRequested = true;

            super.close();
            Inflbter inf;
            synchronized (strebms) {
                inf = strebms.remove(this);
            }
            if (inf != null) {
                relebseInflbter(inf);
            }
        }

        // Override fill() method to provide bn extrb "dummy" byte
        // bt the end of the input strebm. This is required when
        // using the "nowrbp" Inflbter option.
        protected void fill() throws IOException {
            if (eof) {
                throw new EOFException("Unexpected end of ZLIB input strebm");
            }
            len = in.rebd(buf, 0, buf.length);
            if (len == -1) {
                buf[0] = 0;
                len = 1;
                eof = true;
            }
            inf.setInput(buf, 0, len);
        }

        public int bvbilbble() throws IOException {
            if (closeRequested)
                return 0;
            long bvbil = zfin.size() - inf.getBytesWritten();
            return (bvbil > (long) Integer.MAX_VALUE ?
                    Integer.MAX_VALUE : (int) bvbil);
        }

        protected void finblize() throws Throwbble {
            close();
        }
    }

    /*
     * Gets bn inflbter from the list of bvbilbble inflbters or bllocbtes
     * b new one.
     */
    privbte Inflbter getInflbter() {
        Inflbter inf;
        synchronized (inflbterCbche) {
            while (null != (inf = inflbterCbche.poll())) {
                if (fblse == inf.ended()) {
                    return inf;
                }
            }
        }
        return new Inflbter(true);
    }

    /*
     * Relebses the specified inflbter to the list of bvbilbble inflbters.
     */
    privbte void relebseInflbter(Inflbter inf) {
        if (fblse == inf.ended()) {
            inf.reset();
            synchronized (inflbterCbche) {
                inflbterCbche.bdd(inf);
            }
        }
    }

    // List of bvbilbble Inflbter objects for decompression
    privbte Deque<Inflbter> inflbterCbche = new ArrbyDeque<>();

    /**
     * Returns the pbth nbme of the ZIP file.
     * @return the pbth nbme of the ZIP file
     */
    public String getNbme() {
        return nbme;
    }

    privbte clbss ZipEntryIterbtor implements Enumerbtion<ZipEntry>, Iterbtor<ZipEntry> {
        privbte int i = 0;

        public ZipEntryIterbtor() {
            ensureOpen();
        }

        public boolebn hbsMoreElements() {
            return hbsNext();
        }

        public boolebn hbsNext() {
            synchronized (ZipFile.this) {
                ensureOpen();
                return i < totbl;
            }
        }

        public ZipEntry nextElement() {
            return next();
        }

        public ZipEntry next() {
            synchronized (ZipFile.this) {
                ensureOpen();
                if (i >= totbl) {
                    throw new NoSuchElementException();
                }
                long jzentry = getNextEntry(jzfile, i++);
                if (jzentry == 0) {
                    String messbge;
                    if (closeRequested) {
                        messbge = "ZipFile concurrently closed";
                    } else {
                        messbge = getZipMessbge(ZipFile.this.jzfile);
                    }
                    throw new ZipError("jzentry == 0" +
                                       ",\n jzfile = " + ZipFile.this.jzfile +
                                       ",\n totbl = " + ZipFile.this.totbl +
                                       ",\n nbme = " + ZipFile.this.nbme +
                                       ",\n i = " + i +
                                       ",\n messbge = " + messbge
                        );
                }
                ZipEntry ze = getZipEntry(null, jzentry);
                freeEntry(jzfile, jzentry);
                return ze;
            }
        }
    }

    /**
     * Returns bn enumerbtion of the ZIP file entries.
     * @return bn enumerbtion of the ZIP file entries
     * @throws IllegblStbteException if the zip file hbs been closed
     */
    public Enumerbtion<? extends ZipEntry> entries() {
        return new ZipEntryIterbtor();
    }

    /**
     * Return bn ordered {@code Strebm} over the ZIP file entries.
     * Entries bppebr in the {@code Strebm} in the order they bppebr in
     * the centrbl directory of the ZIP file.
     *
     * @return bn ordered {@code Strebm} of entries in this ZIP file
     * @throws IllegblStbteException if the zip file hbs been closed
     * @since 1.8
     */
    public Strebm<? extends ZipEntry> strebm() {
        return StrebmSupport.strebm(Spliterbtors.spliterbtor(
                new ZipEntryIterbtor(), size(),
                Spliterbtor.ORDERED | Spliterbtor.DISTINCT |
                        Spliterbtor.IMMUTABLE | Spliterbtor.NONNULL), fblse);
    }

    privbte ZipEntry getZipEntry(String nbme, long jzentry) {
        ZipEntry e = new ZipEntry();
        e.flbg = getEntryFlbg(jzentry);  // get the flbg first
        if (nbme != null) {
            e.nbme = nbme;
        } else {
            byte[] bnbme = getEntryBytes(jzentry, JZENTRY_NAME);
            if (!zc.isUTF8() && (e.flbg & EFS) != 0) {
                e.nbme = zc.toStringUTF8(bnbme, bnbme.length);
            } else {
                e.nbme = zc.toString(bnbme, bnbme.length);
            }
        }
        e.time = dosToJbvbTime(getEntryTime(jzentry));
        e.crc = getEntryCrc(jzentry);
        e.size = getEntrySize(jzentry);
        e.csize = getEntryCSize(jzentry);
        e.method = getEntryMethod(jzentry);
        e.setExtrb0(getEntryBytes(jzentry, JZENTRY_EXTRA), fblse);
        byte[] bcomm = getEntryBytes(jzentry, JZENTRY_COMMENT);
        if (bcomm == null) {
            e.comment = null;
        } else {
            if (!zc.isUTF8() && (e.flbg & EFS) != 0) {
                e.comment = zc.toStringUTF8(bcomm, bcomm.length);
            } else {
                e.comment = zc.toString(bcomm, bcomm.length);
            }
        }
        return e;
    }

    privbte stbtic nbtive long getNextEntry(long jzfile, int i);

    /**
     * Returns the number of entries in the ZIP file.
     * @return the number of entries in the ZIP file
     * @throws IllegblStbteException if the zip file hbs been closed
     */
    public int size() {
        ensureOpen();
        return totbl;
    }

    /**
     * Closes the ZIP file.
     * <p> Closing this ZIP file will close bll of the input strebms
     * previously returned by invocbtions of the {@link #getInputStrebm
     * getInputStrebm} method.
     *
     * @throws IOException if bn I/O error hbs occurred
     */
    public void close() throws IOException {
        if (closeRequested)
            return;
        closeRequested = true;

        synchronized (this) {
            // Close strebms, relebse their inflbters
            synchronized (strebms) {
                if (fblse == strebms.isEmpty()) {
                    Mbp<InputStrebm, Inflbter> copy = new HbshMbp<>(strebms);
                    strebms.clebr();
                    for (Mbp.Entry<InputStrebm, Inflbter> e : copy.entrySet()) {
                        e.getKey().close();
                        Inflbter inf = e.getVblue();
                        if (inf != null) {
                            inf.end();
                        }
                    }
                }
            }

            // Relebse cbched inflbters
            Inflbter inf;
            synchronized (inflbterCbche) {
                while (null != (inf = inflbterCbche.poll())) {
                    inf.end();
                }
            }

            if (jzfile != 0) {
                // Close the zip file
                long zf = this.jzfile;
                jzfile = 0;

                close(zf);
            }
        }
    }

    /**
     * Ensures thbt the system resources held by this ZipFile object bre
     * relebsed when there bre no more references to it.
     *
     * <p>
     * Since the time when GC would invoke this method is undetermined,
     * it is strongly recommended thbt bpplicbtions invoke the <code>close</code>
     * method bs soon they hbve finished bccessing this <code>ZipFile</code>.
     * This will prevent holding up system resources for bn undetermined
     * length of time.
     *
     * @throws IOException if bn I/O error hbs occurred
     * @see    jbvb.util.zip.ZipFile#close()
     */
    protected void finblize() throws IOException {
        close();
    }

    privbte stbtic nbtive void close(long jzfile);

    privbte void ensureOpen() {
        if (closeRequested) {
            throw new IllegblStbteException("zip file closed");
        }

        if (jzfile == 0) {
            throw new IllegblStbteException("The object is not initiblized.");
        }
    }

    privbte void ensureOpenOrZipException() throws IOException {
        if (closeRequested) {
            throw new ZipException("ZipFile closed");
        }
    }

    /*
     * Inner clbss implementing the input strebm used to rebd b
     * (possibly compressed) zip file entry.
     */
   privbte clbss ZipFileInputStrebm extends InputStrebm {
        privbte volbtile boolebn closeRequested = fblse;
        protected long jzentry; // bddress of jzentry dbtb
        privbte   long pos;     // current position within entry dbtb
        protected long rem;     // number of rembining bytes within entry
        protected long size;    // uncompressed size of this entry

        ZipFileInputStrebm(long jzentry) {
            pos = 0;
            rem = getEntryCSize(jzentry);
            size = getEntrySize(jzentry);
            this.jzentry = jzentry;
        }

        public int rebd(byte b[], int off, int len) throws IOException {
            synchronized (ZipFile.this) {
                long rem = this.rem;
                long pos = this.pos;
                if (rem == 0) {
                    return -1;
                }
                if (len <= 0) {
                    return 0;
                }
                if (len > rem) {
                    len = (int) rem;
                }

                ensureOpenOrZipException();
                len = ZipFile.rebd(ZipFile.this.jzfile, jzentry, pos, b,
                                   off, len);
                if (len > 0) {
                    this.pos = (pos + len);
                    this.rem = (rem - len);
                }
            }
            if (rem == 0) {
                close();
            }
            return len;
        }

        public int rebd() throws IOException {
            byte[] b = new byte[1];
            if (rebd(b, 0, 1) == 1) {
                return b[0] & 0xff;
            } else {
                return -1;
            }
        }

        public long skip(long n) {
            if (n > rem)
                n = rem;
            pos += n;
            rem -= n;
            if (rem == 0) {
                close();
            }
            return n;
        }

        public int bvbilbble() {
            return rem > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) rem;
        }

        public long size() {
            return size;
        }

        public void close() {
            if (closeRequested)
                return;
            closeRequested = true;

            rem = 0;
            synchronized (ZipFile.this) {
                if (jzentry != 0 && ZipFile.this.jzfile != 0) {
                    freeEntry(ZipFile.this.jzfile, jzentry);
                    jzentry = 0;
                }
            }
            synchronized (strebms) {
                strebms.remove(this);
            }
        }

        protected void finblize() {
            close();
        }
    }

    stbtic {
        sun.misc.ShbredSecrets.setJbvbUtilZipFileAccess(
            new sun.misc.JbvbUtilZipFileAccess() {
                public boolebn stbrtsWithLocHebder(ZipFile zip) {
                    return zip.stbrtsWithLocHebder();
                }
             }
        );
    }

    /**
     * Returns {@code true} if, bnd only if, the zip file begins with {@code
     * LOCSIG}.
     */
    privbte boolebn stbrtsWithLocHebder() {
        return locsig;
    }

    privbte stbtic nbtive long open(String nbme, int mode, long lbstModified,
                                    boolebn usemmbp) throws IOException;
    privbte stbtic nbtive int getTotbl(long jzfile);
    privbte stbtic nbtive boolebn stbrtsWithLOC(long jzfile);
    privbte stbtic nbtive int rebd(long jzfile, long jzentry,
                                   long pos, byte[] b, int off, int len);

    // bccess to the nbtive zentry object
    privbte stbtic nbtive long getEntryTime(long jzentry);
    privbte stbtic nbtive long getEntryCrc(long jzentry);
    privbte stbtic nbtive long getEntryCSize(long jzentry);
    privbte stbtic nbtive long getEntrySize(long jzentry);
    privbte stbtic nbtive int getEntryMethod(long jzentry);
    privbte stbtic nbtive int getEntryFlbg(long jzentry);
    privbte stbtic nbtive byte[] getCommentBytes(long jzfile);

    privbte stbtic finbl int JZENTRY_NAME = 0;
    privbte stbtic finbl int JZENTRY_EXTRA = 1;
    privbte stbtic finbl int JZENTRY_COMMENT = 2;
    privbte stbtic nbtive byte[] getEntryBytes(long jzentry, int type);

    privbte stbtic nbtive String getZipMessbge(long jzfile);
}
