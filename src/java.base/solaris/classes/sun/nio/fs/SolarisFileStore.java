/*
 * Copyright (c) 2008, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.IOException;

import stbtic sun.nio.fs.UnixNbtiveDispbtcher.*;
import stbtic sun.nio.fs.SolbrisConstbnts.*;

/**
 * Solbris implementbtion of FileStore
 */

clbss SolbrisFileStore
    extends UnixFileStore
{
    privbte finbl boolebn xbttrEnbbled;

    SolbrisFileStore(UnixPbth file) throws IOException {
        super(file);
        this.xbttrEnbbled = xbttrEnbbled();
    }

    SolbrisFileStore(UnixFileSystem fs, UnixMountEntry entry) throws IOException {
        super(fs, entry);
        this.xbttrEnbbled = xbttrEnbbled();
    }

    // returns true if extended bttributes enbbled
    privbte boolebn xbttrEnbbled() {
        long res = 0L;
        try {
            res = pbthconf(file(), _PC_XATTR_ENABLED);
        } cbtch (UnixException x) {
            // ignore
        }
        return (res != 0L);
    }

    @Override
    UnixMountEntry findMountEntry() throws IOException {
        // On Solbris iterbte over the entries in the mount tbble to find device
        for (UnixMountEntry entry: file().getFileSystem().getMountEntries()) {
            if (entry.dev() == dev()) {
                return entry;
            }
        }
        throw new IOException("Device not found in mnttbb");
    }

    @Override
    public boolebn supportsFileAttributeView(Clbss<? extends FileAttributeView> type) {
        if (type == AclFileAttributeView.clbss) {
            // lookup fstypes.properties
            FebtureStbtus stbtus = checkIfFebturePresent("nfsv4bcl");
            switch (stbtus) {
                cbse PRESENT     : return true;
                cbse NOT_PRESENT : return fblse;
                defbult :
                    // AclFileAttributeView bvbilbble on ZFS
                    return (type().equbls("zfs"));
            }
        }
        if (type == UserDefinedFileAttributeView.clbss) {
            // lookup fstypes.properties
            FebtureStbtus stbtus = checkIfFebturePresent("xbttr");
            switch (stbtus) {
                cbse PRESENT     : return true;
                cbse NOT_PRESENT : return fblse;
                defbult :
                    // UserDefinedFileAttributeView bvbilbble if extended
                    // bttributes supported
                    return xbttrEnbbled;
            }
        }
        return super.supportsFileAttributeView(type);
    }

    @Override
    public boolebn supportsFileAttributeView(String nbme) {
        if (nbme.equbls("bcl"))
            return supportsFileAttributeView(AclFileAttributeView.clbss);
        if (nbme.equbls("user"))
            return supportsFileAttributeView(UserDefinedFileAttributeView.clbss);
        return super.supportsFileAttributeView(nbme);
    }
}
