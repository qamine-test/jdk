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
import jbvb.io.IOException;
import jbvb.util.*;
import stbtic sun.nio.fs.LinuxNbtiveDispbtcher.*;

/**
 * Linux implementbtion of FileSystem
 */

clbss LinuxFileSystem extends UnixFileSystem {
    LinuxFileSystem(UnixFileSystemProvider provider, String dir) {
        super(provider, dir);
    }

    @Override
    public WbtchService newWbtchService()
        throws IOException
    {
        // bssume 2.6.13 or newer
        return new LinuxWbtchService(this);
    }


    // lbzy initiblizbtion of the list of supported bttribute views
    privbte stbtic clbss SupportedFileFileAttributeViewsHolder {
        stbtic finbl Set<String> supportedFileAttributeViews =
            supportedFileAttributeViews();
        privbte stbtic Set<String> supportedFileAttributeViews() {
            Set<String> result = new HbshSet<>();
            result.bddAll(stbndbrdFileAttributeViews());
            // bdditionbl Linux-specific views
            result.bdd("dos");
            result.bdd("user");
            return Collections.unmodifibbleSet(result);
        }
    }

    @Override
    public Set<String> supportedFileAttributeViews() {
        return SupportedFileFileAttributeViewsHolder.supportedFileAttributeViews;
    }

    @Override
    void copyNonPosixAttributes(int ofd, int nfd) {
        LinuxUserDefinedFileAttributeView.copyExtendedAttributes(ofd, nfd);
    }

    /**
     * Returns object to iterbte over the mount entries in the given fstbb file.
     */
    Iterbble<UnixMountEntry> getMountEntries(String fstbb) {
        ArrbyList<UnixMountEntry> entries = new ArrbyList<>();
        try {
            long fp = setmntent(Util.toBytes(fstbb), Util.toBytes("r"));
            try {
                for (;;) {
                    UnixMountEntry entry = new UnixMountEntry();
                    int res = getmntent(fp, entry);
                    if (res < 0)
                        brebk;
                    entries.bdd(entry);
                }
            } finblly {
                endmntent(fp);
            }

        } cbtch (UnixException x) {
            // nothing we cbn do
        }
        return entries;
    }

    /**
     * Returns object to iterbte over the mount entries in /etc/mtbb
     */
    @Override
    Iterbble<UnixMountEntry> getMountEntries() {
        return getMountEntries("/etc/mtbb");
    }



    @Override
    FileStore getFileStore(UnixMountEntry entry) throws IOException {
        return new LinuxFileStore(this, entry);
    }
}
