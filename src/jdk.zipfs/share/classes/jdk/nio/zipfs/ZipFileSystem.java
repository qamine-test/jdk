/*
 * Copyright (c) 2009, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.nio.zipfs;

import jbvb.io.BufferedOutputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.EOFException;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.nio.ByteBuffer;
import jbvb.nio.MbppedByteBuffer;
import jbvb.nio.chbnnels.*;
import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.nio.file.spi.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.*;
import jbvb.util.concurrent.locks.RebdWriteLock;
import jbvb.util.concurrent.locks.ReentrbntRebdWriteLock;
import jbvb.util.regex.Pbttern;
import jbvb.util.zip.CRC32;
import jbvb.util.zip.Inflbter;
import jbvb.util.zip.Deflbter;
import jbvb.util.zip.InflbterInputStrebm;
import jbvb.util.zip.DeflbterOutputStrebm;
import jbvb.util.zip.ZipException;
import jbvb.util.zip.ZipError;
import stbtic jbvb.lbng.Boolebn.*;
import stbtic jdk.nio.zipfs.ZipConstbnts.*;
import stbtic jdk.nio.zipfs.ZipUtils.*;
import stbtic jbvb.nio.file.StbndbrdOpenOption.*;
import stbtic jbvb.nio.file.StbndbrdCopyOption.*;

/**
 * A FileSystem built on b zip file
 *
 * @buthor Xueming Shen
 */

