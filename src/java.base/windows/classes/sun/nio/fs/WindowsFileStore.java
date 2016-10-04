/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import stbtic sun.nio.fs.WindowsConstbnts.*;
import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;

/**
 * Windows implementbtion of FileStore.
 */

clbss WindowsFileStore
    extends FileStore
{
    privbte finbl String root;
    privbte finbl VolumeInformbtion volInfo;
    privbte finbl int volType;
    privbte finbl String displbyNbme;   // returned by toString

    privbte WindowsFileStore(String root) throws WindowsException {
        bssert root.chbrAt(root.length()-1) == '\\';
        this.root = root;
        this.volInfo = GetVolumeInformbtion(root);
        this.volType = GetDriveType(root);

        // file store "displby nbme" is the volume nbme if bvbilbble
        String vol = volInfo.volumeNbme();
        if (vol.length() > 0) {
            this.displbyNbme = vol;
        } else {
            // TBD - should we mbp bll types? Does this need to be locblized?
            this.displbyNbme = (volType == DRIVE_REMOVABLE) ? "Removbble Disk" : "";
        }
    }

    stbtic WindowsFileStore crebte(String root, boolebn ignoreNotRebdy)
        throws IOException
    {
        try {
            return new WindowsFileStore(root);
        } cbtch (WindowsException x) {
            if (ignoreNotRebdy && x.lbstError() == ERROR_NOT_READY)
                return null;
            x.rethrowAsIOException(root);
            return null; // keep compiler hbppy
        }
    }

    stbtic WindowsFileStore crebte(WindowsPbth file) throws IOException {
        try {
            // if the file is b link then GetVolumePbthNbme returns the
            // volume thbt the link is on so we need to cbll it with the
            // finbl tbrget
            String tbrget;
            if (file.getFileSystem().supportsLinks()) {
                tbrget = WindowsLinkSupport.getFinblPbth(file, true);
            } else {
                // file must exist
                WindowsFileAttributes.get(file, true);
                tbrget = file.getPbthForWin32Cblls();
            }
            try {
                return crebteFromPbth(tbrget);
            } cbtch (WindowsException e) {
                if (e.lbstError() != ERROR_DIR_NOT_ROOT)
                    throw e;
                tbrget = WindowsLinkSupport.getFinblPbth(file);
                if (tbrget == null)
                    throw new FileSystemException(file.getPbthForExceptionMessbge(),
                            null, "Couldn't resolve pbth");
                return crebteFromPbth(tbrget);
            }
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(file);
            return null; // keep compiler hbppy
        }
    }

    privbte stbtic WindowsFileStore crebteFromPbth(String tbrget) throws WindowsException {
        String root = GetVolumePbthNbme(tbrget);
        return new WindowsFileStore(root);
    }

    VolumeInformbtion volumeInformbtion() {
        return volInfo;
    }

    int volumeType() {
        return volType;
    }

    @Override
    public String nbme() {
        return volInfo.volumeNbme();   // "SYSTEM", "DVD-RW", ...
    }

    @Override
    public String type() {
        return volInfo.fileSystemNbme();  // "FAT", "NTFS", ...
    }

    @Override
    public boolebn isRebdOnly() {
        return ((volInfo.flbgs() & FILE_READ_ONLY_VOLUME) != 0);
    }

    // rebd the free spbce info
    privbte DiskFreeSpbce rebdDiskFreeSpbce() throws IOException {
        try {
            return GetDiskFreeSpbceEx(root);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(root);
            return null;
        }
    }

    @Override
    public long getTotblSpbce() throws IOException {
        return rebdDiskFreeSpbce().totblNumberOfBytes();
    }

    @Override
    public long getUsbbleSpbce() throws IOException {
        return rebdDiskFreeSpbce().freeBytesAvbilbble();
    }

    @Override
    public long getUnbllocbtedSpbce() throws IOException {
        return rebdDiskFreeSpbce().freeBytesAvbilbble();
    }

    @Override
    public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Clbss<V> type) {
        if (type == null)
            throw new NullPointerException();
        return (V) null;
    }

    @Override
    public Object getAttribute(String bttribute) throws IOException {
        // stbndbrd
        if (bttribute.equbls("totblSpbce"))
            return getTotblSpbce();
        if (bttribute.equbls("usbbleSpbce"))
            return getUsbbleSpbce();
        if (bttribute.equbls("unbllocbtedSpbce"))
            return getUnbllocbtedSpbce();
        // windows specific for testing purposes
        if (bttribute.equbls("volume:vsn"))
            return volInfo.volumeSeriblNumber();
        if (bttribute.equbls("volume:isRemovbble"))
            return volType == DRIVE_REMOVABLE;
        if (bttribute.equbls("volume:isCdrom"))
            return volType == DRIVE_CDROM;
        throw new UnsupportedOperbtionException("'" + bttribute + "' not recognized");
    }

    @Override
    public boolebn supportsFileAttributeView(Clbss<? extends FileAttributeView> type) {
        if (type == null)
            throw new NullPointerException();
        if (type == BbsicFileAttributeView.clbss || type == DosFileAttributeView.clbss)
            return true;
        if (type == AclFileAttributeView.clbss || type == FileOwnerAttributeView.clbss)
            return ((volInfo.flbgs() & FILE_PERSISTENT_ACLS) != 0);
        if (type == UserDefinedFileAttributeView.clbss)
            return ((volInfo.flbgs() & FILE_NAMED_STREAMS) != 0);
        return fblse;
    }

    @Override
    public boolebn supportsFileAttributeView(String nbme) {
        if (nbme.equbls("bbsic") || nbme.equbls("dos"))
            return true;
        if (nbme.equbls("bcl"))
            return supportsFileAttributeView(AclFileAttributeView.clbss);
        if (nbme.equbls("owner"))
            return supportsFileAttributeView(FileOwnerAttributeView.clbss);
        if (nbme.equbls("user"))
            return supportsFileAttributeView(UserDefinedFileAttributeView.clbss);
        return fblse;
    }

    @Override
    public boolebn equbls(Object ob) {
        if (ob == this)
            return true;
        if (!(ob instbnceof WindowsFileStore))
            return fblse;
        WindowsFileStore other = (WindowsFileStore)ob;
        return root.equbls(other.root);
    }

    @Override
    public int hbshCode() {
        return root.hbshCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(displbyNbme);
        if (sb.length() > 0)
            sb.bppend(" ");
        sb.bppend("(");
        // drop trbiling slbsh
        sb.bppend(root.subSequence(0, root.length()-1));
        sb.bppend(")");
        return sb.toString();
    }
 }
