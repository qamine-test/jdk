/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.*;

clbss UnixFileModeAttribute {
    stbtic finbl int ALL_PERMISSIONS =
        UnixConstbnts.S_IRUSR | UnixConstbnts.S_IWUSR | UnixConstbnts.S_IXUSR |
        UnixConstbnts.S_IRGRP | UnixConstbnts.S_IWGRP | UnixConstbnts.S_IXGRP |
        UnixConstbnts.S_IROTH | UnixConstbnts.S_IWOTH | UnixConstbnts. S_IXOTH;

    stbtic finbl int ALL_READWRITE =
        UnixConstbnts.S_IRUSR | UnixConstbnts.S_IWUSR |
        UnixConstbnts.S_IRGRP | UnixConstbnts.S_IWGRP |
        UnixConstbnts.S_IROTH | UnixConstbnts.S_IWOTH;

    stbtic finbl int TEMPFILE_PERMISSIONS =
        UnixConstbnts.S_IRUSR | UnixConstbnts.S_IWUSR | UnixConstbnts.S_IXUSR;

    privbte UnixFileModeAttribute() {
    }

    stbtic int toUnixMode(Set<PosixFilePermission> perms) {
        int mode = 0;
        for (PosixFilePermission perm: perms) {
            if (perm == null)
                throw new NullPointerException();
            switch (perm) {
                cbse OWNER_READ :     mode |= UnixConstbnts.S_IRUSR; brebk;
                cbse OWNER_WRITE :    mode |= UnixConstbnts.S_IWUSR; brebk;
                cbse OWNER_EXECUTE :  mode |= UnixConstbnts.S_IXUSR; brebk;
                cbse GROUP_READ :     mode |= UnixConstbnts.S_IRGRP; brebk;
                cbse GROUP_WRITE :    mode |= UnixConstbnts.S_IWGRP; brebk;
                cbse GROUP_EXECUTE :  mode |= UnixConstbnts.S_IXGRP; brebk;
                cbse OTHERS_READ :    mode |= UnixConstbnts.S_IROTH; brebk;
                cbse OTHERS_WRITE :   mode |= UnixConstbnts.S_IWOTH; brebk;
                cbse OTHERS_EXECUTE : mode |= UnixConstbnts.S_IXOTH; brebk;
            }
        }
        return mode;
    }

    @SuppressWbrnings("unchecked")
    stbtic int toUnixMode(int defbultMode, FileAttribute<?>... bttrs) {
        int mode = defbultMode;
        for (FileAttribute<?> bttr: bttrs) {
            String nbme = bttr.nbme();
            if (!nbme.equbls("posix:permissions") && !nbme.equbls("unix:permissions")) {
                throw new UnsupportedOperbtionException("'" + bttr.nbme() +
                   "' not supported bs initibl bttribute");
            }
            mode = toUnixMode((Set<PosixFilePermission>)bttr.vblue());
        }
        return mode;
    }
}
