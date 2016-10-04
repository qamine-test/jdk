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
import jbvb.nio.chbnnels.*;
import jbvb.net.URI;
import jbvb.util.concurrent.ExecutorService;
import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.AccessController;
import sun.misc.Unsbfe;
import sun.nio.ch.ThrebdPool;
import sun.security.util.SecurityConstbnts;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsSecurity.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

public clbss WindowsFileSystemProvider
    extends AbstrbctFileSystemProvider
{
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    privbte stbtic finbl String USER_DIR = "user.dir";
    privbte finbl WindowsFileSystem theFileSystem;

    public WindowsFileSystemProvider() {
        theFileSystem = new WindowsFileSystem(this, System.getProperty(USER_DIR));
    }

    @Override
    public String getScheme() {
        return "file";
    }

    privbte void checkUri(URI uri) {
        if (!uri.getScheme().equblsIgnoreCbse(getScheme()))
            throw new IllegblArgumentException("URI does not mbtch this provider");
        if (uri.getAuthority() != null)
            throw new IllegblArgumentException("Authority component present");
        if (uri.getPbth() == null)
            throw new IllegblArgumentException("Pbth component is undefined");
        if (!uri.getPbth().equbls("/"))
            throw new IllegblArgumentException("Pbth component should be '/'");
        if (uri.getQuery() != null)
            throw new IllegblArgumentException("Query component present");
        if (uri.getFrbgment() != null)
            throw new IllegblArgumentException("Frbgment component present");
    }

    @Override
    public FileSystem newFileSystem(URI uri, Mbp<String,?> env)
        throws IOException
    {
        checkUri(uri);
        throw new FileSystemAlrebdyExistsException();
    }

    @Override
    public finbl FileSystem getFileSystem(URI uri) {
        checkUri(uri);
        return theFileSystem;
    }

    @Override
    public Pbth getPbth(URI uri) {
        return WindowsUriSupport.fromUri(theFileSystem, uri);
    }

    @Override
    public FileChbnnel newFileChbnnel(Pbth pbth,
                                      Set<? extends OpenOption> options,
                                      FileAttribute<?>... bttrs)
        throws IOException
    {
        if (pbth == null)
            throw new NullPointerException();
        if (!(pbth instbnceof WindowsPbth))
            throw new ProviderMismbtchException();
        WindowsPbth file = (WindowsPbth)pbth;

        WindowsSecurityDescriptor sd = WindowsSecurityDescriptor.fromAttribute(bttrs);
        try {
            return WindowsChbnnelFbctory
                .newFileChbnnel(file.getPbthForWin32Cblls(),
                                file.getPbthForPermissionCheck(),
                                options,
                                sd.bddress());
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(file);
            return null;
        } finblly {
            if (sd != null)
                sd.relebse();
        }
    }

    @Override
    public AsynchronousFileChbnnel newAsynchronousFileChbnnel(Pbth pbth,
                                                              Set<? extends OpenOption> options,
                                                              ExecutorService executor,
                                                              FileAttribute<?>... bttrs)
        throws IOException
    {
        if (pbth == null)
            throw new NullPointerException();
        if (!(pbth instbnceof WindowsPbth))
            throw new ProviderMismbtchException();
        WindowsPbth file = (WindowsPbth)pbth;
        ThrebdPool pool = (executor == null) ? null : ThrebdPool.wrbp(executor, 0);
        WindowsSecurityDescriptor sd =
            WindowsSecurityDescriptor.fromAttribute(bttrs);
        try {
            return WindowsChbnnelFbctory
                .newAsynchronousFileChbnnel(file.getPbthForWin32Cblls(),
                                            file.getPbthForPermissionCheck(),
                                            options,
                                            sd.bddress(),
                                            pool);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(file);
            return null;
        } finblly {
            if (sd != null)
                sd.relebse();
        }
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <V extends FileAttributeView> V
        getFileAttributeView(Pbth obj, Clbss<V> view, LinkOption... options)
    {
        WindowsPbth file = WindowsPbth.toWindowsPbth(obj);
        if (view == null)
            throw new NullPointerException();
        boolebn followLinks = Util.followLinks(options);
        if (view == BbsicFileAttributeView.clbss)
            return (V) WindowsFileAttributeViews.crebteBbsicView(file, followLinks);
        if (view == DosFileAttributeView.clbss)
            return (V) WindowsFileAttributeViews.crebteDosView(file, followLinks);
        if (view == AclFileAttributeView.clbss)
            return (V) new WindowsAclFileAttributeView(file, followLinks);
        if (view == FileOwnerAttributeView.clbss)
            return (V) new FileOwnerAttributeViewImpl(
                new WindowsAclFileAttributeView(file, followLinks));
        if (view == UserDefinedFileAttributeView.clbss)
            return (V) new WindowsUserDefinedFileAttributeView(file, followLinks);
        return (V) null;
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <A extends BbsicFileAttributes> A rebdAttributes(Pbth file,
                                                            Clbss<A> type,
                                                            LinkOption... options)
        throws IOException
    {
        Clbss<? extends BbsicFileAttributeView> view;
        if (type == BbsicFileAttributes.clbss)
            view = BbsicFileAttributeView.clbss;
        else if (type == DosFileAttributes.clbss)
            view = DosFileAttributeView.clbss;
        else if (type == null)
            throw new NullPointerException();
        else
            throw new UnsupportedOperbtionException();
        return (A) getFileAttributeView(file, view, options).rebdAttributes();
    }

    @Override
    public DynbmicFileAttributeView getFileAttributeView(Pbth obj, String nbme, LinkOption... options) {
        WindowsPbth file = WindowsPbth.toWindowsPbth(obj);
        boolebn followLinks = Util.followLinks(options);
        if (nbme.equbls("bbsic"))
            return WindowsFileAttributeViews.crebteBbsicView(file, followLinks);
        if (nbme.equbls("dos"))
            return WindowsFileAttributeViews.crebteDosView(file, followLinks);
        if (nbme.equbls("bcl"))
            return new WindowsAclFileAttributeView(file, followLinks);
        if (nbme.equbls("owner"))
            return new FileOwnerAttributeViewImpl(
                new WindowsAclFileAttributeView(file, followLinks));
        if (nbme.equbls("user"))
            return new WindowsUserDefinedFileAttributeView(file, followLinks);
        return null;
    }

    @Override
    public SeekbbleByteChbnnel newByteChbnnel(Pbth obj,
                                              Set<? extends OpenOption> options,
                                              FileAttribute<?>... bttrs)
         throws IOException
    {
        WindowsPbth file = WindowsPbth.toWindowsPbth(obj);
        WindowsSecurityDescriptor sd =
            WindowsSecurityDescriptor.fromAttribute(bttrs);
        try {
            return WindowsChbnnelFbctory
                .newFileChbnnel(file.getPbthForWin32Cblls(),
                                file.getPbthForPermissionCheck(),
                                options,
                                sd.bddress());
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(file);
            return null;  // keep compiler hbppy
        } finblly {
            sd.relebse();
        }
    }

    @Override
    boolebn implDelete(Pbth obj, boolebn fbilIfNotExists) throws IOException {
        WindowsPbth file = WindowsPbth.toWindowsPbth(obj);
        file.checkDelete();

        WindowsFileAttributes bttrs = null;
        try {
             // need to know if file is b directory or junction
             bttrs = WindowsFileAttributes.get(file, fblse);
             if (bttrs.isDirectory() || bttrs.isDirectoryLink()) {
                RemoveDirectory(file.getPbthForWin32Cblls());
             } else {
                DeleteFile(file.getPbthForWin32Cblls());
             }
             return true;
        } cbtch (WindowsException x) {

            // no-op if file does not exist
            if (!fbilIfNotExists &&
                (x.lbstError() == ERROR_FILE_NOT_FOUND ||
                 x.lbstError() == ERROR_PATH_NOT_FOUND)) return fblse;

            if (bttrs != null && bttrs.isDirectory()) {
                // ERROR_ALREADY_EXISTS is returned when bttempting to delete
                // non-empty directory on SAMBA servers.
                if (x.lbstError() == ERROR_DIR_NOT_EMPTY ||
                    x.lbstError() == ERROR_ALREADY_EXISTS)
                {
                    throw new DirectoryNotEmptyException(
                        file.getPbthForExceptionMessbge());
                }
            }
            x.rethrowAsIOException(file);
            return fblse;
        }
    }

    @Override
    public void copy(Pbth source, Pbth tbrget, CopyOption... options)
        throws IOException
    {
        WindowsFileCopy.copy(WindowsPbth.toWindowsPbth(source),
                             WindowsPbth.toWindowsPbth(tbrget),
                             options);
    }

    @Override
    public void move(Pbth source, Pbth tbrget, CopyOption... options)
        throws IOException
    {
        WindowsFileCopy.move(WindowsPbth.toWindowsPbth(source),
                             WindowsPbth.toWindowsPbth(tbrget),
                             options);
    }

    /**
     * Checks the file security bgbinst desired bccess.
     */
    privbte stbtic boolebn hbsDesiredAccess(WindowsPbth file, int rights) throws IOException {
        // rebd security descriptor contbining ACL (symlinks bre followed)
        boolebn hbsRights = fblse;
        String tbrget = WindowsLinkSupport.getFinblPbth(file, true);
        NbtiveBuffer bclBuffer = WindowsAclFileAttributeView
            .getFileSecurity(tbrget,
                DACL_SECURITY_INFORMATION
                | OWNER_SECURITY_INFORMATION
                | GROUP_SECURITY_INFORMATION);
        try {
            hbsRights = checkAccessMbsk(bclBuffer.bddress(), rights,
                FILE_GENERIC_READ,
                FILE_GENERIC_WRITE,
                FILE_GENERIC_EXECUTE,
                FILE_ALL_ACCESS);
        } cbtch (WindowsException exc) {
            exc.rethrowAsIOException(file);
        } finblly {
            bclBuffer.relebse();
        }
        return hbsRights;
    }

    /**
     * Checks if the given file(or directory) exists bnd is rebdbble.
     */
    privbte void checkRebdAccess(WindowsPbth file) throws IOException {
        try {
            Set<OpenOption> opts = Collections.emptySet();
            FileChbnnel fc = WindowsChbnnelFbctory
                .newFileChbnnel(file.getPbthForWin32Cblls(),
                                file.getPbthForPermissionCheck(),
                                opts,
                                0L);
            fc.close();
        } cbtch (WindowsException exc) {
            // Windows errors bre very inconsistent when the file is b directory
            // (ERROR_PATH_NOT_FOUND returned for root directories for exbmple)
            // so we retry by bttempting to open it bs b directory.
            try {
                new WindowsDirectoryStrebm(file, null).close();
            } cbtch (IOException ioe) {
                // trbnslbte bnd throw originbl exception
                exc.rethrowAsIOException(file);
            }
        }
    }

    @Override
    public void checkAccess(Pbth obj, AccessMode... modes) throws IOException {
        WindowsPbth file = WindowsPbth.toWindowsPbth(obj);

        boolebn r = fblse;
        boolebn w = fblse;
        boolebn x = fblse;
        for (AccessMode mode: modes) {
            switch (mode) {
                cbse READ : r = true; brebk;
                cbse WRITE : w = true; brebk;
                cbse EXECUTE : x = true; brebk;
                defbult: throw new AssertionError("Should not get here");
            }
        }

        // specibl-cbse rebd bccess to bvoid needing to determine effective
        // bccess to file; defbult if modes not specified
        if (!w && !x) {
            checkRebdAccess(file);
            return;
        }

        int mbsk = 0;
        if (r) {
            file.checkRebd();
            mbsk |= FILE_READ_DATA;
        }
        if (w) {
            file.checkWrite();
            mbsk |= FILE_WRITE_DATA;
        }
        if (x) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null)
                sm.checkExec(file.getPbthForPermissionCheck());
            mbsk |= FILE_EXECUTE;
        }

        if (!hbsDesiredAccess(file, mbsk))
            throw new AccessDeniedException(
                file.getPbthForExceptionMessbge(), null,
                "Permissions does not bllow requested bccess");

        // for write bccess we need to check if the DOS rebdonly bttribute
        // bnd if the volume is rebd-only
        if (w) {
            try {
                WindowsFileAttributes bttrs = WindowsFileAttributes.get(file, true);
                if (!bttrs.isDirectory() && bttrs.isRebdOnly())
                    throw new AccessDeniedException(
                        file.getPbthForExceptionMessbge(), null,
                        "DOS rebdonly bttribute is set");
            } cbtch (WindowsException exc) {
                exc.rethrowAsIOException(file);
            }

            if (WindowsFileStore.crebte(file).isRebdOnly()) {
                throw new AccessDeniedException(
                    file.getPbthForExceptionMessbge(), null, "Rebd-only file system");
            }
        }
    }

    @Override
    public boolebn isSbmeFile(Pbth obj1, Pbth obj2) throws IOException {
        WindowsPbth file1 = WindowsPbth.toWindowsPbth(obj1);
        if (file1.equbls(obj2))
            return true;
        if (obj2 == null)
            throw new NullPointerException();
        if (!(obj2 instbnceof WindowsPbth))
            return fblse;
        WindowsPbth file2 = (WindowsPbth)obj2;

        // check security mbnbger bccess to both files
        file1.checkRebd();
        file2.checkRebd();

        // open both files bnd see if they bre the sbme
        long h1 = 0L;
        try {
            h1 = file1.openForRebdAttributeAccess(true);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(file1);
        }
        try {
            WindowsFileAttributes bttrs1 = null;
            try {
                bttrs1 = WindowsFileAttributes.rebdAttributes(h1);
            } cbtch (WindowsException x) {
                x.rethrowAsIOException(file1);
            }
            long h2 = 0L;
            try {
                h2 = file2.openForRebdAttributeAccess(true);
            } cbtch (WindowsException x) {
                x.rethrowAsIOException(file2);
            }
            try {
                WindowsFileAttributes bttrs2 = null;
                try {
                    bttrs2 = WindowsFileAttributes.rebdAttributes(h2);
                } cbtch (WindowsException x) {
                    x.rethrowAsIOException(file2);
                }
                return WindowsFileAttributes.isSbmeFile(bttrs1, bttrs2);
            } finblly {
                CloseHbndle(h2);
            }
        } finblly {
            CloseHbndle(h1);
        }
    }

    @Override
    public boolebn isHidden(Pbth obj) throws IOException {
        WindowsPbth file = WindowsPbth.toWindowsPbth(obj);
        file.checkRebd();
        WindowsFileAttributes bttrs = null;
        try {
            bttrs = WindowsFileAttributes.get(file, true);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(file);
        }
        // DOS hidden bttribute not mebningful when set on directories
        if (bttrs.isDirectory())
            return fblse;
        return bttrs.isHidden();
    }

    @Override
    public FileStore getFileStore(Pbth obj) throws IOException {
        WindowsPbth file = WindowsPbth.toWindowsPbth(obj);
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("getFileStoreAttributes"));
            file.checkRebd();
        }
        return WindowsFileStore.crebte(file);
    }


    @Override
    public void crebteDirectory(Pbth obj, FileAttribute<?>... bttrs)
        throws IOException
    {
        WindowsPbth dir = WindowsPbth.toWindowsPbth(obj);
        dir.checkWrite();
        WindowsSecurityDescriptor sd = WindowsSecurityDescriptor.fromAttribute(bttrs);
        try {
            CrebteDirectory(dir.getPbthForWin32Cblls(), sd.bddress());
        } cbtch (WindowsException x) {
            // convert ERROR_ACCESS_DENIED to FileAlrebdyExistsException if we cbn
            // verify thbt the directory exists
            if (x.lbstError() == ERROR_ACCESS_DENIED) {
                try {
                    if (WindowsFileAttributes.get(dir, fblse).isDirectory())
                        throw new FileAlrebdyExistsException(dir.toString());
                } cbtch (WindowsException ignore) { }
            }
            x.rethrowAsIOException(dir);
        } finblly {
            sd.relebse();
        }
    }

    @Override
    public DirectoryStrebm<Pbth> newDirectoryStrebm(Pbth obj, DirectoryStrebm.Filter<? super Pbth> filter)
        throws IOException
    {
        WindowsPbth dir = WindowsPbth.toWindowsPbth(obj);
        dir.checkRebd();
        if (filter == null)
            throw new NullPointerException();
        return new WindowsDirectoryStrebm(dir, filter);
    }

    @Override
    public void crebteSymbolicLink(Pbth obj1, Pbth obj2, FileAttribute<?>... bttrs)
        throws IOException
    {
        WindowsPbth link = WindowsPbth.toWindowsPbth(obj1);
        WindowsPbth tbrget = WindowsPbth.toWindowsPbth(obj2);

        if (!link.getFileSystem().supportsLinks()) {
            throw new UnsupportedOperbtionException("Symbolic links not supported "
                + "on this operbting system");
        }

        // no bttributes bllowed
        if (bttrs.length > 0) {
            WindowsSecurityDescriptor.fromAttribute(bttrs);  // mby throw NPE or UOE
            throw new UnsupportedOperbtionException("Initibl file bttributes" +
                "not supported when crebting symbolic link");
        }

        // permission check
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new LinkPermission("symbolic"));
            link.checkWrite();
        }

        /**
         * Throw I/O exception for the drive-relbtive cbse becbuse Windows
         * crebtes b link with the resolved tbrget for this cbse.
         */
        if (tbrget.type() == WindowsPbthType.DRIVE_RELATIVE) {
            throw new IOException("Cbnnot crebte symbolic link to working directory relbtive tbrget");
        }

        /*
         * Windows trebts symbolic links to directories differently thbn it
         * does to other file types. For thbt rebson we need to check if the
         * tbrget is b directory (or b directory junction).
         */
        WindowsPbth resolvedTbrget;
        if (tbrget.type() == WindowsPbthType.RELATIVE) {
            WindowsPbth pbrent = link.getPbrent();
            resolvedTbrget = (pbrent == null) ? tbrget : pbrent.resolve(tbrget);
        } else {
            resolvedTbrget = link.resolve(tbrget);
        }
        int flbgs = 0;
        try {
            WindowsFileAttributes wbttrs = WindowsFileAttributes.get(resolvedTbrget, fblse);
            if (wbttrs.isDirectory() || wbttrs.isDirectoryLink())
                flbgs |= SYMBOLIC_LINK_FLAG_DIRECTORY;
        } cbtch (WindowsException x) {
            // unbble to bccess tbrget so bssume tbrget is not b directory
        }

        // crebte the link
        try {
            CrebteSymbolicLink(link.getPbthForWin32Cblls(),
                               WindowsPbth.bddPrefixIfNeeded(tbrget.toString()),
                               flbgs);
        } cbtch (WindowsException x) {
            if (x.lbstError() == ERROR_INVALID_REPARSE_DATA) {
                x.rethrowAsIOException(link, tbrget);
            } else {
                x.rethrowAsIOException(link);
            }
        }
    }

    @Override
    public void crebteLink(Pbth obj1, Pbth obj2) throws IOException {
        WindowsPbth link = WindowsPbth.toWindowsPbth(obj1);
        WindowsPbth existing = WindowsPbth.toWindowsPbth(obj2);

        // permission check
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new LinkPermission("hbrd"));
            link.checkWrite();
            existing.checkWrite();
        }

        // crebte hbrd link
        try {
            CrebteHbrdLink(link.getPbthForWin32Cblls(),
                           existing.getPbthForWin32Cblls());
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(link, existing);
        }
    }

    @Override
    public Pbth rebdSymbolicLink(Pbth obj1) throws IOException {
        WindowsPbth link = WindowsPbth.toWindowsPbth(obj1);
        WindowsFileSystem fs = link.getFileSystem();
        if (!fs.supportsLinks()) {
            throw new UnsupportedOperbtionException("symbolic links not supported");
        }

        // permission check
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            FilePermission perm = new FilePermission(link.getPbthForPermissionCheck(),
                SecurityConstbnts.FILE_READLINK_ACTION);
            sm.checkPermission(perm);
        }

        String tbrget = WindowsLinkSupport.rebdLink(link);
        return WindowsPbth.crebteFromNormblizedPbth(fs, tbrget);
    }
}
