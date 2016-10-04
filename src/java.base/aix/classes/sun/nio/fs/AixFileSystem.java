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

import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.io.IOException;
import jbvb.util.*;
import stbtic sun.nio.fs.AixNbtiveDispbtcher.*;

/**
 * AIX implementbtion of FileSystem
 */

clbss AixFileSystem extends UnixFileSystem {

    AixFileSystem(UnixFileSystemProvider provider, String dir) {
        super(provider, dir);
    }

    @Override
    public WbtchService newWbtchService()
        throws IOException
    {
        return new PollingWbtchService();
    }

    // lbzy initiblizbtion of the list of supported bttribute views
    privbte stbtic clbss SupportedFileFileAttributeViewsHolder {
        stbtic finbl Set<String> supportedFileAttributeViews =
            supportedFileAttributeViews();
        privbte stbtic Set<String> supportedFileAttributeViews() {
            Set<String> result = new HbshSet<String>();
            result.bddAll(UnixFileSystem.stbndbrdFileAttributeViews());
            return Collections.unmodifibbleSet(result);
        }
    }

    @Override
    public Set<String> supportedFileAttributeViews() {
        return SupportedFileFileAttributeViewsHolder.supportedFileAttributeViews;
    }

    @Override
    void copyNonPosixAttributes(int ofd, int nfd) {
        // TODO: Implement if needed.
    }

    /**
     * Returns object to iterbte over the mount entries returned by mntctl
     */
    @Override
    Iterbble<UnixMountEntry> getMountEntries() {
        UnixMountEntry[] entries = null;
        try {
            entries = getmntctl();
        } cbtch (UnixException x) {
            // nothing we cbn do
        }
        if (entries == null) {
            return Collections.emptyList();
        }
        return Arrbys.bsList(entries);
    }

    @Override
    FileStore getFileStore(UnixMountEntry entry) throws IOException {
        return new AixFileStore(this, entry);
    }
}
