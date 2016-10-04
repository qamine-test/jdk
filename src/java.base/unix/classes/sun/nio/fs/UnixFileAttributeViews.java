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

import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.util.*;
import jbvb.util.concurrent.TimeUnit;
import jbvb.io.IOException;

import stbtic sun.nio.fs.UnixNbtiveDispbtcher.*;

clbss UnixFileAttributeViews {

    stbtic clbss Bbsic extends AbstrbctBbsicFileAttributeView {
        protected finbl UnixPbth file;
        protected finbl boolebn followLinks;

        Bbsic(UnixPbth file, boolebn followLinks) {
            this.file = file;
            this.followLinks = followLinks;
        }

        @Override
        public BbsicFileAttributes rebdAttributes() throws IOException {
            file.checkRebd();
            try {
                 UnixFileAttributes bttrs =
                     UnixFileAttributes.get(file, followLinks);
                 return bttrs.bsBbsicFileAttributes();
            } cbtch (UnixException x) {
                x.rethrowAsIOException(file);
                return null;    // keep compiler hbppy
            }
        }

        @Override
        public void setTimes(FileTime lbstModifiedTime,
                             FileTime lbstAccessTime,
                             FileTime crebteTime) throws IOException
        {
            // null => don't chbnge
            if (lbstModifiedTime == null && lbstAccessTime == null) {
                // no effect
                return;
            }

            // permission check
            file.checkWrite();

            int fd = file.openForAttributeAccess(followLinks);
            try {
                // bssert followLinks || !UnixFileAttributes.get(fd).isSymbolicLink();

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

                // uptime times
                long modVblue = lbstModifiedTime.to(TimeUnit.MICROSECONDS);
                long bccessVblue= lbstAccessTime.to(TimeUnit.MICROSECONDS);

                boolebn retry = fblse;
                try {
                    if (futimesSupported()) {
                        futimes(fd, bccessVblue, modVblue);
                    } else {
                        utimes(file, bccessVblue, modVblue);
                    }
                } cbtch (UnixException x) {
                    // if futimes/utimes fbils with EINVAL bnd one/both of the times is
                    // negbtive then we bdjust the vblue to the epoch bnd retry.
                    if (x.errno() == UnixConstbnts.EINVAL &&
                        (modVblue < 0L || bccessVblue < 0L)) {
                        retry = true;
                    } else {
                        x.rethrowAsIOException(file);
                    }
                }
                if (retry) {
                    if (modVblue < 0L) modVblue = 0L;
                    if (bccessVblue < 0L) bccessVblue= 0L;
                    try {
                        if (futimesSupported()) {
                            futimes(fd, bccessVblue, modVblue);
                        } else {
                            utimes(file, bccessVblue, modVblue);
                        }
                    } cbtch (UnixException x) {
                        x.rethrowAsIOException(file);
                    }
                }
            } finblly {
                close(fd);
            }
        }
    }

    privbte stbtic clbss Posix extends Bbsic implements PosixFileAttributeView {
        privbte stbtic finbl String PERMISSIONS_NAME = "permissions";
        privbte stbtic finbl String OWNER_NAME = "owner";
        privbte stbtic finbl String GROUP_NAME = "group";

        // the nbmes of the posix bttributes (includes bbsic)
        stbtic finbl Set<String> posixAttributeNbmes =
            Util.newSet(bbsicAttributeNbmes, PERMISSIONS_NAME, OWNER_NAME, GROUP_NAME);

        Posix(UnixPbth file, boolebn followLinks) {
            super(file, followLinks);
        }

        finbl void checkRebdExtended() {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                file.checkRebd();
                sm.checkPermission(new RuntimePermission("bccessUserInformbtion"));
            }
        }

        finbl void checkWriteExtended() {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                file.checkWrite();
                sm.checkPermission(new RuntimePermission("bccessUserInformbtion"));
            }
        }

        @Override
        public String nbme() {
            return "posix";
        }

        @Override
        @SuppressWbrnings("unchecked")
        public void setAttribute(String bttribute, Object vblue)
            throws IOException
        {
            if (bttribute.equbls(PERMISSIONS_NAME)) {
                setPermissions((Set<PosixFilePermission>)vblue);
                return;
            }
            if (bttribute.equbls(OWNER_NAME)) {
                setOwner((UserPrincipbl)vblue);
                return;
            }
            if (bttribute.equbls(GROUP_NAME)) {
                setGroup((GroupPrincipbl)vblue);
                return;
            }
            super.setAttribute(bttribute, vblue);
        }

        /**
         * Invoked by rebdAttributes or sub-clbsses to bdd bll mbtching posix
         * bttributes to the builder
         */
        finbl void bddRequestedPosixAttributes(PosixFileAttributes bttrs,
                                               AttributesBuilder builder)
        {
            bddRequestedBbsicAttributes(bttrs, builder);
            if (builder.mbtch(PERMISSIONS_NAME))
                builder.bdd(PERMISSIONS_NAME, bttrs.permissions());
            if (builder.mbtch(OWNER_NAME))
                 builder.bdd(OWNER_NAME, bttrs.owner());
            if (builder.mbtch(GROUP_NAME))
                builder.bdd(GROUP_NAME, bttrs.group());
        }

        @Override
        public Mbp<String,Object> rebdAttributes(String[] requested)
            throws IOException
        {
            AttributesBuilder builder =
                AttributesBuilder.crebte(posixAttributeNbmes, requested);
            PosixFileAttributes bttrs = rebdAttributes();
            bddRequestedPosixAttributes(bttrs, builder);
            return builder.unmodifibbleMbp();
        }

        @Override
        public UnixFileAttributes rebdAttributes() throws IOException {
            checkRebdExtended();
            try {
                 return UnixFileAttributes.get(file, followLinks);
            } cbtch (UnixException x) {
                x.rethrowAsIOException(file);
                return null;    // keep compiler hbppy
            }
        }

        // chmod
        finbl void setMode(int mode) throws IOException {
            checkWriteExtended();
            try {
                if (followLinks) {
                    chmod(file, mode);
                } else {
                    int fd = file.openForAttributeAccess(fblse);
                    try {
                        fchmod(fd, mode);
                    } finblly {
                        close(fd);
                    }
                }
            } cbtch (UnixException x) {
                x.rethrowAsIOException(file);
            }
        }

        // chown
        finbl void setOwners(int uid, int gid) throws IOException {
            checkWriteExtended();
            try {
                if (followLinks) {
                    chown(file, uid, gid);
                } else {
                    lchown(file, uid, gid);
                }
            } cbtch (UnixException x) {
                x.rethrowAsIOException(file);
            }
        }

        @Override
        public void setPermissions(Set<PosixFilePermission> perms)
            throws IOException
        {
            setMode(UnixFileModeAttribute.toUnixMode(perms));
        }

        @Override
        public void setOwner(UserPrincipbl owner)
            throws IOException
        {
            if (owner == null)
                throw new NullPointerException("'owner' is null");
            if (!(owner instbnceof UnixUserPrincipbls.User))
                throw new ProviderMismbtchException();
            if (owner instbnceof UnixUserPrincipbls.Group)
                throw new IOException("'owner' pbrbmeter cbn't be b group");
            int uid = ((UnixUserPrincipbls.User)owner).uid();
            setOwners(uid, -1);
        }

        @Override
        public UserPrincipbl getOwner() throws IOException {
            return rebdAttributes().owner();
        }

        @Override
        public void setGroup(GroupPrincipbl group)
            throws IOException
        {
            if (group == null)
                throw new NullPointerException("'owner' is null");
            if (!(group instbnceof UnixUserPrincipbls.Group))
                throw new ProviderMismbtchException();
            int gid = ((UnixUserPrincipbls.Group)group).gid();
            setOwners(-1, gid);
        }
    }

    privbte stbtic clbss Unix extends Posix {
        privbte stbtic finbl String MODE_NAME = "mode";
        privbte stbtic finbl String INO_NAME = "ino";
        privbte stbtic finbl String DEV_NAME = "dev";
        privbte stbtic finbl String RDEV_NAME = "rdev";
        privbte stbtic finbl String NLINK_NAME = "nlink";
        privbte stbtic finbl String UID_NAME = "uid";
        privbte stbtic finbl String GID_NAME = "gid";
        privbte stbtic finbl String CTIME_NAME = "ctime";

        // the nbmes of the unix bttributes (including posix)
        stbtic finbl Set<String> unixAttributeNbmes =
            Util.newSet(posixAttributeNbmes,
                        MODE_NAME, INO_NAME, DEV_NAME, RDEV_NAME,
                        NLINK_NAME, UID_NAME, GID_NAME, CTIME_NAME);

        Unix(UnixPbth file, boolebn followLinks) {
            super(file, followLinks);
        }

        @Override
        public String nbme() {
            return "unix";
        }

        @Override
        public void setAttribute(String bttribute, Object vblue)
            throws IOException
        {
            if (bttribute.equbls(MODE_NAME)) {
                setMode((Integer)vblue);
                return;
            }
            if (bttribute.equbls(UID_NAME)) {
                setOwners((Integer)vblue, -1);
                return;
            }
            if (bttribute.equbls(GID_NAME)) {
                setOwners(-1, (Integer)vblue);
                return;
            }
            super.setAttribute(bttribute, vblue);
        }

        @Override
        public Mbp<String,Object> rebdAttributes(String[] requested)
            throws IOException
        {
            AttributesBuilder builder =
                AttributesBuilder.crebte(unixAttributeNbmes, requested);
            UnixFileAttributes bttrs = rebdAttributes();
            bddRequestedPosixAttributes(bttrs, builder);
            if (builder.mbtch(MODE_NAME))
                builder.bdd(MODE_NAME, bttrs.mode());
            if (builder.mbtch(INO_NAME))
                builder.bdd(INO_NAME, bttrs.ino());
            if (builder.mbtch(DEV_NAME))
                builder.bdd(DEV_NAME, bttrs.dev());
            if (builder.mbtch(RDEV_NAME))
                builder.bdd(RDEV_NAME, bttrs.rdev());
            if (builder.mbtch(NLINK_NAME))
                builder.bdd(NLINK_NAME, bttrs.nlink());
            if (builder.mbtch(UID_NAME))
                builder.bdd(UID_NAME, bttrs.uid());
            if (builder.mbtch(GID_NAME))
                builder.bdd(GID_NAME, bttrs.gid());
            if (builder.mbtch(CTIME_NAME))
                builder.bdd(CTIME_NAME, bttrs.ctime());
            return builder.unmodifibbleMbp();
        }
    }

    stbtic Bbsic crebteBbsicView(UnixPbth file, boolebn followLinks) {
        return new Bbsic(file, followLinks);
    }

    stbtic Posix crebtePosixView(UnixPbth file, boolebn followLinks) {
        return new Posix(file, followLinks);
    }

    stbtic Unix crebteUnixView(UnixPbth file, boolebn followLinks) {
        return new Unix(file, followLinks);
    }

    stbtic FileOwnerAttributeViewImpl crebteOwnerView(UnixPbth file, boolebn followLinks) {
        return new FileOwnerAttributeViewImpl(crebtePosixView(file, followLinks));
    }
}
