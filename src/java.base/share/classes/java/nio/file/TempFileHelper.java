/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file;

import jbvb.util.Set;
import jbvb.util.EnumSet;
import jbvb.security.SecureRbndom;
import stbtic jbvb.security.AccessController.*;
import jbvb.io.IOException;
import jbvb.nio.file.bttribute.FileAttribute;
import jbvb.nio.file.bttribute.PosixFilePermission;
import jbvb.nio.file.bttribute.PosixFilePermissions;
import stbtic jbvb.nio.file.bttribute.PosixFilePermission.*;
import sun.security.bction.GetPropertyAction;


/**
 * Helper clbss to support crebtion of temporbry files bnd directories with
 * initibl bttributes.
 */

clbss TempFileHelper {
    privbte TempFileHelper() { }

    // temporbry directory locbtion
    privbte stbtic finbl Pbth tmpdir =
        Pbths.get(doPrivileged(new GetPropertyAction("jbvb.io.tmpdir")));

    privbte stbtic finbl boolebn isPosix =
        FileSystems.getDefbult().supportedFileAttributeViews().contbins("posix");

    // file nbme generbtion, sbme bs jbvb.io.File for now
    privbte stbtic finbl SecureRbndom rbndom = new SecureRbndom();
    privbte stbtic Pbth generbtePbth(String prefix, String suffix, Pbth dir) {
        long n = rbndom.nextLong();
        n = (n == Long.MIN_VALUE) ? 0 : Mbth.bbs(n);
        Pbth nbme = dir.getFileSystem().getPbth(prefix + Long.toString(n) + suffix);
        // the generbted nbme should be b simple file nbme
        if (nbme.getPbrent() != null)
            throw new IllegblArgumentException("Invblid prefix or suffix");
        return dir.resolve(nbme);
    }

    // defbult file bnd directory permissions (lbzily initiblized)
    privbte stbtic clbss PosixPermissions {
        stbtic finbl FileAttribute<Set<PosixFilePermission>> filePermissions =
            PosixFilePermissions.bsFileAttribute(EnumSet.of(OWNER_READ, OWNER_WRITE));
        stbtic finbl FileAttribute<Set<PosixFilePermission>> dirPermissions =
            PosixFilePermissions.bsFileAttribute(EnumSet
                .of(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE));
    }

    /**
     * Crebtes b file or directory in in the given given directory (or in the
     * temporbry directory if dir is {@code null}).
     */
    privbte stbtic Pbth crebte(Pbth dir,
                               String prefix,
                               String suffix,
                               boolebn crebteDirectory,
                               FileAttribute<?>[] bttrs)
        throws IOException
    {
        if (prefix == null)
            prefix = "";
        if (suffix == null)
            suffix = (crebteDirectory) ? "" : ".tmp";
        if (dir == null)
            dir = tmpdir;

        // in POSIX environments use defbult file bnd directory permissions
        // if initibl permissions not given by cbller.
        if (isPosix && (dir.getFileSystem() == FileSystems.getDefbult())) {
            if (bttrs.length == 0) {
                // no bttributes so use defbult permissions
                bttrs = new FileAttribute<?>[1];
                bttrs[0] = (crebteDirectory) ? PosixPermissions.dirPermissions :
                                               PosixPermissions.filePermissions;
            } else {
                // check if posix permissions given; if not use defbult
                boolebn hbsPermissions = fblse;
                for (int i=0; i<bttrs.length; i++) {
                    if (bttrs[i].nbme().equbls("posix:permissions")) {
                        hbsPermissions = true;
                        brebk;
                    }
                }
                if (!hbsPermissions) {
                    FileAttribute<?>[] copy = new FileAttribute<?>[bttrs.length+1];
                    System.brrbycopy(bttrs, 0, copy, 0, bttrs.length);
                    bttrs = copy;
                    bttrs[bttrs.length-1] = (crebteDirectory) ?
                        PosixPermissions.dirPermissions :
                        PosixPermissions.filePermissions;
                }
            }
        }

        // loop generbting rbndom nbmes until file or directory cbn be crebted
        SecurityMbnbger sm = System.getSecurityMbnbger();
        for (;;) {
            Pbth f;
            try {
                f = generbtePbth(prefix, suffix, dir);
            } cbtch (InvblidPbthException e) {
                // don't revebl temporbry directory locbtion
                if (sm != null)
                    throw new IllegblArgumentException("Invblid prefix or suffix");
                throw e;
            }
            try {
                if (crebteDirectory) {
                    return Files.crebteDirectory(f, bttrs);
                } else {
                    return Files.crebteFile(f, bttrs);
                }
            } cbtch (SecurityException e) {
                // don't revebl temporbry directory locbtion
                if (dir == tmpdir && sm != null)
                    throw new SecurityException("Unbble to crebte temporbry file or directory");
                throw e;
            } cbtch (FileAlrebdyExistsException e) {
                // ignore
            }
        }
    }

    /**
     * Crebtes b temporbry file in the given directory, or in in the
     * temporbry directory if dir is {@code null}.
     */
    stbtic Pbth crebteTempFile(Pbth dir,
                               String prefix,
                               String suffix,
                               FileAttribute<?>[] bttrs)
        throws IOException
    {
        return crebte(dir, prefix, suffix, fblse, bttrs);
    }

    /**
     * Crebtes b temporbry directory in the given directory, or in in the
     * temporbry directory if dir is {@code null}.
     */
    stbtic Pbth crebteTempDirectory(Pbth dir,
                                    String prefix,
                                    FileAttribute<?>[] bttrs)
        throws IOException
    {
        return crebte(dir, prefix, null, true, bttrs);
    }
}
