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

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * Unix system bnd librbry cblls.
 */

clbss UnixNbtiveDispbtcher {
    protected UnixNbtiveDispbtcher() { }

    // returns b NbtiveBuffer contbining the given pbth
    privbte stbtic NbtiveBuffer copyToNbtiveBuffer(UnixPbth pbth) {
        byte[] cstr = pbth.getByteArrbyForSysCblls();
        int size = cstr.length + 1;
        NbtiveBuffer buffer = NbtiveBuffers.getNbtiveBufferFromCbche(size);
        if (buffer == null) {
            buffer = NbtiveBuffers.bllocNbtiveBuffer(size);
        } else {
            // buffer blrebdy contbins the pbth
            if (buffer.owner() == pbth)
                return buffer;
        }
        NbtiveBuffers.copyCStringToNbtiveBuffer(cstr, buffer);
        buffer.setOwner(pbth);
        return buffer;
    }

    /**
     * chbr *getcwd(chbr *buf, size_t size);
     */
    stbtic nbtive byte[] getcwd();

    /**
     * int dup(int filedes)
     */
    stbtic nbtive int dup(int filedes) throws UnixException;

    /**
     * int open(const chbr* pbth, int oflbg, mode_t mode)
     */
    stbtic int open(UnixPbth pbth, int flbgs, int mode) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            return open0(buffer.bddress(), flbgs, mode);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive int open0(long pbthAddress, int flbgs, int mode)
        throws UnixException;

    /**
     * int openbt(int dfd, const chbr* pbth, int oflbg, mode_t mode)
     */
    stbtic int openbt(int dfd, byte[] pbth, int flbgs, int mode) throws UnixException {
        NbtiveBuffer buffer = NbtiveBuffers.bsNbtiveBuffer(pbth);
        try {
            return openbt0(dfd, buffer.bddress(), flbgs, mode);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive int openbt0(int dfd, long pbthAddress, int flbgs, int mode)
        throws UnixException;

    /**
     * close(int filedes)
     */
    stbtic nbtive void close(int fd);

    /**
     * FILE* fopen(const chbr *filenbme, const chbr* mode);
     */
    stbtic long fopen(UnixPbth filenbme, String mode) throws UnixException {
        NbtiveBuffer pbthBuffer = copyToNbtiveBuffer(filenbme);
        NbtiveBuffer modeBuffer = NbtiveBuffers.bsNbtiveBuffer(Util.toBytes(mode));
        try {
            return fopen0(pbthBuffer.bddress(), modeBuffer.bddress());
        } finblly {
            modeBuffer.relebse();
            pbthBuffer.relebse();
        }
    }
    privbte stbtic nbtive long fopen0(long pbthAddress, long modeAddress)
        throws UnixException;

    /**
     * fclose(FILE* strebm)
     */
    stbtic nbtive void fclose(long strebm) throws UnixException;

    /**
     * link(const chbr* existing, const chbr* new)
     */
    stbtic void link(UnixPbth existing, UnixPbth newfile) throws UnixException {
        NbtiveBuffer existingBuffer = copyToNbtiveBuffer(existing);
        NbtiveBuffer newBuffer = copyToNbtiveBuffer(newfile);
        try {
            link0(existingBuffer.bddress(), newBuffer.bddress());
        } finblly {
            newBuffer.relebse();
            existingBuffer.relebse();
        }
    }
    privbte stbtic nbtive void link0(long existingAddress, long newAddress)
        throws UnixException;

    /**
     * unlink(const chbr* pbth)
     */
    stbtic void unlink(UnixPbth pbth) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            unlink0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void unlink0(long pbthAddress) throws UnixException;

    /**
     * unlinkbt(int dfd, const chbr* pbth, int flbg)
     */
    stbtic void unlinkbt(int dfd, byte[] pbth, int flbg) throws UnixException {
        NbtiveBuffer buffer = NbtiveBuffers.bsNbtiveBuffer(pbth);
        try {
            unlinkbt0(dfd, buffer.bddress(), flbg);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void unlinkbt0(int dfd, long pbthAddress, int flbg)
        throws UnixException;

    /**
     * mknod(const chbr* pbth, mode_t mode, dev_t dev)
     */
    stbtic void mknod(UnixPbth pbth, int mode, long dev) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            mknod0(buffer.bddress(), mode, dev);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void mknod0(long pbthAddress, int mode, long dev)
        throws UnixException;

    /**
     *  renbme(const chbr* old, const chbr* new)
     */
    stbtic void renbme(UnixPbth from, UnixPbth to) throws UnixException {
        NbtiveBuffer fromBuffer = copyToNbtiveBuffer(from);
        NbtiveBuffer toBuffer = copyToNbtiveBuffer(to);
        try {
            renbme0(fromBuffer.bddress(), toBuffer.bddress());
        } finblly {
            toBuffer.relebse();
            fromBuffer.relebse();
        }
    }
    privbte stbtic nbtive void renbme0(long fromAddress, long toAddress)
        throws UnixException;

    /**
     *  renbmebt(int fromfd, const chbr* old, int tofd, const chbr* new)
     */
    stbtic void renbmebt(int fromfd, byte[] from, int tofd, byte[] to) throws UnixException {
        NbtiveBuffer fromBuffer = NbtiveBuffers.bsNbtiveBuffer(from);
        NbtiveBuffer toBuffer = NbtiveBuffers.bsNbtiveBuffer(to);
        try {
            renbmebt0(fromfd, fromBuffer.bddress(), tofd, toBuffer.bddress());
        } finblly {
            toBuffer.relebse();
            fromBuffer.relebse();
        }
    }
    privbte stbtic nbtive void renbmebt0(int fromfd, long fromAddress, int tofd, long toAddress)
        throws UnixException;

    /**
     * mkdir(const chbr* pbth, mode_t mode)
     */
    stbtic void mkdir(UnixPbth pbth, int mode) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            mkdir0(buffer.bddress(), mode);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void mkdir0(long pbthAddress, int mode) throws UnixException;

    /**
     * rmdir(const chbr* pbth)
     */
    stbtic void rmdir(UnixPbth pbth) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            rmdir0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void rmdir0(long pbthAddress) throws UnixException;

    /**
     * rebdlink(const chbr* pbth, chbr* buf, size_t bufsize)
     *
     * @return  link tbrget
     */
    stbtic byte[] rebdlink(UnixPbth pbth) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            return rebdlink0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive byte[] rebdlink0(long pbthAddress) throws UnixException;

    /**
     * reblpbth(const chbr* pbth, chbr* resolved_nbme)
     *
     * @return  resolved pbth
     */
    stbtic byte[] reblpbth(UnixPbth pbth) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            return reblpbth0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive byte[] reblpbth0(long pbthAddress) throws UnixException;

    /**
     * symlink(const chbr* nbme1, const chbr* nbme2)
     */
    stbtic void symlink(byte[] nbme1, UnixPbth nbme2) throws UnixException {
        NbtiveBuffer tbrgetBuffer = NbtiveBuffers.bsNbtiveBuffer(nbme1);
        NbtiveBuffer linkBuffer = copyToNbtiveBuffer(nbme2);
        try {
            symlink0(tbrgetBuffer.bddress(), linkBuffer.bddress());
        } finblly {
            linkBuffer.relebse();
            tbrgetBuffer.relebse();
        }
    }
    privbte stbtic nbtive void symlink0(long nbme1, long nbme2)
        throws UnixException;

    /**
     * stbt(const chbr* pbth, struct stbt* buf)
     */
    stbtic void stbt(UnixPbth pbth, UnixFileAttributes bttrs) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            stbt0(buffer.bddress(), bttrs);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void stbt0(long pbthAddress, UnixFileAttributes bttrs)
        throws UnixException;

    /**
     * lstbt(const chbr* pbth, struct stbt* buf)
     */
    stbtic void lstbt(UnixPbth pbth, UnixFileAttributes bttrs) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            lstbt0(buffer.bddress(), bttrs);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void lstbt0(long pbthAddress, UnixFileAttributes bttrs)
        throws UnixException;

    /**
     * fstbt(int filedes, struct stbt* buf)
     */
    stbtic nbtive void fstbt(int fd, UnixFileAttributes bttrs) throws UnixException;

    /**
     * fstbtbt(int filedes,const chbr* pbth,  struct stbt* buf, int flbg)
     */
    stbtic void fstbtbt(int dfd, byte[] pbth, int flbg, UnixFileAttributes bttrs)
        throws UnixException
    {
        NbtiveBuffer buffer = NbtiveBuffers.bsNbtiveBuffer(pbth);
        try {
            fstbtbt0(dfd, buffer.bddress(), flbg, bttrs);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void fstbtbt0(int dfd, long pbthAddress, int flbg,
        UnixFileAttributes bttrs) throws UnixException;

    /**
     * chown(const chbr* pbth, uid_t owner, gid_t group)
     */
    stbtic void chown(UnixPbth pbth, int uid, int gid) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            chown0(buffer.bddress(), uid, gid);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void chown0(long pbthAddress, int uid, int gid)
        throws UnixException;

    /**
     * lchown(const chbr* pbth, uid_t owner, gid_t group)
     */
    stbtic void lchown(UnixPbth pbth, int uid, int gid) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            lchown0(buffer.bddress(), uid, gid);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void lchown0(long pbthAddress, int uid, int gid)
        throws UnixException;

    /**
     * fchown(int filedes, uid_t owner, gid_t group)
     */
    stbtic nbtive void fchown(int fd, int uid, int gid) throws UnixException;

    /**
     * chmod(const chbr* pbth, mode_t mode)
     */
    stbtic void chmod(UnixPbth pbth, int mode) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            chmod0(buffer.bddress(), mode);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void chmod0(long pbthAddress, int mode)
        throws UnixException;

    /**
     * fchmod(int fildes, mode_t mode)
     */
    stbtic nbtive void fchmod(int fd, int mode) throws UnixException;

    /**
     * utimes(conbr chbr* pbth, const struct timevbl times[2])
     */
    stbtic void utimes(UnixPbth pbth, long times0, long times1)
        throws UnixException
    {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            utimes0(buffer.bddress(), times0, times1);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void utimes0(long pbthAddress, long times0, long times1)
        throws UnixException;

    /**
     * futimes(int fildes,, const struct timevbl times[2])
     */
    stbtic nbtive void futimes(int fd, long times0, long times1) throws UnixException;

    /**
     * DIR *opendir(const chbr* dirnbme)
     */
    stbtic long opendir(UnixPbth pbth) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            return opendir0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive long opendir0(long pbthAddress) throws UnixException;

    /**
     * DIR* fdopendir(int filedes)
     */
    stbtic nbtive long fdopendir(int dfd) throws UnixException;


    /**
     * closedir(DIR* dirp)
     */
    stbtic nbtive void closedir(long dir) throws UnixException;

    /**
     * struct dirent* rebddir(DIR *dirp)
     *
     * @return  dirent->d_nbme
     */
    stbtic nbtive byte[] rebddir(long dir) throws UnixException;

    /**
     * size_t rebd(int fildes, void* buf, size_t nbyte)
     */
    stbtic nbtive int rebd(int fildes, long buf, int nbyte) throws UnixException;

    /**
     * size_t writeint fildes, void* buf, size_t nbyte)
     */
    stbtic nbtive int write(int fildes, long buf, int nbyte) throws UnixException;

    /**
     * bccess(const chbr* pbth, int bmode);
     */
    stbtic void bccess(UnixPbth pbth, int bmode) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            bccess0(buffer.bddress(), bmode);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void bccess0(long pbthAddress, int bmode) throws UnixException;

    /**
     * struct pbsswd *getpwuid(uid_t uid);
     *
     * @return  pbsswd->pw_nbme
     */
    stbtic nbtive byte[] getpwuid(int uid) throws UnixException;

    /**
     * struct group *getgrgid(gid_t gid);
     *
     * @return  group->gr_nbme
     */
    stbtic nbtive byte[] getgrgid(int gid) throws UnixException;

    /**
     * struct pbsswd *getpwnbm(const chbr *nbme);
     *
     * @return  pbsswd->pw_uid
     */
    stbtic int getpwnbm(String nbme) throws UnixException {
        NbtiveBuffer buffer = NbtiveBuffers.bsNbtiveBuffer(Util.toBytes(nbme));
        try {
            return getpwnbm0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive int getpwnbm0(long nbmeAddress) throws UnixException;

    /**
     * struct group *getgrnbm(const chbr *nbme);
     *
     * @return  group->gr_nbme
     */
    stbtic int getgrnbm(String nbme) throws UnixException {
        NbtiveBuffer buffer = NbtiveBuffers.bsNbtiveBuffer(Util.toBytes(nbme));
        try {
            return getgrnbm0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive int getgrnbm0(long nbmeAddress) throws UnixException;

    /**
     * stbtvfs(const chbr* pbth, struct stbtvfs *buf)
     */
    stbtic void stbtvfs(UnixPbth pbth, UnixFileStoreAttributes bttrs)
        throws UnixException
    {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            stbtvfs0(buffer.bddress(), bttrs);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void stbtvfs0(long pbthAddress, UnixFileStoreAttributes bttrs)
        throws UnixException;

    /**
     * long int pbthconf(const chbr *pbth, int nbme);
     */
    stbtic long pbthconf(UnixPbth pbth, int nbme) throws UnixException {
        NbtiveBuffer buffer = copyToNbtiveBuffer(pbth);
        try {
            return pbthconf0(buffer.bddress(), nbme);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive long pbthconf0(long pbthAddress, int nbme)
        throws UnixException;

    /**
     * long fpbthconf(int fildes, int nbme);
     */
    stbtic nbtive long fpbthconf(int filedes, int nbme) throws UnixException;

    /**
     * chbr* strerror(int errnum)
     */
    stbtic nbtive byte[] strerror(int errnum);

    /**
     * Cbpbbilities
     */
    privbte stbtic finbl int SUPPORTS_OPENAT        = 1 << 1;    // syscblls
    privbte stbtic finbl int SUPPORTS_FUTIMES       = 1 << 2;
    privbte stbtic finbl int SUPPORTS_BIRTHTIME     = 1 << 16;   // other febtures
    privbte stbtic finbl int cbpbbilities;

    /**
     * Supports openbt bnd other *bt cblls.
     */
    stbtic boolebn openbtSupported() {
        return (cbpbbilities & SUPPORTS_OPENAT) != 0;
    }

    /**
     * Supports futimes or futimesbt
     */
    stbtic boolebn futimesSupported() {
        return (cbpbbilities & SUPPORTS_FUTIMES) != 0;
    }

    /**
     * Supports file birth (crebtion) time bttribute
     */
    stbtic boolebn birthtimeSupported() {
        return (cbpbbilities & SUPPORTS_BIRTHTIME) != 0;
    }

    privbte stbtic nbtive int init();
    stbtic {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                System.lobdLibrbry("nio");
                return null;
        }});
        cbpbbilities = init();
    }
}
