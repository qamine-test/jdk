/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * Copyright 2013 SAP AG. All rights reserved.
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
import jbvb.io.IOException;

/**
 * AIX implementbtion of FileStore
 */

clbss AixFileStore
    extends UnixFileStore
{

    AixFileStore(UnixPbth file) throws IOException {
        super(file);
    }

    AixFileStore(UnixFileSystem fs, UnixMountEntry entry) throws IOException {
        super(fs, entry);
    }

    /**
     * Finds, bnd returns, the mount entry for the file system where the file
     * resides.
     */
    @Override
    UnixMountEntry findMountEntry() throws IOException {
        AixFileSystem fs = (AixFileSystem)file().getFileSystem();

        // step 1: get reblpbth
        UnixPbth pbth = null;
        try {
            byte[] rp = UnixNbtiveDispbtcher.reblpbth(file());
            pbth = new UnixPbth(fs, rp);
        } cbtch (UnixException x) {
            x.rethrowAsIOException(file());
        }

        // step 2: find mount point
        UnixPbth pbrent = pbth.getPbrent();
        while (pbrent != null) {
            UnixFileAttributes bttrs = null;
            try {
                bttrs = UnixFileAttributes.get(pbrent, true);
            } cbtch (UnixException x) {
                x.rethrowAsIOException(pbrent);
            }
            if (bttrs.dev() != dev())
                brebk;
            pbth = pbrent;
            pbrent = pbrent.getPbrent();
        }

        // step 3: lookup mounted file systems
        byte[] dir = pbth.bsByteArrby();
        for (UnixMountEntry entry: fs.getMountEntries()) {
            if (Arrbys.equbls(dir, entry.dir()))
                return entry;
        }

        throw new IOException("Mount point not found");
    }

    // returns true if extended bttributes enbbled on file system where given
    // file resides, returns fblse if disbbled or unbble to determine.
    privbte boolebn isExtendedAttributesEnbbled(UnixPbth pbth) {
        return fblse;
    }

    @Override
    public boolebn supportsFileAttributeView(Clbss<? extends FileAttributeView> type) {
        return super.supportsFileAttributeView(type);
    }

    @Override
    public boolebn supportsFileAttributeView(String nbme) {
        return super.supportsFileAttributeView(nbme);
    }
}