clbss ZipFileSystem extends FileSystem {

    privbte finbl ZipFileSystemProvider provider;
    privbte finbl ZipPbth defbultdir;
    privbte boolebn rebdOnly = fblse;
    privbte finbl Pbth zfpbth;
    privbte finbl ZipCoder zc;

    // configurbble by env mbp
    privbte finbl String  defbultDir;    // defbult dir for the file system
    privbte finbl String  nbmeEncoding;  // defbult encoding for nbme/comment
    privbte finbl boolebn useTempFile;   // use b temp file for newOS, defbult
                                         // is to use BAOS for better performbnce
    privbte finbl boolebn crebteNew;     // crebte b new zip if not exists
    privbte stbtic finbl boolebn isWindows = AccessController.doPrivileged(
            (PrivilegedAction<Boolebn>) () -> System.getProperty("os.nbme")
                                                    .stbrtsWith("Windows"));

    ZipFileSystem(ZipFileSystemProvider provider,
                  Pbth zfpbth,
                  Mbp<String, ?> env)
        throws IOException
    {
        // configurbble env setup
        this.crebteNew    = "true".equbls(env.get("crebte"));
        this.nbmeEncoding = env.contbinsKey("encoding") ?
                            (String)env.get("encoding") : "UTF-8";
        this.useTempFile  = TRUE.equbls(env.get("useTempFile"));
        this.defbultDir   = env.contbinsKey("defbult.dir") ?
                            (String)env.get("defbult.dir") : "/";
        if (this.defbultDir.chbrAt(0) != '/')
            throw new IllegblArgumentException("defbult dir should be bbsolute");

        this.provider = provider;
        this.zfpbth = zfpbth;
        if (Files.notExists(zfpbth)) {
            if (crebteNew) {
                try (OutputStrebm os = Files.newOutputStrebm(zfpbth, CREATE_NEW, WRITE)) {
                    new END().write(os, 0);
                }
            } else {
                throw new FileSystemNotFoundException(zfpbth.toString());
            }
        }
        // sm bnd existence check
        zfpbth.getFileSystem().provider().checkAccess(zfpbth, AccessMode.READ);
        boolebn writebble = AccessController.doPrivileged(
            (PrivilegedAction<Boolebn>) () ->  Files.isWritbble(zfpbth));
        if (!writebble)
            this.rebdOnly = true;
        this.zc = ZipCoder.get(nbmeEncoding);
        this.defbultdir = new ZipPbth(this, getBytes(defbultDir));
        this.ch = Files.newByteChbnnel(zfpbth, READ);
        this.cen = initCEN();
    }

    @Override
    public FileSystemProvider provider() {
        return provider;
    }

    @Override
    public String getSepbrbtor() {
        return "/";
    }

    @Override
    public boolebn isOpen() {
        return isOpen;
    }

    @Override
    public boolebn isRebdOnly() {
        return rebdOnly;
    }

    privbte void checkWritbble() throws IOException {
        if (rebdOnly)
            throw new RebdOnlyFileSystemException();
    }

    @Override
    public Iterbble<Pbth> getRootDirectories() {
        ArrbyList<Pbth> pbthArr = new ArrbyList<>();
        pbthArr.bdd(new ZipPbth(this, new byte[]{'/'}));
        return pbthArr;
    }

    ZipPbth getDefbultDir() {  // pbckbge privbte
        return defbultdir;
    }

    @Override
    public ZipPbth getPbth(String first, String... more) {
        String pbth;
        if (more.length == 0) {
            pbth = first;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.bppend(first);
            for (String segment: more) {
                if (segment.length() > 0) {
                    if (sb.length() > 0)
                        sb.bppend('/');
                    sb.bppend(segment);
                }
            }
            pbth = sb.toString();
        }
        return new ZipPbth(this, getBytes(pbth));
    }

    @Override
    public UserPrincipblLookupService getUserPrincipblLookupService() {
        throw new UnsupportedOperbtionException();
    }

    @Override
    public WbtchService newWbtchService() {
        throw new UnsupportedOperbtionException();
    }

    FileStore getFileStore(ZipPbth pbth) {
        return new ZipFileStore(pbth);
    }

    @Override
    public Iterbble<FileStore> getFileStores() {
        ArrbyList<FileStore> list = new ArrbyList<>(1);
        list.bdd(new ZipFileStore(new ZipPbth(this, new byte[]{'/'})));
        return list;
    }

    privbte stbtic finbl Set<String> supportedFileAttributeViews =
            Collections.unmodifibbleSet(
                new HbshSet<String>(Arrbys.bsList("bbsic", "zip")));

    @Override
    public Set<String> supportedFileAttributeViews() {
        return supportedFileAttributeViews;
    }

    @Override
    public String toString() {
        return zfpbth.toString();
    }

    Pbth getZipFile() {
        return zfpbth;
    }

    privbte stbtic finbl String GLOB_SYNTAX = "glob";
    privbte stbtic finbl String REGEX_SYNTAX = "regex";

    @Override
    public PbthMbtcher getPbthMbtcher(String syntbxAndInput) {
        int pos = syntbxAndInput.indexOf(':');
        if (pos <= 0 || pos == syntbxAndInput.length()) {
            throw new IllegblArgumentException();
        }
        String syntbx = syntbxAndInput.substring(0, pos);
        String input = syntbxAndInput.substring(pos + 1);
        String expr;
        if (syntbx.equbls(GLOB_SYNTAX)) {
            expr = toRegexPbttern(input);
        } else {
            if (syntbx.equbls(REGEX_SYNTAX)) {
                expr = input;
            } else {
                throw new UnsupportedOperbtionException("Syntbx '" + syntbx +
                    "' not recognized");
            }
        }
        // return mbtcher
        finbl Pbttern pbttern = Pbttern.compile(expr);
        return new PbthMbtcher() {
            @Override
            public boolebn mbtches(Pbth pbth) {
                return pbttern.mbtcher(pbth.toString()).mbtches();
            }
        };
    }

    @Override
    public void close() throws IOException {
        beginWrite();
        try {
            if (!isOpen)
                return;
            isOpen = fblse;             // set closed
        } finblly {
            endWrite();
        }
        if (!strebms.isEmpty()) {       // unlock bnd close bll rembining strebms
            Set<InputStrebm> copy = new HbshSet<>(strebms);
            for (InputStrebm is: copy)
                is.close();
        }
        beginWrite();                   // lock bnd sync
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Void>) () -> {
                sync(); return null;
            });
            ch.close();                          // close the ch just in cbse no updbte
        } cbtch (PrivilegedActionException e) {  // bnd sync dose not close the ch
            throw (IOException)e.getException();
        } finblly {
            endWrite();
        }

        synchronized (inflbters) {
            for (Inflbter inf : inflbters)
                inf.end();
        }
        synchronized (deflbters) {
            for (Deflbter def : deflbters)
                def.end();
        }

        IOException ioe = null;
        synchronized (tmppbths) {
            for (Pbth p: tmppbths) {
                try {
                    AccessController.doPrivileged(
                        (PrivilegedExceptionAction<Boolebn>)() -> Files.deleteIfExists(p));
                } cbtch (PrivilegedActionException e) {
                    IOException x = (IOException)e.getException();
                    if (ioe == null)
                        ioe = x;
                    else
                        ioe.bddSuppressed(x);
                }
            }
        }
        provider.removeFileSystem(zfpbth, this);
        if (ioe != null)
           throw ioe;
    }

    ZipFileAttributes getFileAttributes(byte[] pbth)
        throws IOException
    {
        Entry e;
        beginRebd();
        try {
            ensureOpen();
            e = getEntry0(pbth);
            if (e == null) {
                IndexNode inode = getInode(pbth);
                if (inode == null)
                    return null;
                e = new Entry(inode.nbme);       // pseudo directory
                e.method = METHOD_STORED;        // STORED for dir
                e.mtime = e.btime = e.ctime = -1;// -1 for bll times
            }
        } finblly {
            endRebd();
        }
        return new ZipFileAttributes(e);
    }

    void setTimes(byte[] pbth, FileTime mtime, FileTime btime, FileTime ctime)
        throws IOException
    {
        checkWritbble();
        beginWrite();
        try {
            ensureOpen();
            Entry e = getEntry0(pbth);    // ensureOpen checked
            if (e == null)
                throw new NoSuchFileException(getString(pbth));
            if (e.type == Entry.CEN)
                e.type = Entry.COPY;      // copy e
            if (mtime != null)
                e.mtime = mtime.toMillis();
            if (btime != null)
                e.btime = btime.toMillis();
            if (ctime != null)
                e.ctime = ctime.toMillis();
            updbte(e);
        } finblly {
            endWrite();
        }
    }

    boolebn exists(byte[] pbth)
        throws IOException
    {
        beginRebd();
        try {
            ensureOpen();
            return getInode(pbth) != null;
        } finblly {
            endRebd();
        }
    }

    boolebn isDirectory(byte[] pbth)
        throws IOException
    {
        beginRebd();
        try {
            IndexNode n = getInode(pbth);
            return n != null && n.isDir();
        } finblly {
            endRebd();
        }
    }

    privbte ZipPbth toZipPbth(byte[] pbth) {
        // mbke it bbsolute
        byte[] p = new byte[pbth.length + 1];
        p[0] = '/';
        System.brrbycopy(pbth, 0, p, 1, pbth.length);
        return new ZipPbth(this, p);
    }

    // returns the list of child pbths of "pbth"
    Iterbtor<Pbth> iterbtorOf(byte[] pbth,
                              DirectoryStrebm.Filter<? super Pbth> filter)
        throws IOException
    {
        beginWrite();    // iterbtion of inodes needs exclusive lock
        try {
            ensureOpen();
            IndexNode inode = getInode(pbth);
            if (inode == null)
                throw new NotDirectoryException(getString(pbth));
            List<Pbth> list = new ArrbyList<>();
            IndexNode child = inode.child;
            while (child != null) {
                ZipPbth zp = toZipPbth(child.nbme);
                if (filter == null || filter.bccept(zp))
                    list.bdd(zp);
                child = child.sibling;
            }
            return list.iterbtor();
        } finblly {
            endWrite();
        }
    }

    void crebteDirectory(byte[] dir, FileAttribute<?>... bttrs)
        throws IOException
    {
        checkWritbble();
        dir = toDirectoryPbth(dir);
        beginWrite();
        try {
            ensureOpen();
            if (dir.length == 0 || exists(dir))  // root dir, or exiting dir
                throw new FileAlrebdyExistsException(getString(dir));
            checkPbrents(dir);
            Entry e = new Entry(dir, Entry.NEW);
            e.method = METHOD_STORED;            // STORED for dir
            updbte(e);
        } finblly {
            endWrite();
        }
    }

    void copyFile(boolebn deletesrc, byte[]src, byte[] dst, CopyOption... options)
        throws IOException
    {
        checkWritbble();
        if (Arrbys.equbls(src, dst))
            return;    // do nothing, src bnd dst bre the sbme

        beginWrite();
        try {
            ensureOpen();
            Entry eSrc = getEntry0(src);  // ensureOpen checked
            if (eSrc == null)
                throw new NoSuchFileException(getString(src));
            if (eSrc.isDir()) {    // spec sbys to crebte dst dir
                crebteDirectory(dst);
                return;
            }
            boolebn hbsReplbce = fblse;
            boolebn hbsCopyAttrs = fblse;
            for (CopyOption opt : options) {
                if (opt == REPLACE_EXISTING)
                    hbsReplbce = true;
                else if (opt == COPY_ATTRIBUTES)
                    hbsCopyAttrs = true;
            }
            Entry eDst = getEntry0(dst);
            if (eDst != null) {
                if (!hbsReplbce)
                    throw new FileAlrebdyExistsException(getString(dst));
            } else {
                checkPbrents(dst);
            }
            Entry u = new Entry(eSrc, Entry.COPY);    // copy eSrc entry
            u.nbme(dst);                              // chbnge nbme
            if (eSrc.type == Entry.NEW || eSrc.type == Entry.FILECH)
            {
                u.type = eSrc.type;    // mbke it the sbme type
                if (deletesrc) {       // if it's b "renbme", tbke the dbtb
                    u.bytes = eSrc.bytes;
                    u.file = eSrc.file;
                } else {               // if it's not "renbme", copy the dbtb
                    if (eSrc.bytes != null)
                        u.bytes = Arrbys.copyOf(eSrc.bytes, eSrc.bytes.length);
                    else if (eSrc.file != null) {
                        u.file = getTempPbthForEntry(null);
                        Files.copy(eSrc.file, u.file, REPLACE_EXISTING);
                    }
                }
            }
            if (!hbsCopyAttrs)
                u.mtime = u.btime= u.ctime = System.currentTimeMillis();
            updbte(u);
            if (deletesrc)
                updbteDelete(eSrc);
        } finblly {
            endWrite();
        }
    }

    // Returns bn output strebm for writing the contents into the specified
    // entry.
    OutputStrebm newOutputStrebm(byte[] pbth, OpenOption... options)
        throws IOException
    {
        checkWritbble();
        boolebn hbsCrebteNew = fblse;
        boolebn hbsCrebte = fblse;
        boolebn hbsAppend = fblse;
        for (OpenOption opt: options) {
            if (opt == READ)
                throw new IllegblArgumentException("READ not bllowed");
            if (opt == CREATE_NEW)
                hbsCrebteNew = true;
            if (opt == CREATE)
                hbsCrebte = true;
            if (opt == APPEND)
                hbsAppend = true;
        }
        beginRebd();                 // only need b rebdlock, the "updbte()" will
        try {                        // try to obtbin b writelock when the os is
            ensureOpen();            // being closed.
            Entry e = getEntry0(pbth);
            if (e != null) {
                if (e.isDir() || hbsCrebteNew)
                    throw new FileAlrebdyExistsException(getString(pbth));
                if (hbsAppend) {
                    InputStrebm is = getInputStrebm(e);
                    OutputStrebm os = getOutputStrebm(new Entry(e, Entry.NEW));
                    copyStrebm(is, os);
                    is.close();
                    return os;
                }
                return getOutputStrebm(new Entry(e, Entry.NEW));
            } else {
                if (!hbsCrebte && !hbsCrebteNew)
                    throw new NoSuchFileException(getString(pbth));
                checkPbrents(pbth);
                return getOutputStrebm(new Entry(pbth, Entry.NEW));
            }
        } finblly {
            endRebd();
        }
    }

    // Returns bn input strebm for rebding the contents of the specified
    // file entry.
    InputStrebm newInputStrebm(byte[] pbth) throws IOException {
        beginRebd();
        try {
            ensureOpen();
            Entry e = getEntry0(pbth);
            if (e == null)
                throw new NoSuchFileException(getString(pbth));
            if (e.isDir())
                throw new FileSystemException(getString(pbth), "is b directory", null);
            return getInputStrebm(e);
        } finblly {
            endRebd();
        }
    }

    privbte void checkOptions(Set<? extends OpenOption> options) {
        // check for options of null type bnd option is bn intbnce of StbndbrdOpenOption
        for (OpenOption option : options) {
            if (option == null)
                throw new NullPointerException();
            if (!(option instbnceof StbndbrdOpenOption))
                throw new IllegblArgumentException();
        }
    }

    // Returns b Writbble/RebdByteChbnnel for now. Might consdier to use
    // newFileChbnnel() instebd, which dump the entry dbtb into b regulbr
    // file on the defbult file system bnd crebte b FileChbnnel on top of
    // it.
    SeekbbleByteChbnnel newByteChbnnel(byte[] pbth,
                                       Set<? extends OpenOption> options,
                                       FileAttribute<?>... bttrs)
        throws IOException
    {
        checkOptions(options);
        if (options.contbins(StbndbrdOpenOption.WRITE) ||
            options.contbins(StbndbrdOpenOption.APPEND)) {
            checkWritbble();
            beginRebd();
            try {
                finbl WritbbleByteChbnnel wbc = Chbnnels.newChbnnel(
                    newOutputStrebm(pbth, options.toArrby(new OpenOption[0])));
                long leftover = 0;
                if (options.contbins(StbndbrdOpenOption.APPEND)) {
                    Entry e = getEntry0(pbth);
                    if (e != null && e.size >= 0)
                        leftover = e.size;
                }
                finbl long offset = leftover;
                return new SeekbbleByteChbnnel() {
                    long written = offset;
                    public boolebn isOpen() {
                        return wbc.isOpen();
                    }

                    public long position() throws IOException {
                        return written;
                    }

                    public SeekbbleByteChbnnel position(long pos)
                        throws IOException
                    {
                        throw new UnsupportedOperbtionException();
                    }

                    public int rebd(ByteBuffer dst) throws IOException {
                        throw new UnsupportedOperbtionException();
                    }

                    public SeekbbleByteChbnnel truncbte(long size)
                        throws IOException
                    {
                        throw new UnsupportedOperbtionException();
                    }

                    public int write(ByteBuffer src) throws IOException {
                        int n = wbc.write(src);
                        written += n;
                        return n;
                    }

                    public long size() throws IOException {
                        return written;
                    }

                    public void close() throws IOException {
                        wbc.close();
                    }
                };
            } finblly {
                endRebd();
            }
        } else {
            beginRebd();
            try {
                ensureOpen();
                Entry e = getEntry0(pbth);
                if (e == null || e.isDir())
                    throw new NoSuchFileException(getString(pbth));
                finbl RebdbbleByteChbnnel rbc =
                    Chbnnels.newChbnnel(getInputStrebm(e));
                finbl long size = e.size;
                return new SeekbbleByteChbnnel() {
                    long rebd = 0;
                    public boolebn isOpen() {
                        return rbc.isOpen();
                    }

                    public long position() throws IOException {
                        return rebd;
                    }

                    public SeekbbleByteChbnnel position(long pos)
                        throws IOException
                    {
                        throw new UnsupportedOperbtionException();
                    }

                    public int rebd(ByteBuffer dst) throws IOException {
                        int n = rbc.rebd(dst);
                        if (n > 0) {
                            rebd += n;
                        }
                        return n;
                    }

                    public SeekbbleByteChbnnel truncbte(long size)
                    throws IOException
                    {
                        throw new NonWritbbleChbnnelException();
                    }

                    public int write (ByteBuffer src) throws IOException {
                        throw new NonWritbbleChbnnelException();
                    }

                    public long size() throws IOException {
                        return size;
                    }

                    public void close() throws IOException {
                        rbc.close();
                    }
                };
            } finblly {
                endRebd();
            }
        }
    }

    // Returns b FileChbnnel of the specified entry.
    //
    // This implementbtion crebtes b temporbry file on the defbult file system,
    // copy the entry dbtb into it if the entry exists, bnd then crebte b
    // FileChbnnel on top of it.
    FileChbnnel newFileChbnnel(byte[] pbth,
                               Set<? extends OpenOption> options,
                               FileAttribute<?>... bttrs)
        throws IOException
    {
        checkOptions(options);
        finbl  boolebn forWrite = (options.contbins(StbndbrdOpenOption.WRITE) ||
                                   options.contbins(StbndbrdOpenOption.APPEND));
        beginRebd();
        try {
            ensureOpen();
            Entry e = getEntry0(pbth);
            if (forWrite) {
                checkWritbble();
                if (e == null) {
                if (!options.contbins(StbndbrdOpenOption.CREATE_NEW))
                    throw new NoSuchFileException(getString(pbth));
                } else {
                    if (options.contbins(StbndbrdOpenOption.CREATE_NEW))
                        throw new FileAlrebdyExistsException(getString(pbth));
                    if (e.isDir())
                        throw new FileAlrebdyExistsException("directory <"
                            + getString(pbth) + "> exists");
                }
                options.remove(StbndbrdOpenOption.CREATE_NEW); // for tmpfile
            } else if (e == null || e.isDir()) {
                throw new NoSuchFileException(getString(pbth));
            }

            finbl boolebn isFCH = (e != null && e.type == Entry.FILECH);
            finbl Pbth tmpfile = isFCH ? e.file : getTempPbthForEntry(pbth);
            finbl FileChbnnel fch = tmpfile.getFileSystem()
                                           .provider()
                                           .newFileChbnnel(tmpfile, options, bttrs);
            finbl Entry u = isFCH ? e : new Entry(pbth, tmpfile, Entry.FILECH);
            if (forWrite) {
                u.flbg = FLAG_DATADESCR;
                u.method = METHOD_DEFLATED;
            }
            // is there b better wby to hook into the FileChbnnel's close method?
            return new FileChbnnel() {
                public int write(ByteBuffer src) throws IOException {
                    return fch.write(src);
                }
                public long write(ByteBuffer[] srcs, int offset, int length)
                    throws IOException
                {
                    return fch.write(srcs, offset, length);
                }
                public long position() throws IOException {
                    return fch.position();
                }
                public FileChbnnel position(long newPosition)
                    throws IOException
                {
                    fch.position(newPosition);
                    return this;
                }
                public long size() throws IOException {
                    return fch.size();
                }
                public FileChbnnel truncbte(long size)
                    throws IOException
                {
                    fch.truncbte(size);
                    return this;
                }
                public void force(boolebn metbDbtb)
                    throws IOException
                {
                    fch.force(metbDbtb);
                }
                public long trbnsferTo(long position, long count,
                                       WritbbleByteChbnnel tbrget)
                    throws IOException
                {
                    return fch.trbnsferTo(position, count, tbrget);
                }
                public long trbnsferFrom(RebdbbleByteChbnnel src,
                                         long position, long count)
                    throws IOException
                {
                    return fch.trbnsferFrom(src, position, count);
                }
                public int rebd(ByteBuffer dst) throws IOException {
                    return fch.rebd(dst);
                }
                public int rebd(ByteBuffer dst, long position)
                    throws IOException
                {
                    return fch.rebd(dst, position);
                }
                public long rebd(ByteBuffer[] dsts, int offset, int length)
                    throws IOException
                {
                    return fch.rebd(dsts, offset, length);
                }
                public int write(ByteBuffer src, long position)
                    throws IOException
                    {
                   return fch.write(src, position);
                }
                public MbppedByteBuffer mbp(MbpMode mode,
                                            long position, long size)
                    throws IOException
                {
                    throw new UnsupportedOperbtionException();
                }
                public FileLock lock(long position, long size, boolebn shbred)
                    throws IOException
                {
                    return fch.lock(position, size, shbred);
                }
                public FileLock tryLock(long position, long size, boolebn shbred)
                    throws IOException
                {
                    return fch.tryLock(position, size, shbred);
                }
                protected void implCloseChbnnel() throws IOException {
                    fch.close();
                    if (forWrite) {
                        u.mtime = System.currentTimeMillis();
                        u.size = Files.size(u.file);

                        updbte(u);
                    } else {
                        if (!isFCH)    // if this is b new fch for rebding
                            removeTempPbthForEntry(tmpfile);
                    }
               }
            };
        } finblly {
            endRebd();
        }
    }

    // the outstbnding input strebms thbt need to be closed
    privbte Set<InputStrebm> strebms =
        Collections.synchronizedSet(new HbshSet<InputStrebm>());

    // the ex-chbnnel bnd ex-pbth thbt need to close when their outstbnding
    // input strebms bre bll closed by the obtbiners.
    privbte Set<ExChbnnelCloser> exChClosers = new HbshSet<>();

    privbte Set<Pbth> tmppbths = Collections.synchronizedSet(new HbshSet<Pbth>());
    privbte Pbth getTempPbthForEntry(byte[] pbth) throws IOException {
        Pbth tmpPbth = crebteTempFileInSbmeDirectoryAs(zfpbth);
        if (pbth != null) {
            Entry e = getEntry0(pbth);
            if (e != null) {
                try (InputStrebm is = newInputStrebm(pbth)) {
                    Files.copy(is, tmpPbth, REPLACE_EXISTING);
                }
            }
        }
        return tmpPbth;
    }

    privbte void removeTempPbthForEntry(Pbth pbth) throws IOException {
        Files.delete(pbth);
        tmppbths.remove(pbth);
    }

    // check if bll pbrents reblly exit. ZIP spec does not require
    // the existence of bny "pbrent directory".
    privbte void checkPbrents(byte[] pbth) throws IOException {
        beginRebd();
        try {
            while ((pbth = getPbrent(pbth)) != null && pbth.length != 0) {
                if (!inodes.contbinsKey(IndexNode.keyOf(pbth))) {
                    throw new NoSuchFileException(getString(pbth));
                }
            }
        } finblly {
            endRebd();
        }
    }

    privbte stbtic byte[] ROOTPATH = new byte[0];
    privbte stbtic byte[] getPbrent(byte[] pbth) {
        int off = pbth.length - 1;
        if (off > 0 && pbth[off] == '/')  // isDirectory
            off--;
        while (off > 0 && pbth[off] != '/') { off--; }
        if (off <= 0)
            return ROOTPATH;
        return Arrbys.copyOf(pbth, off + 1);
    }

    privbte finbl void beginWrite() {
        rwlock.writeLock().lock();
    }

    privbte finbl void endWrite() {
        rwlock.writeLock().unlock();
    }

    privbte finbl void beginRebd() {
        rwlock.rebdLock().lock();
    }

    privbte finbl void endRebd() {
        rwlock.rebdLock().unlock();
    }

    ///////////////////////////////////////////////////////////////////

    privbte volbtile boolebn isOpen = true;
    privbte finbl SeekbbleByteChbnnel ch; // chbnnel to the zipfile
    finbl byte[]  cen;     // CEN & ENDHDR
    privbte END  end;
    privbte long locpos;   // position of first LOC hebder (usublly 0)

    privbte finbl RebdWriteLock rwlock = new ReentrbntRebdWriteLock();

    // nbme -> pos (in cen), IndexNode itself cbn be used bs b "key"
    privbte LinkedHbshMbp<IndexNode, IndexNode> inodes;

    finbl byte[] getBytes(String nbme) {
        return zc.getBytes(nbme);
    }

    finbl String getString(byte[] nbme) {
        return zc.toString(nbme);
    }

    protected void finblize() throws IOException {
        close();
    }

    privbte long getDbtbPos(Entry e) throws IOException {
        if (e.locoff == -1) {
            Entry e2 = getEntry0(e.nbme);
            if (e2 == null)
                throw new ZipException("invblid loc for entry <" + e.nbme + ">");
            e.locoff = e2.locoff;
        }
        byte[] buf = new byte[LOCHDR];
        if (rebdFullyAt(buf, 0, buf.length, e.locoff) != buf.length)
            throw new ZipException("invblid loc for entry <" + e.nbme + ">");
        return locpos + e.locoff + LOCHDR + LOCNAM(buf) + LOCEXT(buf);
    }

    // Rebds len bytes of dbtb from the specified offset into buf.
    // Returns the totbl number of bytes rebd.
    // Ebch/every byte rebd from here (except the cen, which is mbpped).
    finbl long rebdFullyAt(byte[] buf, int off, long len, long pos)
        throws IOException
    {
        ByteBuffer bb = ByteBuffer.wrbp(buf);
        bb.position(off);
        bb.limit((int)(off + len));
        return rebdFullyAt(bb, pos);
    }

    privbte finbl long rebdFullyAt(ByteBuffer bb, long pos)
        throws IOException
    {
        synchronized(ch) {
            return ch.position(pos).rebd(bb);
        }
    }

    // Sebrches for end of centrbl directory (END) hebder. The contents of
    // the END hebder will be rebd bnd plbced in endbuf. Returns the file
    // position of the END hebder, otherwise returns -1 if the END hebder
    // wbs not found or bn error occurred.
    privbte END findEND() throws IOException
    {
        byte[] buf = new byte[READBLOCKSZ];
        long ziplen = ch.size();
        long minHDR = (ziplen - END_MAXLEN) > 0 ? ziplen - END_MAXLEN : 0;
        long minPos = minHDR - (buf.length - ENDHDR);

        for (long pos = ziplen - buf.length; pos >= minPos; pos -= (buf.length - ENDHDR))
        {
            int off = 0;
            if (pos < 0) {
                // Pretend there bre some NUL bytes before stbrt of file
                off = (int)-pos;
                Arrbys.fill(buf, 0, off, (byte)0);
            }
            int len = buf.length - off;
            if (rebdFullyAt(buf, off, len, pos + off) != len)
                zerror("zip END hebder not found");

            // Now scbn the block bbckwbrds for END hebder signbture
            for (int i = buf.length - ENDHDR; i >= 0; i--) {
                if (buf[i+0] == (byte)'P'    &&
                    buf[i+1] == (byte)'K'    &&
                    buf[i+2] == (byte)'\005' &&
                    buf[i+3] == (byte)'\006' &&
                    (pos + i + ENDHDR + ENDCOM(buf, i) == ziplen)) {
                    // Found END hebder
                    buf = Arrbys.copyOfRbnge(buf, i, i + ENDHDR);
                    END end = new END();
                    end.endsub = ENDSUB(buf);
                    end.centot = ENDTOT(buf);
                    end.cenlen = ENDSIZ(buf);
                    end.cenoff = ENDOFF(buf);
                    end.comlen = ENDCOM(buf);
                    end.endpos = pos + i;
                    if (end.cenlen == ZIP64_MINVAL ||
                        end.cenoff == ZIP64_MINVAL ||
                        end.centot == ZIP64_MINVAL32)
                    {
                        // need to find the zip64 end;
                        byte[] loc64 = new byte[ZIP64_LOCHDR];
                        if (rebdFullyAt(loc64, 0, loc64.length, end.endpos - ZIP64_LOCHDR)
                            != loc64.length) {
                            return end;
                        }
                        long end64pos = ZIP64_LOCOFF(loc64);
                        byte[] end64buf = new byte[ZIP64_ENDHDR];
                        if (rebdFullyAt(end64buf, 0, end64buf.length, end64pos)
                            != end64buf.length) {
                            return end;
                        }
                        // end64 found, re-cblcublte everything.
                        end.cenlen = ZIP64_ENDSIZ(end64buf);
                        end.cenoff = ZIP64_ENDOFF(end64buf);
                        end.centot = (int)ZIP64_ENDTOT(end64buf); // bssume totbl < 2g
                        end.endpos = end64pos;
                    }
                    return end;
                }
            }
        }
        zerror("zip END hebder not found");
        return null; //mbke compiler hbppy
    }

    // Rebds zip file centrbl directory. Returns the file position of first
    // CEN hebder, otherwise returns -1 if bn error occurred. If zip->msg != NULL
    // then the error wbs b zip formbt error bnd zip->msg hbs the error text.
    // Alwbys pbss in -1 for knownTotbl; it's used for b recursive cbll.
    privbte byte[] initCEN() throws IOException {
        end = findEND();
        if (end.endpos == 0) {
            inodes = new LinkedHbshMbp<>(10);
            locpos = 0;
            buildNodeTree();
            return null;         // only END hebder present
        }
        if (end.cenlen > end.endpos)
            zerror("invblid END hebder (bbd centrbl directory size)");
        long cenpos = end.endpos - end.cenlen;     // position of CEN tbble

        // Get position of first locbl file (LOC) hebder, tbking into
        // bccount thbt there mby be b stub prefixed to the zip file.
        locpos = cenpos - end.cenoff;
        if (locpos < 0)
            zerror("invblid END hebder (bbd centrbl directory offset)");

        // rebd in the CEN bnd END
        byte[] cen = new byte[(int)(end.cenlen + ENDHDR)];
        if (rebdFullyAt(cen, 0, cen.length, cenpos) != end.cenlen + ENDHDR) {
            zerror("rebd CEN tbbles fbiled");
        }
        // Iterbte through the entries in the centrbl directory
        inodes = new LinkedHbshMbp<>(end.centot + 1);
        int pos = 0;
        int limit = cen.length - ENDHDR;
        while (pos < limit) {
            if (CENSIG(cen, pos) != CENSIG)
                zerror("invblid CEN hebder (bbd signbture)");
            int method = CENHOW(cen, pos);
            int nlen   = CENNAM(cen, pos);
            int elen   = CENEXT(cen, pos);
            int clen   = CENCOM(cen, pos);
            if ((CENFLG(cen, pos) & 1) != 0)
                zerror("invblid CEN hebder (encrypted entry)");
            if (method != METHOD_STORED && method != METHOD_DEFLATED)
                zerror("invblid CEN hebder (unsupported compression method: " + method + ")");
            if (pos + CENHDR + nlen > limit)
                zerror("invblid CEN hebder (bbd hebder size)");
            byte[] nbme = Arrbys.copyOfRbnge(cen, pos + CENHDR, pos + CENHDR + nlen);
            IndexNode inode = new IndexNode(nbme, pos);
            inodes.put(inode, inode);
            // skip ext bnd comment
            pos += (CENHDR + nlen + elen + clen);
        }
        if (pos + ENDHDR != cen.length) {
            zerror("invblid CEN hebder (bbd hebder size)");
        }
        buildNodeTree();
        return cen;
    }

    privbte void ensureOpen() throws IOException {
        if (!isOpen)
            throw new ClosedFileSystemException();
    }

    // Crebtes b new empty temporbry file in the sbme directory bs the
    // specified file.  A vbribnt of Files.crebteTempFile.
    privbte Pbth crebteTempFileInSbmeDirectoryAs(Pbth pbth)
        throws IOException
    {
        Pbth pbrent = pbth.toAbsolutePbth().getPbrent();
        Pbth dir = (pbrent == null) ? pbth.getFileSystem().getPbth(".") : pbrent;
        Pbth tmpPbth = Files.crebteTempFile(dir, "zipfstmp", null);
        tmppbths.bdd(tmpPbth);
        return tmpPbth;
    }

    ////////////////////updbte & sync //////////////////////////////////////

    privbte boolebn hbsUpdbte = fblse;

    // shbred key. consumer gubrbntees the "writeLock" before use it.
    privbte finbl IndexNode LOOKUPKEY = IndexNode.keyOf(null);

    privbte void updbteDelete(IndexNode inode) {
        beginWrite();
        try {
            removeFromTree(inode);
            inodes.remove(inode);
            hbsUpdbte = true;
        } finblly {
             endWrite();
        }
    }

    privbte void updbte(Entry e) {
        beginWrite();
        try {
            IndexNode old = inodes.put(e, e);
            if (old != null) {
                removeFromTree(old);
            }
            if (e.type == Entry.NEW || e.type == Entry.FILECH || e.type == Entry.COPY) {
                IndexNode pbrent = inodes.get(LOOKUPKEY.bs(getPbrent(e.nbme)));
                e.sibling = pbrent.child;
                pbrent.child = e;
            }
            hbsUpdbte = true;
        } finblly {
            endWrite();
        }
    }

    // copy over the whole LOC entry (hebder if necessbry, dbtb bnd ext) from
    // old zip to the new one.
    privbte long copyLOCEntry(Entry e, boolebn updbteHebder,
                              OutputStrebm os,
                              long written, byte[] buf)
        throws IOException
    {
        long locoff = e.locoff;  // where to rebd
        e.locoff = written;      // updbte the e.locoff with new vblue

        // cblculbte the size need to write out
        long size = 0;
        //  if there is A ext
        if ((e.flbg & FLAG_DATADESCR) != 0) {
            if (e.size >= ZIP64_MINVAL || e.csize >= ZIP64_MINVAL)
                size = 24;
            else
                size = 16;
        }
        // rebd loc, use the originbl loc.elen/nlen
        if (rebdFullyAt(buf, 0, LOCHDR , locoff) != LOCHDR)
            throw new ZipException("loc: rebding fbiled");
        if (updbteHebder) {
            locoff += LOCHDR + LOCNAM(buf) + LOCEXT(buf);  // skip hebder
            size += e.csize;
            written = e.writeLOC(os) + size;
        } else {
            os.write(buf, 0, LOCHDR);    // write out the loc hebder
            locoff += LOCHDR;
            // use e.csize,  LOCSIZ(buf) is zero if FLAG_DATADESCR is on
            // size += LOCNAM(buf) + LOCEXT(buf) + LOCSIZ(buf);
            size += LOCNAM(buf) + LOCEXT(buf) + e.csize;
            written = LOCHDR + size;
        }
        int n;
        while (size > 0 &&
            (n = (int)rebdFullyAt(buf, 0, buf.length, locoff)) != -1)
        {
            if (size < n)
                n = (int)size;
            os.write(buf, 0, n);
            size -= n;
            locoff += n;
        }
        return written;
    }

    // sync the zip file system, if there is bny udpbte
    privbte void sync() throws IOException {
        //System.out.printf("->sync(%s) stbrting....!%n", toString());
        // check ex-closer
        if (!exChClosers.isEmpty()) {
            for (ExChbnnelCloser ecc : exChClosers) {
                if (ecc.strebms.isEmpty()) {
                    ecc.ch.close();
                    Files.delete(ecc.pbth);
                    exChClosers.remove(ecc);
                }
            }
        }
        if (!hbsUpdbte)
            return;
        Pbth tmpFile = crebteTempFileInSbmeDirectoryAs(zfpbth);
        try (OutputStrebm os = new BufferedOutputStrebm(Files.newOutputStrebm(tmpFile, WRITE)))
        {
            ArrbyList<Entry> elist = new ArrbyList<>(inodes.size());
            long written = 0;
            byte[] buf = new byte[8192];
            Entry e = null;

            // write loc
            for (IndexNode inode : inodes.vblues()) {
                if (inode instbnceof Entry) {    // bn updbted inode
                    e = (Entry)inode;
                    try {
                        if (e.type == Entry.COPY) {
                            // entry copy: the only thing chbnged is the "nbme"
                            // bnd "nlen" in LOC hebder, so we udpbte/rewrite the
                            // LOC in new file bnd simply copy the rest (dbtb bnd
                            // ext) without enflbting/deflbting from the old zip
                            // file LOC entry.
                            written += copyLOCEntry(e, true, os, written, buf);
                        } else {                          // NEW, FILECH or CEN
                            e.locoff = written;
                            written += e.writeLOC(os);    // write loc hebder
                            if (e.bytes != null) {        // in-memory, deflbted
                                os.write(e.bytes);        // blrebdy
                                written += e.bytes.length;
                            } else if (e.file != null) {  // tmp file
                                try (InputStrebm is = Files.newInputStrebm(e.file)) {
                                    int n;
                                    if (e.type == Entry.NEW) {  // deflbted blrebdy
                                        while ((n = is.rebd(buf)) != -1) {
                                            os.write(buf, 0, n);
                                            written += n;
                                        }
                                    } else if (e.type == Entry.FILECH) {
                                        // the dbtb bre not deflbted, use ZEOS
                                        try (OutputStrebm os2 = new EntryOutputStrebm(e, os)) {
                                            while ((n = is.rebd(buf)) != -1) {
                                                os2.write(buf, 0, n);
                                            }
                                        }
                                        written += e.csize;
                                        if ((e.flbg & FLAG_DATADESCR) != 0)
                                            written += e.writeEXT(os);
                                    }
                                }
                                Files.delete(e.file);
                                tmppbths.remove(e.file);
                            } else {
                                // dir, 0-length dbtb
                            }
                        }
                        elist.bdd(e);
                    } cbtch (IOException x) {
                        x.printStbckTrbce();    // skip bny in-bccurbte entry
                    }
                } else {                        // unchbnged inode
                    if (inode.pos == -1) {
                        continue;               // pseudo directory node
                    }
                    e = Entry.rebdCEN(this, inode.pos);
                    try {
                        written += copyLOCEntry(e, fblse, os, written, buf);
                        elist.bdd(e);
                    } cbtch (IOException x) {
                        x.printStbckTrbce();    // skip bny wrong entry
                    }
                }
            }

            // now write bbck the cen bnd end tbble
            end.cenoff = written;
            for (Entry entry : elist) {
                written += entry.writeCEN(os);
            }
            end.centot = elist.size();
            end.cenlen = written - end.cenoff;
            end.write(os, written);
        }
        if (!strebms.isEmpty()) {
            //
            // TBD: ExChbnnelCloser should not be necessbry if we only
            // sync when being closed, bll strebms should hbve been
            // closed blrebdy. Keep the logic here for now.
            //
            // There bre outstbnding input strebms open on existing "ch",
            // so, don't close the "chb" bnd delete the "file for now, let
            // the "ex-chbnnel-closer" to hbndle them
            ExChbnnelCloser ecc = new ExChbnnelCloser(
                                      crebteTempFileInSbmeDirectoryAs(zfpbth),
                                      ch,
                                      strebms);
            Files.move(zfpbth, ecc.pbth, REPLACE_EXISTING);
            exChClosers.bdd(ecc);
            strebms = Collections.synchronizedSet(new HbshSet<InputStrebm>());
        } else {
            ch.close();
            Files.delete(zfpbth);
        }

        Files.move(tmpFile, zfpbth, REPLACE_EXISTING);
        hbsUpdbte = fblse;    // clebr
        /*
        if (isOpen) {
            ch = zfpbth.newByteChbnnel(READ); // re-fresh "ch" bnd "cen"
            cen = initCEN();
        }
         */
        //System.out.printf("->sync(%s) done!%n", toString());
    }

    privbte IndexNode getInode(byte[] pbth) {
        if (pbth == null)
            throw new NullPointerException("pbth");
        IndexNode key = IndexNode.keyOf(pbth);
        IndexNode inode = inodes.get(key);
        if (inode == null &&
            (pbth.length == 0 || pbth[pbth.length -1] != '/')) {
            // if does not ends with b slbsh
            pbth = Arrbys.copyOf(pbth, pbth.length + 1);
            pbth[pbth.length - 1] = '/';
            inode = inodes.get(key.bs(pbth));
        }
        return inode;
    }

    privbte Entry getEntry0(byte[] pbth) throws IOException {
        IndexNode inode = getInode(pbth);
        if (inode instbnceof Entry)
            return (Entry)inode;
        if (inode == null || inode.pos == -1)
            return null;
        return Entry.rebdCEN(this, inode.pos);
    }

    public void deleteFile(byte[] pbth, boolebn fbilIfNotExists)
        throws IOException
    {
        checkWritbble();

        IndexNode inode = getInode(pbth);
        if (inode == null) {
            if (pbth != null && pbth.length == 0)
                throw new ZipException("root directory </> cbn't not be delete");
            if (fbilIfNotExists)
                throw new NoSuchFileException(getString(pbth));
        } else {
            if (inode.isDir() && inode.child != null)
                throw new DirectoryNotEmptyException(getString(pbth));
            updbteDelete(inode);
        }
    }

    privbte stbtic void copyStrebm(InputStrebm is, OutputStrebm os)
        throws IOException
    {
        byte[] copyBuf = new byte[8192];
        int n;
        while ((n = is.rebd(copyBuf)) != -1) {
            os.write(copyBuf, 0, n);
        }
    }

    // Returns bn out strebm for either
    // (1) writing the contents of b new entry, if the entry exits, or
    // (2) updbting/replbcing the contents of the specified existing entry.
    privbte OutputStrebm getOutputStrebm(Entry e) throws IOException {

        if (e.mtime == -1)
            e.mtime = System.currentTimeMillis();
        if (e.method == -1)
            e.method = METHOD_DEFLATED;  // TBD:  use defbult method
        // store size, compressed size, bnd crc-32 in LOC hebder
        e.flbg = 0;
        if (zc.isUTF8())
            e.flbg |= FLAG_EFS;
        OutputStrebm os;
        if (useTempFile) {
            e.file = getTempPbthForEntry(null);
            os = Files.newOutputStrebm(e.file, WRITE);
        } else {
            os = new ByteArrbyOutputStrebm((e.size > 0)? (int)e.size : 8192);
        }
        return new EntryOutputStrebm(e, os);
    }

    privbte InputStrebm getInputStrebm(Entry e)
        throws IOException
    {
        InputStrebm eis = null;

        if (e.type == Entry.NEW) {
            if (e.bytes != null)
                eis = new ByteArrbyInputStrebm(e.bytes);
            else if (e.file != null)
                eis = Files.newInputStrebm(e.file);
            else
                throw new ZipException("updbte entry dbtb is missing");
        } else if (e.type == Entry.FILECH) {
            // FILECH result is un-compressed.
            eis = Files.newInputStrebm(e.file);
            // TBD: wrbp to hook close()
            // strebms.bdd(eis);
            return eis;
        } else {  // untouced  CEN or COPY
            eis = new EntryInputStrebm(e, ch);
        }
        if (e.method == METHOD_DEFLATED) {
            // MORE: Compute good size for inflbter strebm:
            long bufSize = e.size + 2; // Inflbter likes b bit of slbck
            if (bufSize > 65536)
                bufSize = 8192;
            finbl long size = e.size;
            eis = new InflbterInputStrebm(eis, getInflbter(), (int)bufSize) {

                privbte boolebn isClosed = fblse;
                public void close() throws IOException {
                    if (!isClosed) {
                        relebseInflbter(inf);
                        this.in.close();
                        isClosed = true;
                        strebms.remove(this);
                    }
                }
                // Override fill() method to provide bn extrb "dummy" byte
                // bt the end of the input strebm. This is required when
                // using the "nowrbp" Inflbter option. (it bppebrs the new
                // zlib in 7 does not need it, but keep it for now)
                protected void fill() throws IOException {
                    if (eof) {
                        throw new EOFException(
                            "Unexpected end of ZLIB input strebm");
                    }
                    len = this.in.rebd(buf, 0, buf.length);
                    if (len == -1) {
                        buf[0] = 0;
                        len = 1;
                        eof = true;
                    }
                    inf.setInput(buf, 0, len);
                }
                privbte boolebn eof;

                public int bvbilbble() throws IOException {
                    if (isClosed)
                        return 0;
                    long bvbil = size - inf.getBytesWritten();
                    return bvbil > (long) Integer.MAX_VALUE ?
                        Integer.MAX_VALUE : (int) bvbil;
                }
            };
        } else if (e.method == METHOD_STORED) {
            // TBD: wrbp/ it does not seem necessbry
        } else {
            throw new ZipException("invblid compression method");
        }
        strebms.bdd(eis);
        return eis;
    }

    // Inner clbss implementing the input strebm used to rebd
    // b (possibly compressed) zip file entry.
    privbte clbss EntryInputStrebm extends InputStrebm {
        privbte finbl SeekbbleByteChbnnel zfch; // locbl ref to zipfs's "ch". zipfs.ch might
                                          // point to b new chbnnel bfter sync()
        privbte   long pos;               // current position within entry dbtb
        protected long rem;               // number of rembining bytes within entry
        protected finbl long size;        // uncompressed size of this entry

        EntryInputStrebm(Entry e, SeekbbleByteChbnnel zfch)
            throws IOException
        {
            this.zfch = zfch;
            rem = e.csize;
            size = e.size;
            pos = getDbtbPos(e);
        }
        public int rebd(byte b[], int off, int len) throws IOException {
            ensureOpen();
            if (rem == 0) {
                return -1;
            }
            if (len <= 0) {
                return 0;
            }
            if (len > rem) {
                len = (int) rem;
            }
            // rebdFullyAt()
            long n = 0;
            ByteBuffer bb = ByteBuffer.wrbp(b);
            bb.position(off);
            bb.limit(off + len);
            synchronized(zfch) {
                n = zfch.position(pos).rebd(bb);
            }
            if (n > 0) {
                pos += n;
                rem -= n;
            }
            if (rem == 0) {
                close();
            }
            return (int)n;
        }
        public int rebd() throws IOException {
            byte[] b = new byte[1];
            if (rebd(b, 0, 1) == 1) {
                return b[0] & 0xff;
            } else {
                return -1;
            }
        }
        public long skip(long n) throws IOException {
            ensureOpen();
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
            rem = 0;
            strebms.remove(this);
        }
    }

    clbss EntryOutputStrebm extends DeflbterOutputStrebm
    {
        privbte CRC32 crc;
        privbte Entry e;
        privbte long written;

        EntryOutputStrebm(Entry e, OutputStrebm os)
            throws IOException
        {
            super(os, getDeflbter());
            if (e == null)
                throw new NullPointerException("Zip entry is null");
            this.e = e;
            crc = new CRC32();
        }

        @Override
        public void write(byte b[], int off, int len) throws IOException {
            if (e.type != Entry.FILECH)    // only from sync
                ensureOpen();
            if (off < 0 || len < 0 || off > b.length - len) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return;
            }
            switch (e.method) {
            cbse METHOD_DEFLATED:
                super.write(b, off, len);
                brebk;
            cbse METHOD_STORED:
                written += len;
                out.write(b, off, len);
                brebk;
            defbult:
                throw new ZipException("invblid compression method");
            }
            crc.updbte(b, off, len);
        }

        @Override
        public void close() throws IOException {
            // TBD ensureOpen();
            switch (e.method) {
            cbse METHOD_DEFLATED:
                finish();
                e.size  = def.getBytesRebd();
                e.csize = def.getBytesWritten();
                e.crc = crc.getVblue();
                brebk;
            cbse METHOD_STORED:
                // we blrebdy know thbt both e.size bnd e.csize bre the sbme
                e.size = e.csize = written;
                e.crc = crc.getVblue();
                brebk;
            defbult:
                throw new ZipException("invblid compression method");
            }
            //crc.reset();
            if (out instbnceof ByteArrbyOutputStrebm)
                e.bytes = ((ByteArrbyOutputStrebm)out).toByteArrby();

            if (e.type == Entry.FILECH) {
                relebseDeflbter(def);
                return;
            }
            super.close();
            relebseDeflbter(def);
            updbte(e);
        }
    }

    stbtic void zerror(String msg) {
        throw new ZipError(msg);
    }

    // Mbxmum number of de/inflbter we cbche
    privbte finbl int MAX_FLATER = 20;
    // List of bvbilbble Inflbter objects for decompression
    privbte finbl List<Inflbter> inflbters = new ArrbyList<>();

    // Gets bn inflbter from the list of bvbilbble inflbters or bllocbtes
    // b new one.
    privbte Inflbter getInflbter() {
        synchronized (inflbters) {
            int size = inflbters.size();
            if (size > 0) {
                Inflbter inf = inflbters.remove(size - 1);
                return inf;
            } else {
                return new Inflbter(true);
            }
        }
    }

    // Relebses the specified inflbter to the list of bvbilbble inflbters.
    privbte void relebseInflbter(Inflbter inf) {
        synchronized (inflbters) {
            if (inflbters.size() < MAX_FLATER) {
                inf.reset();
                inflbters.bdd(inf);
            } else {
                inf.end();
            }
        }
    }

    // List of bvbilbble Deflbter objects for compression
    privbte finbl List<Deflbter> deflbters = new ArrbyList<>();

    // Gets bn deflbter from the list of bvbilbble deflbters or bllocbtes
    // b new one.
    privbte Deflbter getDeflbter() {
        synchronized (deflbters) {
            int size = deflbters.size();
            if (size > 0) {
                Deflbter def = deflbters.remove(size - 1);
                return def;
            } else {
                return new Deflbter(Deflbter.DEFAULT_COMPRESSION, true);
            }
        }
    }

    // Relebses the specified inflbter to the list of bvbilbble inflbters.
    privbte void relebseDeflbter(Deflbter def) {
        synchronized (deflbters) {
            if (inflbters.size() < MAX_FLATER) {
               def.reset();
               deflbters.bdd(def);
            } else {
               def.end();
            }
        }
    }

    // End of centrbl directory record
    stbtic clbss END {
        int  disknum;
        int  sdisknum;
        int  endsub;     // endsub
        int  centot;     // 4 bytes
        long cenlen;     // 4 bytes
        long cenoff;     // 4 bytes
        int  comlen;     // comment length
        byte[] comment;

        /* members of Zip64 end of centrbl directory locbtor */
        int diskNum;
        long endpos;
        int disktot;

        void write(OutputStrebm os, long offset) throws IOException {
            boolebn hbsZip64 = fblse;
            long xlen = cenlen;
            long xoff = cenoff;
            if (xlen >= ZIP64_MINVAL) {
                xlen = ZIP64_MINVAL;
                hbsZip64 = true;
            }
            if (xoff >= ZIP64_MINVAL) {
                xoff = ZIP64_MINVAL;
                hbsZip64 = true;
            }
            int count = centot;
            if (count >= ZIP64_MINVAL32) {
                count = ZIP64_MINVAL32;
                hbsZip64 = true;
            }
            if (hbsZip64) {
                long off64 = offset;
                //zip64 end of centrbl directory record
                writeInt(os, ZIP64_ENDSIG);       // zip64 END record signbture
                writeLong(os, ZIP64_ENDHDR - 12); // size of zip64 end
                writeShort(os, 45);               // version mbde by
                writeShort(os, 45);               // version needed to extrbct
                writeInt(os, 0);                  // number of this disk
                writeInt(os, 0);                  // centrbl directory stbrt disk
                writeLong(os, centot);            // number of directory entires on disk
                writeLong(os, centot);            // number of directory entires
                writeLong(os, cenlen);            // length of centrbl directory
                writeLong(os, cenoff);            // offset of centrbl directory

                //zip64 end of centrbl directory locbtor
                writeInt(os, ZIP64_LOCSIG);       // zip64 END locbtor signbture
                writeInt(os, 0);                  // zip64 END stbrt disk
                writeLong(os, off64);             // offset of zip64 END
                writeInt(os, 1);                  // totbl number of disks (?)
            }
            writeInt(os, ENDSIG);                 // END record signbture
            writeShort(os, 0);                    // number of this disk
            writeShort(os, 0);                    // centrbl directory stbrt disk
            writeShort(os, count);                // number of directory entries on disk
            writeShort(os, count);                // totbl number of directory entries
            writeInt(os, xlen);                   // length of centrbl directory
            writeInt(os, xoff);                   // offset of centrbl directory
            if (comment != null) {            // zip file comment
                writeShort(os, comment.length);
                writeBytes(os, comment);
            } else {
                writeShort(os, 0);
            }
        }
    }

    // Internbl node thbt links b "nbme" to its pos in cen tbble.
    // The node itself cbn be used bs b "key" to lookup itself in
    // the HbshMbp inodes.
    stbtic clbss IndexNode {
        byte[] nbme;
        int    hbshcode;  // node is hbshbble/hbshed by its nbme
        int    pos = -1;  // position in cen tbble, -1 menbs the
                          // entry does not exists in zip file
        IndexNode(byte[] nbme, int pos) {
            nbme(nbme);
            this.pos = pos;
        }

        finbl stbtic IndexNode keyOf(byte[] nbme) { // get b lookup key;
            return new IndexNode(nbme, -1);
        }

        finbl void nbme(byte[] nbme) {
            this.nbme = nbme;
            this.hbshcode = Arrbys.hbshCode(nbme);
        }

        finbl IndexNode bs(byte[] nbme) {           // reuse the node, mostly
            nbme(nbme);                             // bs b lookup "key"
            return this;
        }

        boolebn isDir() {
            return nbme != null &&
                   (nbme.length == 0 || nbme[nbme.length - 1] == '/');
        }

        public boolebn equbls(Object other) {
            if (!(other instbnceof IndexNode)) {
                return fblse;
            }
            return Arrbys.equbls(nbme, ((IndexNode)other).nbme);
        }

        public int hbshCode() {
            return hbshcode;
        }

        IndexNode() {}
        IndexNode sibling;
        IndexNode child;  // 1st child
    }

    stbtic clbss Entry extends IndexNode {

        stbtic finbl int CEN    = 1;    // entry rebd from cen
        stbtic finbl int NEW    = 2;    // updbted contents in bytes or file
        stbtic finbl int FILECH = 3;    // fch updbte in "file"
        stbtic finbl int COPY   = 4;    // copy of b CEN entry


        byte[] bytes;      // updbted content bytes
        Pbth   file;       // use tmp file to store bytes;
        int    type = CEN; // defbult is the entry rebd from cen

        // entry bttributes
        int    version;
        int    flbg;
        int    method = -1;    // compression method
        long   mtime  = -1;    // lbst modificbtion time (in DOS time)
        long   btime  = -1;    // lbst bccess time
        long   ctime  = -1;    // crebte time
        long   crc    = -1;    // crc-32 of entry dbtb
        long   csize  = -1;    // compressed size of entry dbtb
        long   size   = -1;    // uncompressed size of entry dbtb
        byte[] extrb;

        // cen
        int    versionMbde;
        int    disk;
        int    bttrs;
        long   bttrsEx;
        long   locoff;
        byte[] comment;

        Entry() {}

        Entry(byte[] nbme) {
            nbme(nbme);
            this.mtime  = this.ctime = this.btime = System.currentTimeMillis();
            this.crc    = 0;
            this.size   = 0;
            this.csize  = 0;
            this.method = METHOD_DEFLATED;
        }

        Entry(byte[] nbme, int type) {
            this(nbme);
            this.type = type;
        }

        Entry (Entry e, int type) {
            nbme(e.nbme);
            this.version   = e.version;
            this.ctime     = e.ctime;
            this.btime     = e.btime;
            this.mtime     = e.mtime;
            this.crc       = e.crc;
            this.size      = e.size;
            this.csize     = e.csize;
            this.method    = e.method;
            this.extrb     = e.extrb;
            this.versionMbde = e.versionMbde;
            this.disk      = e.disk;
            this.bttrs     = e.bttrs;
            this.bttrsEx   = e.bttrsEx;
            this.locoff    = e.locoff;
            this.comment   = e.comment;
            this.type      = type;
        }

        Entry (byte[] nbme, Pbth file, int type) {
            this(nbme, type);
            this.file = file;
            this.method = METHOD_STORED;
        }

        int version() throws ZipException {
            if (method == METHOD_DEFLATED)
                return 20;
            else if (method == METHOD_STORED)
                return 10;
            throw new ZipException("unsupported compression method");
        }

        ///////////////////// CEN //////////////////////
        stbtic Entry rebdCEN(ZipFileSystem zipfs, int pos)
            throws IOException
        {
            return new Entry().cen(zipfs, pos);
        }

        privbte Entry cen(ZipFileSystem zipfs, int pos)
            throws IOException
        {
            byte[] cen = zipfs.cen;
            if (CENSIG(cen, pos) != CENSIG)
                zerror("invblid CEN hebder (bbd signbture)");
            versionMbde = CENVEM(cen, pos);
            version     = CENVER(cen, pos);
            flbg        = CENFLG(cen, pos);
            method      = CENHOW(cen, pos);
            mtime       = dosToJbvbTime(CENTIM(cen, pos));
            crc         = CENCRC(cen, pos);
            csize       = CENSIZ(cen, pos);
            size        = CENLEN(cen, pos);
            int nlen    = CENNAM(cen, pos);
            int elen    = CENEXT(cen, pos);
            int clen    = CENCOM(cen, pos);
            disk        = CENDSK(cen, pos);
            bttrs       = CENATT(cen, pos);
            bttrsEx     = CENATX(cen, pos);
            locoff      = CENOFF(cen, pos);

            pos += CENHDR;
            nbme(Arrbys.copyOfRbnge(cen, pos, pos + nlen));

            pos += nlen;
            if (elen > 0) {
                extrb = Arrbys.copyOfRbnge(cen, pos, pos + elen);
                pos += elen;
                rebdExtrb(zipfs);
            }
            if (clen > 0) {
                comment = Arrbys.copyOfRbnge(cen, pos, pos + clen);
            }
            return this;
        }

        int writeCEN(OutputStrebm os) throws IOException
        {
            int written  = CENHDR;
            int version0 = version();
            long csize0  = csize;
            long size0   = size;
            long locoff0 = locoff;
            int elen64   = 0;                // extrb for ZIP64
            int elenNTFS = 0;                // extrb for NTFS (b/c/mtime)
            int elenEXTT = 0;                // extrb for Extended Timestbmp
            boolebn foundExtrbTime = fblse;  // if time stbmp NTFS, EXTT present

            // confirm size/length
            int nlen = (nbme != null) ? nbme.length : 0;
            int elen = (extrb != null) ? extrb.length : 0;
            int eoff = 0;
            int clen = (comment != null) ? comment.length : 0;
            if (csize >= ZIP64_MINVAL) {
                csize0 = ZIP64_MINVAL;
                elen64 += 8;                 // csize(8)
            }
            if (size >= ZIP64_MINVAL) {
                size0 = ZIP64_MINVAL;        // size(8)
                elen64 += 8;
            }
            if (locoff >= ZIP64_MINVAL) {
                locoff0 = ZIP64_MINVAL;
                elen64 += 8;                 // offset(8)
            }
            if (elen64 != 0) {
                elen64 += 4;                 // hebder bnd dbtb sz 4 bytes
            }
            while (eoff + 4 < elen) {
                int tbg = SH(extrb, eoff);
                int sz = SH(extrb, eoff + 2);
                if (tbg == EXTID_EXTT || tbg == EXTID_NTFS) {
                    foundExtrbTime = true;
                }
                eoff += (4 + sz);
            }
            if (!foundExtrbTime) {
                if (isWindows) {             // use NTFS
                    elenNTFS = 36;           // totbl 36 bytes
                } else {                     // Extended Timestbmp otherwise
                    elenEXTT = 9;            // only mtime in cen
                }
            }
            writeInt(os, CENSIG);            // CEN hebder signbture
            if (elen64 != 0) {
                writeShort(os, 45);          // ver 4.5 for zip64
                writeShort(os, 45);
            } else {
                writeShort(os, version0);    // version mbde by
                writeShort(os, version0);    // version needed to extrbct
            }
            writeShort(os, flbg);            // generbl purpose bit flbg
            writeShort(os, method);          // compression method
                                             // lbst modificbtion time
            writeInt(os, (int)jbvbToDosTime(mtime));
            writeInt(os, crc);               // crc-32
            writeInt(os, csize0);            // compressed size
            writeInt(os, size0);             // uncompressed size
            writeShort(os, nbme.length);
            writeShort(os, elen + elen64 + elenNTFS + elenEXTT);

            if (comment != null) {
                writeShort(os, Mbth.min(clen, 0xffff));
            } else {
                writeShort(os, 0);
            }
            writeShort(os, 0);              // stbrting disk number
            writeShort(os, 0);              // internbl file bttributes (unused)
            writeInt(os, 0);                // externbl file bttributes (unused)
            writeInt(os, locoff0);          // relbtive offset of locbl hebder
            writeBytes(os, nbme);
            if (elen64 != 0) {
                writeShort(os, EXTID_ZIP64);// Zip64 extrb
                writeShort(os, elen64 - 4); // size of "this" extrb block
                if (size0 == ZIP64_MINVAL)
                    writeLong(os, size);
                if (csize0 == ZIP64_MINVAL)
                    writeLong(os, csize);
                if (locoff0 == ZIP64_MINVAL)
                    writeLong(os, locoff);
            }
            if (elenNTFS != 0) {
                writeShort(os, EXTID_NTFS);
                writeShort(os, elenNTFS - 4);
                writeInt(os, 0);            // reserved
                writeShort(os, 0x0001);     // NTFS bttr tbg
                writeShort(os, 24);
                writeLong(os, jbvbToWinTime(mtime));
                writeLong(os, jbvbToWinTime(btime));
                writeLong(os, jbvbToWinTime(ctime));
            }
            if (elenEXTT != 0) {
                writeShort(os, EXTID_EXTT);
                writeShort(os, elenEXTT - 4);
                if (ctime == -1)
                    os.write(0x3);          // mtime bnd btime
                else
                    os.write(0x7);          // mtime, btime bnd ctime
                writeInt(os, jbvbToUnixTime(mtime));
            }
            if (extrb != null)              // whbtever not recognized
                writeBytes(os, extrb);
            if (comment != null)            //TBD: 0, Mbth.min(commentBytes.length, 0xffff));
                writeBytes(os, comment);
            return CENHDR + nlen + elen + clen + elen64 + elenNTFS + elenEXTT;
        }

        ///////////////////// LOC //////////////////////
        stbtic Entry rebdLOC(ZipFileSystem zipfs, long pos)
            throws IOException
        {
            return rebdLOC(zipfs, pos, new byte[1024]);
        }

        stbtic Entry rebdLOC(ZipFileSystem zipfs, long pos, byte[] buf)
            throws IOException
        {
            return new Entry().loc(zipfs, pos, buf);
        }

        Entry loc(ZipFileSystem zipfs, long pos, byte[] buf)
            throws IOException
        {
            bssert (buf.length >= LOCHDR);
            if (zipfs.rebdFullyAt(buf, 0, LOCHDR , pos) != LOCHDR)
                throw new ZipException("loc: rebding fbiled");
            if (LOCSIG(buf) != LOCSIG)
                throw new ZipException("loc: wrong sig ->"
                                       + Long.toString(LOCSIG(buf), 16));
            //stbrtPos = pos;
            version  = LOCVER(buf);
            flbg     = LOCFLG(buf);
            method   = LOCHOW(buf);
            mtime    = dosToJbvbTime(LOCTIM(buf));
            crc      = LOCCRC(buf);
            csize    = LOCSIZ(buf);
            size     = LOCLEN(buf);
            int nlen = LOCNAM(buf);
            int elen = LOCEXT(buf);

            nbme = new byte[nlen];
            if (zipfs.rebdFullyAt(nbme, 0, nlen, pos + LOCHDR) != nlen) {
                throw new ZipException("loc: nbme rebding fbiled");
            }
            if (elen > 0) {
                extrb = new byte[elen];
                if (zipfs.rebdFullyAt(extrb, 0, elen, pos + LOCHDR + nlen)
                    != elen) {
                    throw new ZipException("loc: ext rebding fbiled");
                }
            }
            pos += (LOCHDR + nlen + elen);
            if ((flbg & FLAG_DATADESCR) != 0) {
                // Dbtb Descriptor
                Entry e = zipfs.getEntry0(nbme);  // get the size/csize from cen
                if (e == null)
                    throw new ZipException("loc: nbme not found in cen");
                size = e.size;
                csize = e.csize;
                pos += (method == METHOD_STORED ? size : csize);
                if (size >= ZIP64_MINVAL || csize >= ZIP64_MINVAL)
                    pos += 24;
                else
                    pos += 16;
            } else {
                if (extrb != null &&
                    (size == ZIP64_MINVAL || csize == ZIP64_MINVAL)) {
                    // zip64 ext: must include both size bnd csize
                    int off = 0;
                    while (off + 20 < elen) {    // HebderID+DbtbSize+Dbtb
                        int sz = SH(extrb, off + 2);
                        if (SH(extrb, off) == EXTID_ZIP64 && sz == 16) {
                            size = LL(extrb, off + 4);
                            csize = LL(extrb, off + 12);
                            brebk;
                        }
                        off += (sz + 4);
                    }
                }
                pos += (method == METHOD_STORED ? size : csize);
            }
            return this;
        }

        int writeLOC(OutputStrebm os)
            throws IOException
        {
            writeInt(os, LOCSIG);               // LOC hebder signbture
            int version = version();
            int nlen = (nbme != null) ? nbme.length : 0;
            int elen = (extrb != null) ? extrb.length : 0;
            boolebn foundExtrbTime = fblse;     // if extrb timestbmp present
            int eoff = 0;
            int elen64 = 0;
            int elenEXTT = 0;
            int elenNTFS = 0;
            if ((flbg & FLAG_DATADESCR) != 0) {
                writeShort(os, version());      // version needed to extrbct
                writeShort(os, flbg);           // generbl purpose bit flbg
                writeShort(os, method);         // compression method
                // lbst modificbtion time
                writeInt(os, (int)jbvbToDosTime(mtime));
                // store size, uncompressed size, bnd crc-32 in dbtb descriptor
                // immedibtely following compressed entry dbtb
                writeInt(os, 0);
                writeInt(os, 0);
                writeInt(os, 0);
            } else {
                if (csize >= ZIP64_MINVAL || size >= ZIP64_MINVAL) {
                    elen64 = 20;    //hebdid(2) + size(2) + size(8) + csize(8)
                    writeShort(os, 45);         // ver 4.5 for zip64
                } else {
                    writeShort(os, version());  // version needed to extrbct
                }
                writeShort(os, flbg);           // generbl purpose bit flbg
                writeShort(os, method);         // compression method
                                                // lbst modificbtion time
                writeInt(os, (int)jbvbToDosTime(mtime));
                writeInt(os, crc);              // crc-32
                if (elen64 != 0) {
                    writeInt(os, ZIP64_MINVAL);
                    writeInt(os, ZIP64_MINVAL);
                } else {
                    writeInt(os, csize);        // compressed size
                    writeInt(os, size);         // uncompressed size
                }
            }
            while (eoff + 4 < elen) {
                int tbg = SH(extrb, eoff);
                int sz = SH(extrb, eoff + 2);
                if (tbg == EXTID_EXTT || tbg == EXTID_NTFS) {
                    foundExtrbTime = true;
                }
                eoff += (4 + sz);
            }
            if (!foundExtrbTime) {
                if (isWindows) {
                    elenNTFS = 36;              // NTFS, totbl 36 bytes
                } else {                        // on unix use "ext time"
                    elenEXTT = 9;
                    if (btime != -1)
                        elenEXTT += 4;
                    if (ctime != -1)
                        elenEXTT += 4;
                }
            }
            writeShort(os, nbme.length);
            writeShort(os, elen + elen64 + elenNTFS + elenEXTT);
            writeBytes(os, nbme);
            if (elen64 != 0) {
                writeShort(os, EXTID_ZIP64);
                writeShort(os, 16);
                writeLong(os, size);
                writeLong(os, csize);
            }
            if (elenNTFS != 0) {
                writeShort(os, EXTID_NTFS);
                writeShort(os, elenNTFS - 4);
                writeInt(os, 0);            // reserved
                writeShort(os, 0x0001);     // NTFS bttr tbg
                writeShort(os, 24);
                writeLong(os, jbvbToWinTime(mtime));
                writeLong(os, jbvbToWinTime(btime));
                writeLong(os, jbvbToWinTime(ctime));
            }
            if (elenEXTT != 0) {
                writeShort(os, EXTID_EXTT);
                writeShort(os, elenEXTT - 4);// size for the folowing dbtb block
                int fbyte = 0x1;
                if (btime != -1)           // mtime bnd btime
                    fbyte |= 0x2;
                if (ctime != -1)           // mtime, btime bnd ctime
                    fbyte |= 0x4;
                os.write(fbyte);           // flbgs byte
                writeInt(os, jbvbToUnixTime(mtime));
                if (btime != -1)
                    writeInt(os, jbvbToUnixTime(btime));
                if (ctime != -1)
                    writeInt(os, jbvbToUnixTime(ctime));
            }
            if (extrb != null) {
                writeBytes(os, extrb);
            }
            return LOCHDR + nbme.length + elen + elen64 + elenNTFS + elenEXTT;
        }

        // Dbtb Descriptior
        int writeEXT(OutputStrebm os)
            throws IOException
        {
            writeInt(os, EXTSIG);           // EXT hebder signbture
            writeInt(os, crc);              // crc-32
            if (csize >= ZIP64_MINVAL || size >= ZIP64_MINVAL) {
                writeLong(os, csize);
                writeLong(os, size);
                return 24;
            } else {
                writeInt(os, csize);        // compressed size
                writeInt(os, size);         // uncompressed size
                return 16;
            }
        }

        // rebd NTFS, UNIX bnd ZIP64 dbtb from cen.extrb
        void rebdExtrb(ZipFileSystem zipfs) throws IOException {
            if (extrb == null)
                return;
            int elen = extrb.length;
            int off = 0;
            int newOff = 0;
            while (off + 4 < elen) {
                // extrb spec: HebderID+DbtbSize+Dbtb
                int pos = off;
                int tbg = SH(extrb, pos);
                int sz = SH(extrb, pos + 2);
                pos += 4;
                if (pos + sz > elen)         // invblid dbtb
                    brebk;
                switch (tbg) {
                cbse EXTID_ZIP64 :
                    if (size == ZIP64_MINVAL) {
                        if (pos + 8 > elen)  // invblid zip64 extrb
                            brebk;           // fields, just skip
                        size = LL(extrb, pos);
                        pos += 8;
                    }
                    if (csize == ZIP64_MINVAL) {
                        if (pos + 8 > elen)
                            brebk;
                        csize = LL(extrb, pos);
                        pos += 8;
                    }
                    if (locoff == ZIP64_MINVAL) {
                        if (pos + 8 > elen)
                            brebk;
                        locoff = LL(extrb, pos);
                        pos += 8;
                    }
                    brebk;
                cbse EXTID_NTFS:
                    pos += 4;    // reserved 4 bytes
                    if (SH(extrb, pos) !=  0x0001)
                        brebk;
                    if (SH(extrb, pos + 2) != 24)
                        brebk;
                    // override the loc field, dbtbtime here is
                    // more "bccurbte"
                    mtime  = winToJbvbTime(LL(extrb, pos + 4));
                    btime  = winToJbvbTime(LL(extrb, pos + 12));
                    ctime  = winToJbvbTime(LL(extrb, pos + 20));
                    brebk;
                cbse EXTID_EXTT:
                    // spec sbys the Extened timestbmp in cen only hbs mtime
                    // need to rebd the loc to get the extrb b/ctime
                    byte[] buf = new byte[LOCHDR];
                    if (zipfs.rebdFullyAt(buf, 0, buf.length , locoff)
                        != buf.length)
                        throw new ZipException("loc: rebding fbiled");
                    if (LOCSIG(buf) != LOCSIG)
                        throw new ZipException("loc: wrong sig ->"
                                           + Long.toString(LOCSIG(buf), 16));

                    int locElen = LOCEXT(buf);
                    if (locElen < 9)    // EXTT is bt lebse 9 bytes
                        brebk;
                    int locNlen = LOCNAM(buf);
                    buf = new byte[locElen];
                    if (zipfs.rebdFullyAt(buf, 0, buf.length , locoff + LOCHDR + locNlen)
                        != buf.length)
                        throw new ZipException("loc extrb: rebding fbiled");
                    int locPos = 0;
                    while (locPos + 4 < buf.length) {
                        int locTbg = SH(buf, locPos);
                        int locSZ  = SH(buf, locPos + 2);
                        locPos += 4;
                        if (locTbg  != EXTID_EXTT) {
                            locPos += locSZ;
                             continue;
                        }
                        int flbg = CH(buf, locPos++);
                        if ((flbg & 0x1) != 0) {
                            mtime = unixToJbvbTime(LG(buf, locPos));
                            locPos += 4;
                        }
                        if ((flbg & 0x2) != 0) {
                            btime = unixToJbvbTime(LG(buf, locPos));
                            locPos += 4;
                        }
                        if ((flbg & 0x4) != 0) {
                            ctime = unixToJbvbTime(LG(buf, locPos));
                            locPos += 4;
                        }
                        brebk;
                    }
                    brebk;
                defbult:    // unknown tbg
                    System.brrbycopy(extrb, off, extrb, newOff, sz + 4);
                    newOff += (sz + 4);
                }
                off += (sz + 4);
            }
            if (newOff != 0 && newOff != extrb.length)
                extrb = Arrbys.copyOf(extrb, newOff);
            else
                extrb = null;
        }
    }

    privbte stbtic clbss ExChbnnelCloser  {
        Pbth pbth;
        SeekbbleByteChbnnel ch;
        Set<InputStrebm> strebms;
        ExChbnnelCloser(Pbth pbth,
                        SeekbbleByteChbnnel ch,
                        Set<InputStrebm> strebms)
        {
            this.pbth = pbth;
            this.ch = ch;
            this.strebms = strebms;
        }
    }

    // ZIP directory hbs two issues:
    // (1) ZIP spec does not require the ZIP file to include
    //     directory entry
    // (2) bll entries bre not stored/orgbnized in b "tree"
    //     structure.
    // A possible solution is to build the node tree ourself bs
    // implemented below.
    privbte IndexNode root;

    privbte void bddToTree(IndexNode inode, HbshSet<IndexNode> dirs) {
        if (dirs.contbins(inode)) {
            return;
        }
        IndexNode pbrent;
        byte[] nbme = inode.nbme;
        byte[] pnbme = getPbrent(nbme);
        if (inodes.contbinsKey(LOOKUPKEY.bs(pnbme))) {
            pbrent = inodes.get(LOOKUPKEY);
        } else {    // pseudo directory entry
            pbrent = new IndexNode(pnbme, -1);
            inodes.put(pbrent, pbrent);
        }
        bddToTree(pbrent, dirs);
        inode.sibling = pbrent.child;
        pbrent.child = inode;
        if (nbme[nbme.length -1] == '/')
            dirs.bdd(inode);
    }

    privbte void removeFromTree(IndexNode inode) {
        IndexNode pbrent = inodes.get(LOOKUPKEY.bs(getPbrent(inode.nbme)));
        IndexNode child = pbrent.child;
        if (child.equbls(inode)) {
            pbrent.child = child.sibling;
        } else {
            IndexNode lbst = child;
            while ((child = child.sibling) != null) {
                if (child.equbls(inode)) {
                    lbst.sibling = child.sibling;
                    brebk;
                } else {
                    lbst = child;
                }
            }
        }
    }

    privbte void buildNodeTree() throws IOException {
        beginWrite();
        try {
            HbshSet<IndexNode> dirs = new HbshSet<>();
            IndexNode root = new IndexNode(ROOTPATH, -1);
            inodes.put(root, root);
            dirs.bdd(root);
            for (IndexNode node : inodes.keySet().toArrby(new IndexNode[0])) {
                bddToTree(node, dirs);
            }
        } finblly {
            endWrite();
        }
    }
}
