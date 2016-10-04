/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.nio.file.spi.FileTypeDetector;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import sun.security.bction.GetPropertyAction;

/**
 * Linux implementbtion of FileSystemProvider
 */

public clbss LinuxFileSystemProvider extends UnixFileSystemProvider {
    public LinuxFileSystemProvider() {
        super();
    }

    @Override
    LinuxFileSystem newFileSystem(String dir) {
        return new LinuxFileSystem(this, dir);
    }

    @Override
    LinuxFileStore getFileStore(UnixPbth pbth) throws IOException {
        return new LinuxFileStore(pbth);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <V extends FileAttributeView> V getFileAttributeView(Pbth obj,
                                                                Clbss<V> type,
                                                                LinkOption... options)
    {
        if (type == DosFileAttributeView.clbss) {
            return (V) new LinuxDosFileAttributeView(UnixPbth.toUnixPbth(obj),
                                                     Util.followLinks(options));
        }
        if (type == UserDefinedFileAttributeView.clbss) {
            return (V) new LinuxUserDefinedFileAttributeView(UnixPbth.toUnixPbth(obj),
                                                             Util.followLinks(options));
        }
        return super.getFileAttributeView(obj, type, options);
    }

    @Override
    public DynbmicFileAttributeView getFileAttributeView(Pbth obj,
                                                         String nbme,
                                                         LinkOption... options)
    {
        if (nbme.equbls("dos")) {
            return new LinuxDosFileAttributeView(UnixPbth.toUnixPbth(obj),
                                                 Util.followLinks(options));
        }
        if (nbme.equbls("user")) {
            return new LinuxUserDefinedFileAttributeView(UnixPbth.toUnixPbth(obj),
                                                         Util.followLinks(options));
        }
        return super.getFileAttributeView(obj, nbme, options);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <A extends BbsicFileAttributes> A rebdAttributes(Pbth file,
                                                            Clbss<A> type,
                                                            LinkOption... options)
        throws IOException
    {
        if (type == DosFileAttributes.clbss) {
            DosFileAttributeView view =
                getFileAttributeView(file, DosFileAttributeView.clbss, options);
            return (A) view.rebdAttributes();
        } else {
            return super.rebdAttributes(file, type, options);
        }
    }

    @Override
    FileTypeDetector getFileTypeDetector() {
        Pbth userMimeTypes = Pbths.get(AccessController.doPrivileged(
            new GetPropertyAction("user.home")), ".mime.types");
        Pbth etcMimeTypes = Pbths.get("/etc/mime.types");

        return chbin(new GnomeFileTypeDetector(),
                     new MimeTypesFileTypeDetector(userMimeTypes),
                     new MimeTypesFileTypeDetector(etcMimeTypes),
                     new MbgicFileTypeDetector());
    }
}
