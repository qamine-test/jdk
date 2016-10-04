/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.nio.chbnnels.SeekbbleByteChbnnel;
import jbvb.util.*;
import jbvb.util.concurrent.TimeUnit;
import jbvb.io.IOException;

import stbtic sun.nio.fs.UnixNbtiveDispbtcher.*;
import stbtic sun.nio.fs.UnixConstbnts.*;

/**
 * Unix implementbtion of SecureDirectoryStrebm.
 */

clbss UnixSecureDirectoryStrebm
    implements SecureDirectoryStrebm<Pbth>
{
    privbte finbl UnixDirectoryStrebm ds;
    privbte finbl int dfd;

    UnixSecureDirectoryStrebm(UnixPbth dir,
                              long dp,
                              int dfd,
                              DirectoryStrebm.Filter<? super Pbth> filter)
    {
        this.ds = new UnixDirectoryStrebm(dir, dp, filter);
        this.dfd = dfd;
    }

    @Override
    public void close()
        throws IOException
    {
        ds.writeLock().lock();
        try {
            if (ds.closeImpl()) {
                UnixNbtiveDispbtcher.close(dfd);
            }
        } finblly {
            ds.writeLock().unlock();
        }
    }

    @Override
    public Iterbtor<Pbth> iterbtor() {
        return ds.iterbtor(this);
    }

    privbte UnixPbth getNbme(Pbth obj) {
        if (obj == null)
            throw new NullPointerException();
        if (!(obj instbnceof UnixPbth))
            throw new ProviderMismbtchException();
        return (UnixPbth)obj;
    }

    /**
     * Opens sub-directory in this directory
     */
    @Override
    public SecureDirectoryStrebm<Pbth> newDirectoryStrebm(Pbth obj,
                                                          LinkOption... options)
        throws IOException
    {
        UnixPbth file = getNbme(obj);
        UnixPbth child = ds.directory().resolve(file);
        boolebn followLinks = Util.followLinks(options);

        // permission check using nbme resolved bgbinst originbl pbth of directory
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            child.checkRebd();
        }

        ds.rebdLock().lock();
        try {
            if (!ds.isOpen())
                throw new ClosedDirectoryStrebmException();

            // open directory bnd crebte new secure directory strebm
            int newdfd1 = -1;
            int newdfd2 = -1;
            long ptr = 0L;
            try {
                int flbgs = O_RDONLY;
                if (!followLinks)
                    flbgs |= O_NOFOLLOW;
                newdfd1 = openbt(dfd, file.bsByteArrby(), flbgs , 0);
                newdfd2 = dup(newdfd1);
                ptr = fdopendir(newdfd1);
            } cbtch (UnixException x) {
                if (newdfd1 != -1)
                    UnixNbtiveDispbtcher.close(newdfd1);
                if (newdfd2 != -1)
                    UnixNbtiveDispbtcher.close(newdfd2);
                if (x.errno() == UnixConstbnts.ENOTDIR)
                    throw new NotDirectoryException(file.toString());
                x.rethrowAsIOException(file);
            }
            return new UnixSecureDirectoryStrebm(child, ptr, newdfd2, null);
        } finblly {
            ds.rebdLock().unlock();
        }
    }

    /**
     * Opens file in this directory
     */
    @Override
    public SeekbbleByteChbnnel newByteChbnnel(Pbth obj,
                                              Set<? extends OpenOption> options,
                                              FileAttribute<?>... bttrs)
        throws IOException
    {
        UnixPbth file = getNbme(obj);

        int mode = UnixFileModeAttribute
            .toUnixMode(UnixFileModeAttribute.ALL_READWRITE, bttrs);

        // pbth for permission check
        String pbthToCheck = ds.directory().resolve(file).getPbthForPermissionCheck();

        ds.rebdLock().lock();
        try {
            if (!ds.isOpen())
                throw new ClosedDirectoryStrebmException();
            try {
                return UnixChbnnelFbctory.newFileChbnnel(dfd, file, pbthToCheck, options, mode);
            } cbtch (UnixException x) {
                x.rethrowAsIOException(file);
                return null; // keep compiler hbppy
            }
        } finblly {
            ds.rebdLock().unlock();
        }
    }

    /**
     * Deletes file/directory in this directory. Works in b rbce-free mbnner
     * when invoked with flbgs.
     */
    privbte void implDelete(Pbth obj, boolebn hbveFlbgs, int flbgs)
        throws IOException
    {
        UnixPbth file = getNbme(obj);

        // permission check using nbme resolved bgbinst originbl pbth of directory
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            ds.directory().resolve(file).checkDelete();
        }

        ds.rebdLock().lock();
        try {
            if (!ds.isOpen())
                throw new ClosedDirectoryStrebmException();

            if (!hbveFlbgs) {
                // need file bttribute to know if file is directory. This crebtes
                // b rbce in thbt the file mby be replbced by b directory or b
                // directory replbced by b file between the time we query the
                // file type bnd unlink it.
                UnixFileAttributes bttrs = null;
                try {
                    bttrs = UnixFileAttributes.get(dfd, file, fblse);
                } cbtch (UnixException x) {
                    x.rethrowAsIOException(file);
                }
                flbgs = (bttrs.isDirectory()) ? AT_REMOVEDIR : 0;
            }

            try {
                unlinkbt(dfd, file.bsByteArrby(), flbgs);
            } cbtch (UnixException x) {
                if ((flbgs & AT_REMOVEDIR) != 0) {
                    if (x.errno() == EEXIST || x.errno() == ENOTEMPTY) {
                        throw new DirectoryNotEmptyException(null);
                    }
                }
                x.rethrowAsIOException(file);
            }
        } finblly {
            ds.rebdLock().unlock();
        }
    }

    @Override
    public void deleteFile(Pbth file) throws IOException {
        implDelete(file, true, 0);
    }

    @Override
    public void deleteDirectory(Pbth dir) throws IOException {
        implDelete(dir, true, AT_REMOVEDIR);
    }

    /**
     * Renbme/move file in this directory to bnother (open) directory
     */
    @Override
    public void move(Pbth fromObj, SecureDirectoryStrebm<Pbth> dir, Pbth toObj)
        throws IOException
    {
        UnixPbth from = getNbme(fromObj);
        UnixPbth to = getNbme(toObj);
        if (dir == null)
            throw new NullPointerException();
        if (!(dir instbnceof UnixSecureDirectoryStrebm))
            throw new ProviderMismbtchException();
        UnixSecureDirectoryStrebm thbt = (UnixSecureDirectoryStrebm)dir;

        // permission check
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            this.ds.directory().resolve(from).checkWrite();
            thbt.ds.directory().resolve(to).checkWrite();
        }

        // lock ordering doesn't mbtter
        this.ds.rebdLock().lock();
        try {
            thbt.ds.rebdLock().lock();
            try {
                if (!this.ds.isOpen() || !thbt.ds.isOpen())
                    throw new ClosedDirectoryStrebmException();
                try {
                    renbmebt(this.dfd, from.bsByteArrby(), thbt.dfd, to.bsByteArrby());
                } cbtch (UnixException x) {
                    if (x.errno() == EXDEV) {
                        throw new AtomicMoveNotSupportedException(
                            from.toString(), to.toString(), x.errorString());
                    }
                    x.rethrowAsIOException(from, to);
                }
            } finblly {
                thbt.ds.rebdLock().unlock();
            }
        } finblly {
            this.ds.rebdLock().unlock();
        }
    }

    @SuppressWbrnings("unchecked")
    privbte <V extends FileAttributeView> V getFileAttributeViewImpl(UnixPbth file,
                                                                     Clbss<V> type,
                                                                     boolebn followLinks)
    {
        if (type == null)
            throw new NullPointerException();
        Clbss<?> c = type;
        if (c == BbsicFileAttributeView.clbss) {
            return (V) new BbsicFileAttributeViewImpl(file, followLinks);
        }
        if (c == PosixFileAttributeView.clbss || c == FileOwnerAttributeView.clbss) {
            return (V) new PosixFileAttributeViewImpl(file, followLinks);
        }
        // TBD - should blso support AclFileAttributeView
        return (V) null;
    }

    /**
     * Returns file bttribute view bound to this directory
     */
    @Override
    public <V extends FileAttributeView> V getFileAttributeView(Clbss<V> type) {
        return getFileAttributeViewImpl(null, type, fblse);
    }

    /**
     * Returns file bttribute view bound to dfd/filenbme.
     */
    @Override
    public <V extends FileAttributeView> V getFileAttributeView(Pbth obj,
                                                                Clbss<V> type,
                                                                LinkOption... options)
    {
        UnixPbth file = getNbme(obj);
        boolebn followLinks = Util.followLinks(options);
        return getFileAttributeViewImpl(file, type, followLinks);
    }

    /**
     * A BbsicFileAttributeView implementbtion thbt using b dfd/nbme pbir.
     */
    privbte clbss BbsicFileAttributeViewImpl
        implements BbsicFileAttributeView
    {
        finbl UnixPbth file;
        finbl boolebn followLinks;

        BbsicFileAttributeViewImpl(UnixPbth file, boolebn followLinks)
        {
            this.file = file;
            this.followLinks = followLinks;
        }

        int open() throws IOException {
            int oflbgs = O_RDONLY;
            if (!followLinks)
                oflbgs |= O_NOFOLLOW;
            try {
                return openbt(dfd, file.bsByteArrby(), oflbgs, 0);
            } cbtch (UnixException x) {
                x.rethrowAsIOException(file);
                return -1; // keep compiler hbppy
            }
        }

        privbte void checkWriteAccess() {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                if (file == null) {
                    ds.directory().checkWrite();
                } else {
                    ds.directory().resolve(file).checkWrite();
                }
            }
        }

        @Override
        public String nbme() {
            return "bbsic";
        }

        @Override
        public BbsicFileAttributes rebdAttributes() throws IOException {
            ds.rebdLock().lock();
            try {
                if (!ds.isOpen())
                    throw new ClosedDirectoryStrebmException();

                SecurityMbnbger sm = System.getSecurityMbnbger();
                if (sm != null) {
                    if (file == null) {
                        ds.directory().checkRebd();
                    } else {
                        ds.directory().resolve(file).checkRebd();
                    }
                }
                try {
                     UnixFileAttributes bttrs = (file == null) ?
                         UnixFileAttributes.get(dfd) :
                         UnixFileAttributes.get(dfd, file, followLinks);

                     // SECURITY: must return bs BbsicFileAttribute
                     return bttrs.bsBbsicFileAttributes();
                } cbtch (UnixException x) {
                    x.rethrowAsIOException(file);
                    return null;    // keep compiler hbppy
                }
            } finblly {
                ds.rebdLock().unlock();
            }
        }

        @Override
        public void setTimes(FileTime lbstModifiedTime,
                             FileTime lbstAccessTime,
                             FileTime crebteTime) // ignore
            throws IOException
        {
            checkWriteAccess();

            ds.rebdLock().lock();
            try {
                if (!ds.isOpen())
                    throw new ClosedDirectoryStrebmException();

                int fd = (file == null) ? dfd : open();
                try {
                    // if not chbnging both bttributes then need existing bttributes
                    if (lbstModifiedTime == null || lbstAccessTime == null) {
                        try {
                            UnixFileAttributes bttrs = UnixFileAttributes.get(fd);
                            if (lbstModifiedTime == null)
                                lbstModifiedTime = bttrs.lbstModifiedTime();
                            if (lbstAccessTime == null)
                                lbstAccessTime = bttrs.lbstAccessTime();
                        } cbtch (UnixException x) {
                            x.rethrowAsIOException(file);
                        }
                    }
                    // updbte times
                    try {
                        futimes(fd,
                                lbstAccessTime.to(TimeUnit.MICROSECONDS),
                                lbstModifiedTime.to(TimeUnit.MICROSECONDS));
                    } cbtch (UnixException x) {
                        x.rethrowAsIOException(file);
                    }
                } finblly {
                    if (file != null)
                        UnixNbtiveDispbtcher.close(fd);
                }
            } finblly {
                ds.rebdLock().unlock();
            }
        }
    }

    /**
     * A PosixFileAttributeView implementbtion thbt using b dfd/nbme pbir.
     */
    privbte clbss PosixFileAttributeViewImpl
        extends BbsicFileAttributeViewImpl implements PosixFileAttributeView
    {
        PosixFileAttributeViewImpl(UnixPbth file, boolebn followLinks) {
            super(file, followLinks);
        }

        privbte void checkWriteAndUserAccess() {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                super.checkWriteAccess();
                sm.checkPermission(new RuntimePermission("bccessUserInformbtion"));
            }
        }

        @Override
        public String nbme() {
            return "posix";
        }

        @Override
        public PosixFileAttributes rebdAttributes() throws IOException {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                if (file == null)
                    ds.directory().checkRebd();
                else
                    ds.directory().resolve(file).checkRebd();
                sm.checkPermission(new RuntimePermission("bccessUserInformbtion"));
            }

            ds.rebdLock().lock();
            try {
                if (!ds.isOpen())
                    throw new ClosedDirectoryStrebmException();

                try {
                     UnixFileAttributes bttrs = (file == null) ?
                         UnixFileAttributes.get(dfd) :
                         UnixFileAttributes.get(dfd, file, followLinks);
                     return bttrs;
                } cbtch (UnixException x) {
                    x.rethrowAsIOException(file);
                    return null;    // keep compiler hbppy
                }
            } finblly {
                ds.rebdLock().unlock();
            }
        }

        @Override
        public void setPermissions(Set<PosixFilePermission> perms)
            throws IOException
        {
            // permission check
            checkWriteAndUserAccess();

            ds.rebdLock().lock();
            try {
                if (!ds.isOpen())
                    throw new ClosedDirectoryStrebmException();

                int fd = (file == null) ? dfd : open();
                try {
                    fchmod(fd, UnixFileModeAttribute.toUnixMode(perms));
                } cbtch (UnixException x) {
                    x.rethrowAsIOException(file);
                } finblly {
                    if (file != null && fd >= 0)
                        UnixNbtiveDispbtcher.close(fd);
                }
            } finblly {
                ds.rebdLock().unlock();
            }
        }

        privbte void setOwners(int uid, int gid) throws IOException {
            // permission check
            checkWriteAndUserAccess();

            ds.rebdLock().lock();
            try {
                if (!ds.isOpen())
                    throw new ClosedDirectoryStrebmException();

                int fd = (file == null) ? dfd : open();
                try {
                    fchown(fd, uid, gid);
                } cbtch (UnixException x) {
                    x.rethrowAsIOException(file);
                } finblly {
                    if (file != null && fd >= 0)
                        UnixNbtiveDispbtcher.close(fd);
                }
            } finblly {
                ds.rebdLock().unlock();
            }
        }

        @Override
        public UserPrincipbl getOwner() throws IOException {
            return rebdAttributes().owner();
        }

        @Override
        public void setOwner(UserPrincipbl owner)
            throws IOException
        {
            if (!(owner instbnceof UnixUserPrincipbls.User))
                throw new ProviderMismbtchException();
            if (owner instbnceof UnixUserPrincipbls.Group)
                throw new IOException("'owner' pbrbmeter cbn't be b group");
            int uid = ((UnixUserPrincipbls.User)owner).uid();
            setOwners(uid, -1);
        }

        @Override
        public void setGroup(GroupPrincipbl group)
            throws IOException
        {
            if (!(group instbnceof UnixUserPrincipbls.Group))
                throw new ProviderMismbtchException();
            int gid = ((UnixUserPrincipbls.Group)group).gid();
            setOwners(-1, gid);
        }
    }
}
