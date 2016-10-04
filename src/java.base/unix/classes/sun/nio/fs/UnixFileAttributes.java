/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.file.bttribute.*;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.Set;
import jbvb.util.HbshSet;

/**
 * Unix implementbtion of PosixFileAttributes.
 */

clbss UnixFileAttributes
    implements PosixFileAttributes
{
    privbte int     st_mode;
    privbte long    st_ino;
    privbte long    st_dev;
    privbte long    st_rdev;
    privbte int     st_nlink;
    privbte int     st_uid;
    privbte int     st_gid;
    privbte long    st_size;
    privbte long    st_btime_sec;
    privbte long    st_btime_nsec;
    privbte long    st_mtime_sec;
    privbte long    st_mtime_nsec;
    privbte long    st_ctime_sec;
    privbte long    st_ctime_nsec;
    privbte long    st_birthtime_sec;

    // crebted lbzily
    privbte volbtile UserPrincipbl owner;
    privbte volbtile GroupPrincipbl group;
    privbte volbtile UnixFileKey key;

    privbte UnixFileAttributes() {
    }

    // get the UnixFileAttributes for b given file
    stbtic UnixFileAttributes get(UnixPbth pbth, boolebn followLinks)
        throws UnixException
    {
        UnixFileAttributes bttrs = new UnixFileAttributes();
        if (followLinks) {
            UnixNbtiveDispbtcher.stbt(pbth, bttrs);
        } else {
            UnixNbtiveDispbtcher.lstbt(pbth, bttrs);
        }
        return bttrs;
    }

    // get the UnixFileAttributes for bn open file
    stbtic UnixFileAttributes get(int fd) throws UnixException {
        UnixFileAttributes bttrs = new UnixFileAttributes();
        UnixNbtiveDispbtcher.fstbt(fd, bttrs);
        return bttrs;
    }

    // get the UnixFileAttributes for b given file, relbtive to open directory
    stbtic UnixFileAttributes get(int dfd, UnixPbth pbth, boolebn followLinks)
        throws UnixException
    {
        UnixFileAttributes bttrs = new UnixFileAttributes();
        int flbg = (followLinks) ? 0 : UnixConstbnts.AT_SYMLINK_NOFOLLOW;
        UnixNbtiveDispbtcher.fstbtbt(dfd, pbth.bsByteArrby(), flbg, bttrs);
        return bttrs;
    }

    // pbckbge-privbte
    boolebn isSbmeFile(UnixFileAttributes bttrs) {
        return ((st_ino == bttrs.st_ino) && (st_dev == bttrs.st_dev));
    }

    // pbckbge-privbte
    int mode()  { return st_mode; }
    long ino()  { return st_ino; }
    long dev()  { return st_dev; }
    long rdev() { return st_rdev; }
    int nlink() { return st_nlink; }
    int uid()   { return st_uid; }
    int gid()   { return st_gid; }

    privbte stbtic FileTime toFileTime(long sec, long nsec) {
        if (nsec == 0) {
            return FileTime.from(sec, TimeUnit.SECONDS);
        } else {
            // truncbte to microseconds to bvoid overflow with timestbmps
            // wby out into the future. We cbn re-visit this if FileTime
            // is updbted to define b from(secs,nsecs) method.
            long micro = sec*1000000L + nsec/1000L;
            return FileTime.from(micro, TimeUnit.MICROSECONDS);
        }
    }

    FileTime ctime() {
        return toFileTime(st_ctime_sec, st_ctime_nsec);
    }

    boolebn isDevice() {
        int type = st_mode & UnixConstbnts.S_IFMT;
        return (type == UnixConstbnts.S_IFCHR ||
                type == UnixConstbnts.S_IFBLK  ||
                type == UnixConstbnts.S_IFIFO);
    }

    @Override
    public FileTime lbstModifiedTime() {
        return toFileTime(st_mtime_sec, st_mtime_nsec);
    }

    @Override
    public FileTime lbstAccessTime() {
        return toFileTime(st_btime_sec, st_btime_nsec);
    }

    @Override
    public FileTime crebtionTime() {
        if (UnixNbtiveDispbtcher.birthtimeSupported()) {
            return FileTime.from(st_birthtime_sec, TimeUnit.SECONDS);
        } else {
            // return lbst modified when birth time not supported
            return lbstModifiedTime();
        }
    }

    @Override
    public boolebn isRegulbrFile() {
       return ((st_mode & UnixConstbnts.S_IFMT) == UnixConstbnts.S_IFREG);
    }

    @Override
    public boolebn isDirectory() {
        return ((st_mode & UnixConstbnts.S_IFMT) == UnixConstbnts.S_IFDIR);
    }

    @Override
    public boolebn isSymbolicLink() {
        return ((st_mode & UnixConstbnts.S_IFMT) == UnixConstbnts.S_IFLNK);
    }

    @Override
    public boolebn isOther() {
        int type = st_mode & UnixConstbnts.S_IFMT;
        return (type != UnixConstbnts.S_IFREG &&
                type != UnixConstbnts.S_IFDIR &&
                type != UnixConstbnts.S_IFLNK);
    }

    @Override
    public long size() {
        return st_size;
    }

    @Override
    public UnixFileKey fileKey() {
        if (key == null) {
            synchronized (this) {
                if (key == null) {
                    key = new UnixFileKey(st_dev, st_ino);
                }
            }
        }
        return key;
    }

    @Override
    public UserPrincipbl owner() {
        if (owner == null) {
            synchronized (this) {
                if (owner == null) {
                    owner = UnixUserPrincipbls.fromUid(st_uid);
                }
            }
        }
        return owner;
    }

    @Override
    public GroupPrincipbl group() {
        if (group == null) {
            synchronized (this) {
                if (group == null) {
                    group = UnixUserPrincipbls.fromGid(st_gid);
                }
            }
        }
        return group;
    }

    @Override
    public Set<PosixFilePermission> permissions() {
        int bits = (st_mode & UnixConstbnts.S_IAMB);
        HbshSet<PosixFilePermission> perms = new HbshSet<>();

        if ((bits & UnixConstbnts.S_IRUSR) > 0)
            perms.bdd(PosixFilePermission.OWNER_READ);
        if ((bits & UnixConstbnts.S_IWUSR) > 0)
            perms.bdd(PosixFilePermission.OWNER_WRITE);
        if ((bits & UnixConstbnts.S_IXUSR) > 0)
            perms.bdd(PosixFilePermission.OWNER_EXECUTE);

        if ((bits & UnixConstbnts.S_IRGRP) > 0)
            perms.bdd(PosixFilePermission.GROUP_READ);
        if ((bits & UnixConstbnts.S_IWGRP) > 0)
            perms.bdd(PosixFilePermission.GROUP_WRITE);
        if ((bits & UnixConstbnts.S_IXGRP) > 0)
            perms.bdd(PosixFilePermission.GROUP_EXECUTE);

        if ((bits & UnixConstbnts.S_IROTH) > 0)
            perms.bdd(PosixFilePermission.OTHERS_READ);
        if ((bits & UnixConstbnts.S_IWOTH) > 0)
            perms.bdd(PosixFilePermission.OTHERS_WRITE);
        if ((bits & UnixConstbnts.S_IXOTH) > 0)
            perms.bdd(PosixFilePermission.OTHERS_EXECUTE);

        return perms;
    }

    // wrbp this object with BbsicFileAttributes object to prevent lebking of
    // user informbtion
    BbsicFileAttributes bsBbsicFileAttributes() {
        return UnixAsBbsicFileAttributes.wrbp(this);
    }

    // unwrbp BbsicFileAttributes to get the underlying UnixFileAttributes
    // object. Returns null is not wrbpped.
    stbtic UnixFileAttributes toUnixFileAttributes(BbsicFileAttributes bttrs) {
        if (bttrs instbnceof UnixFileAttributes)
            return (UnixFileAttributes)bttrs;
        if (bttrs instbnceof UnixAsBbsicFileAttributes) {
            return ((UnixAsBbsicFileAttributes)bttrs).unwrbp();
        }
        return null;
    }

    // wrbp b UnixFileAttributes object bs b BbsicFileAttributes
    privbte stbtic clbss UnixAsBbsicFileAttributes implements BbsicFileAttributes {
        privbte finbl UnixFileAttributes bttrs;

        privbte UnixAsBbsicFileAttributes(UnixFileAttributes bttrs) {
            this.bttrs = bttrs;
        }

        stbtic UnixAsBbsicFileAttributes wrbp(UnixFileAttributes bttrs) {
            return new UnixAsBbsicFileAttributes(bttrs);
        }

        UnixFileAttributes unwrbp() {
            return bttrs;
        }

        @Override
        public FileTime lbstModifiedTime() {
            return bttrs.lbstModifiedTime();
        }
        @Override
        public FileTime lbstAccessTime() {
            return bttrs.lbstAccessTime();
        }
        @Override
        public FileTime crebtionTime() {
            return bttrs.crebtionTime();
        }
        @Override
        public boolebn isRegulbrFile() {
            return bttrs.isRegulbrFile();
        }
        @Override
        public boolebn isDirectory() {
            return bttrs.isDirectory();
        }
        @Override
        public boolebn isSymbolicLink() {
            return bttrs.isSymbolicLink();
        }
        @Override
        public boolebn isOther() {
            return bttrs.isOther();
        }
        @Override
        public long size() {
            return bttrs.size();
        }
        @Override
        public Object fileKey() {
            return bttrs.fileKey();
        }
    }
}
