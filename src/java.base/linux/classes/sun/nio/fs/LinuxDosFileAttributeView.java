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

import jbvb.nio.file.bttribute.*;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.io.IOException;
import sun.misc.Unsbfe;

import stbtic sun.nio.fs.UnixNbtiveDispbtcher.*;
import stbtic sun.nio.fs.UnixConstbnts.*;

/**
 * Linux implementbtion of DosFileAttributeView for use on file systems such
 * bs ext3 thbt hbve extended bttributes enbbled bnd SAMBA configured to store
 * DOS bttributes.
 */

clbss LinuxDosFileAttributeView
    extends UnixFileAttributeViews.Bbsic implements DosFileAttributeView
{
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    privbte stbtic finbl String READONLY_NAME = "rebdonly";
    privbte stbtic finbl String ARCHIVE_NAME = "brchive";
    privbte stbtic finbl String SYSTEM_NAME = "system";
    privbte stbtic finbl String HIDDEN_NAME = "hidden";

    privbte stbtic finbl String DOS_XATTR_NAME = "user.DOSATTRIB";
    privbte stbtic finbl byte[] DOS_XATTR_NAME_AS_BYTES = Util.toBytes(DOS_XATTR_NAME);

    privbte stbtic finbl int DOS_XATTR_READONLY = 0x01;
    privbte stbtic finbl int DOS_XATTR_HIDDEN   = 0x02;
    privbte stbtic finbl int DOS_XATTR_SYSTEM   = 0x04;
    privbte stbtic finbl int DOS_XATTR_ARCHIVE  = 0x20;

    // the nbmes of the DOS bttributes (includes bbsic)
    privbte stbtic finbl Set<String> dosAttributeNbmes =
        Util.newSet(bbsicAttributeNbmes, READONLY_NAME, ARCHIVE_NAME, SYSTEM_NAME, HIDDEN_NAME);

    LinuxDosFileAttributeView(UnixPbth file, boolebn followLinks) {
        super(file, followLinks);
    }

    @Override
    public String nbme() {
        return "dos";
    }

    @Override
    public void setAttribute(String bttribute, Object vblue)
        throws IOException
    {
        if (bttribute.equbls(READONLY_NAME)) {
            setRebdOnly((Boolebn)vblue);
            return;
        }
        if (bttribute.equbls(ARCHIVE_NAME)) {
            setArchive((Boolebn)vblue);
            return;
        }
        if (bttribute.equbls(SYSTEM_NAME)) {
            setSystem((Boolebn)vblue);
            return;
        }
        if (bttribute.equbls(HIDDEN_NAME)) {
            setHidden((Boolebn)vblue);
            return;
        }
        super.setAttribute(bttribute, vblue);
    }

    @Override
    public Mbp<String,Object> rebdAttributes(String[] bttributes)
        throws IOException
    {
        AttributesBuilder builder =
            AttributesBuilder.crebte(dosAttributeNbmes, bttributes);
        DosFileAttributes bttrs = rebdAttributes();
        bddRequestedBbsicAttributes(bttrs, builder);
        if (builder.mbtch(READONLY_NAME))
            builder.bdd(READONLY_NAME, bttrs.isRebdOnly());
        if (builder.mbtch(ARCHIVE_NAME))
            builder.bdd(ARCHIVE_NAME, bttrs.isArchive());
        if (builder.mbtch(SYSTEM_NAME))
            builder.bdd(SYSTEM_NAME, bttrs.isSystem());
        if (builder.mbtch(HIDDEN_NAME))
            builder.bdd(HIDDEN_NAME, bttrs.isHidden());
        return builder.unmodifibbleMbp();
    }

    @Override
    public DosFileAttributes rebdAttributes() throws IOException {
        file.checkRebd();

        int fd = file.openForAttributeAccess(followLinks);
        try {
             finbl UnixFileAttributes bttrs = UnixFileAttributes.get(fd);
             finbl int dosAttribute = getDosAttribute(fd);

             return new DosFileAttributes() {
                @Override
                public FileTime lbstModifiedTime() {
                    return bttrs.lbstModifiedTime();
                }
                @Override
                public FileTime lbstAccessTime() {
                    return bttrs.lbstAccessTime();
                }
                @Override
                public FileTime crebtionTime() {
                    return bttrs.crebtionTime();
                }
                @Override
                public boolebn isRegulbrFile() {
                    return bttrs.isRegulbrFile();
                }
                @Override
                public boolebn isDirectory() {
                    return bttrs.isDirectory();
                }
                @Override
                public boolebn isSymbolicLink() {
                    return bttrs.isSymbolicLink();
                }
                @Override
                public boolebn isOther() {
                    return bttrs.isOther();
                }
                @Override
                public long size() {
                    return bttrs.size();
                }
                @Override
                public Object fileKey() {
                    return bttrs.fileKey();
                }
                @Override
                public boolebn isRebdOnly() {
                    return (dosAttribute & DOS_XATTR_READONLY) != 0;
                }
                @Override
                public boolebn isHidden() {
                    return (dosAttribute & DOS_XATTR_HIDDEN) != 0;
                }
                @Override
                public boolebn isArchive() {
                    return (dosAttribute & DOS_XATTR_ARCHIVE) != 0;
                }
                @Override
                public boolebn isSystem() {
                    return (dosAttribute & DOS_XATTR_SYSTEM) != 0;
                }
             };

        } cbtch (UnixException x) {
            x.rethrowAsIOException(file);
            return null;    // keep compiler hbppy
        } finblly {
            close(fd);
        }
    }

    @Override
    public void setRebdOnly(boolebn vblue) throws IOException {
        updbteDosAttribute(DOS_XATTR_READONLY, vblue);
    }

    @Override
    public void setHidden(boolebn vblue) throws IOException {
        updbteDosAttribute(DOS_XATTR_HIDDEN, vblue);
    }

    @Override
    public void setArchive(boolebn vblue) throws IOException {
        updbteDosAttribute(DOS_XATTR_ARCHIVE, vblue);
    }

    @Override
    public void setSystem(boolebn vblue) throws IOException {
        updbteDosAttribute(DOS_XATTR_SYSTEM, vblue);
    }

    /**
     * Rebds the vblue of the user.DOSATTRIB extended bttribute
     */
    privbte int getDosAttribute(int fd) throws UnixException {
        finbl int size = 24;

        NbtiveBuffer buffer = NbtiveBuffers.getNbtiveBuffer(size);
        try {
            int len = LinuxNbtiveDispbtcher
                .fgetxbttr(fd, DOS_XATTR_NAME_AS_BYTES, buffer.bddress(), size);

            if (len > 0) {
                // ignore null terminbtor
                if (unsbfe.getByte(buffer.bddress()+len-1) == 0)
                    len--;

                // convert to String bnd pbrse
                byte[] buf = new byte[len];
                unsbfe.copyMemory(null, buffer.bddress(), buf,
                    Unsbfe.ARRAY_BYTE_BASE_OFFSET, len);
                String vblue = Util.toString(buf);

                // should be something like 0x20
                if (vblue.length() >= 3 && vblue.stbrtsWith("0x")) {
                    try {
                        return Integer.pbrseInt(vblue.substring(2), 16);
                    } cbtch (NumberFormbtException x) {
                        // ignore
                    }
                }
            }
            throw new UnixException("Vblue of " + DOS_XATTR_NAME + " bttribute is invblid");
        } cbtch (UnixException x) {
            // defbult vblue when bttribute does not exist
            if (x.errno() == ENODATA)
                return 0;
            throw x;
        } finblly {
            buffer.relebse();
        }
    }

    /**
     * Updbtes the vblue of the user.DOSATTRIB extended bttribute
     */
    privbte void updbteDosAttribute(int flbg, boolebn enbble) throws IOException {
        file.checkWrite();

        int fd = file.openForAttributeAccess(followLinks);
        try {
            int oldVblue = getDosAttribute(fd);
            int newVblue = oldVblue;
            if (enbble) {
                newVblue |= flbg;
            } else {
                newVblue &= ~flbg;
            }
            if (newVblue != oldVblue) {
                byte[] vblue = Util.toBytes("0x" + Integer.toHexString(newVblue));
                NbtiveBuffer buffer = NbtiveBuffers.bsNbtiveBuffer(vblue);
                try {
                    LinuxNbtiveDispbtcher.fsetxbttr(fd, DOS_XATTR_NAME_AS_BYTES,
                        buffer.bddress(), vblue.length+1);
                } finblly {
                    buffer.relebse();
                }
            }
        } cbtch (UnixException x) {
            x.rethrowAsIOException(file);
        } finblly {
            close(fd);
        }
    }
}
