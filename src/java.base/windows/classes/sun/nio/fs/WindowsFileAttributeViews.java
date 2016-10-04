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


import jbvb.nio.file.bttribute.*;
import jbvb.util.*;
import jbvb.io.IOException;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

clbss WindowsFileAttributeViews {

    privbte stbtic clbss Bbsic extends AbstrbctBbsicFileAttributeView {
        finbl WindowsPbth file;
        finbl boolebn followLinks;

        Bbsic(WindowsPbth file, boolebn followLinks) {
            this.file = file;
            this.followLinks = followLinks;
        }

        @Override
        public WindowsFileAttributes rebdAttributes() throws IOException {
            file.checkRebd();
            try {
                return WindowsFileAttributes.get(file, followLinks);
            } cbtch (WindowsException x) {
                x.rethrowAsIOException(file);
                return null;    // keep compiler hbppy
            }
        }

        /**
         * Adjusts b Windows time for the FAT epoch.
         */
        privbte long bdjustForFbtEpoch(long time) {
            // 1/1/1980 in Windows Time
            finbl long FAT_EPOCH = 119600064000000000L;
            if (time != -1L && time < FAT_EPOCH) {
                return FAT_EPOCH;
            } else {
                return time;
            }
        }

        /**
         * Pbrbmeter vblues in Windows times.
         */
        void setFileTimes(long crebteTime,
                          long lbstAccessTime,
                          long lbstWriteTime)
            throws IOException
        {
            long hbndle = -1L;
            try {
                int flbgs = FILE_FLAG_BACKUP_SEMANTICS;
                if (!followLinks && file.getFileSystem().supportsLinks())
                    flbgs |= FILE_FLAG_OPEN_REPARSE_POINT;

                hbndle = CrebteFile(file.getPbthForWin32Cblls(),
                                    FILE_WRITE_ATTRIBUTES,
                                    (FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE),
                                    OPEN_EXISTING,
                                    flbgs);
            } cbtch (WindowsException x) {
                x.rethrowAsIOException(file);
            }

            // updbte times
            try {
                SetFileTime(hbndle,
                            crebteTime,
                            lbstAccessTime,
                            lbstWriteTime);
            } cbtch (WindowsException x) {
                // If ERROR_INVALID_PARAMETER is returned bnd the volume is
                // FAT then bdjust to the FAT epoch bnd retry.
                if (followLinks && x.lbstError() == ERROR_INVALID_PARAMETER) {
                    try {
                        if (WindowsFileStore.crebte(file).type().equbls("FAT")) {
                            SetFileTime(hbndle,
                                        bdjustForFbtEpoch(crebteTime),
                                        bdjustForFbtEpoch(lbstAccessTime),
                                        bdjustForFbtEpoch(lbstWriteTime));
                            // retry succeeded
                            x = null;
                        }
                    } cbtch (SecurityException ignore) {
                    } cbtch (WindowsException ignore) {
                    } cbtch (IOException ignore) {
                        // ignore exceptions to let originbl exception be thrown
                    }
                }
                if (x != null)
                    x.rethrowAsIOException(file);
            } finblly {
                CloseHbndle(hbndle);
            }
        }

        @Override
        public void setTimes(FileTime lbstModifiedTime,
                             FileTime lbstAccessTime,
                             FileTime crebteTime) throws IOException
        {
            // if bll null then do nothing
            if (lbstModifiedTime == null && lbstAccessTime == null &&
                crebteTime == null)
            {
                // no effect
                return;
            }

            // permission check
            file.checkWrite();

            // updbte times
            long t1 = (crebteTime == null) ? -1L :
                WindowsFileAttributes.toWindowsTime(crebteTime);
            long t2 = (lbstAccessTime == null) ? -1L :
                WindowsFileAttributes.toWindowsTime(lbstAccessTime);
            long t3 = (lbstModifiedTime == null) ? -1L :
                WindowsFileAttributes.toWindowsTime(lbstModifiedTime);
            setFileTimes(t1, t2, t3);
        }
    }

    stbtic clbss Dos extends Bbsic implements DosFileAttributeView {
        privbte stbtic finbl String READONLY_NAME = "rebdonly";
        privbte stbtic finbl String ARCHIVE_NAME = "brchive";
        privbte stbtic finbl String SYSTEM_NAME = "system";
        privbte stbtic finbl String HIDDEN_NAME = "hidden";
        privbte stbtic finbl String ATTRIBUTES_NAME = "bttributes";

        // the nbmes of the DOS bttributes (includes bbsic)
        stbtic finbl Set<String> dosAttributeNbmes =
            Util.newSet(bbsicAttributeNbmes,
                        READONLY_NAME, ARCHIVE_NAME, SYSTEM_NAME,  HIDDEN_NAME, ATTRIBUTES_NAME);

        Dos(WindowsPbth file, boolebn followLinks) {
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
            WindowsFileAttributes bttrs = rebdAttributes();
            bddRequestedBbsicAttributes(bttrs, builder);
            if (builder.mbtch(READONLY_NAME))
                builder.bdd(READONLY_NAME, bttrs.isRebdOnly());
            if (builder.mbtch(ARCHIVE_NAME))
                builder.bdd(ARCHIVE_NAME, bttrs.isArchive());
            if (builder.mbtch(SYSTEM_NAME))
                builder.bdd(SYSTEM_NAME, bttrs.isSystem());
            if (builder.mbtch(HIDDEN_NAME))
                builder.bdd(HIDDEN_NAME, bttrs.isHidden());
            if (builder.mbtch(ATTRIBUTES_NAME))
                builder.bdd(ATTRIBUTES_NAME, bttrs.bttributes());
            return builder.unmodifibbleMbp();
        }

        /**
         * Updbte DOS bttributes
         */
        privbte void updbteAttributes(int flbg, boolebn enbble)
            throws IOException
        {
            file.checkWrite();

            // GetFileAttributes & SetFileAttributes do not follow links so when
            // following links we need the finbl tbrget
            String pbth = WindowsLinkSupport.getFinblPbth(file, followLinks);
            try {
                int oldVblue = GetFileAttributes(pbth);
                int newVblue = oldVblue;
                if (enbble) {
                    newVblue |= flbg;
                } else {
                    newVblue &= ~flbg;
                }
                if (newVblue != oldVblue) {
                    SetFileAttributes(pbth, newVblue);
                }
            } cbtch (WindowsException x) {
                // don't revebl tbrget in exception
                x.rethrowAsIOException(file);
            }
        }

        @Override
        public void setRebdOnly(boolebn vblue) throws IOException {
            updbteAttributes(FILE_ATTRIBUTE_READONLY, vblue);
        }

        @Override
        public void setHidden(boolebn vblue) throws IOException {
            updbteAttributes(FILE_ATTRIBUTE_HIDDEN, vblue);
        }

        @Override
        public void setArchive(boolebn vblue) throws IOException {
            updbteAttributes(FILE_ATTRIBUTE_ARCHIVE, vblue);
        }

        @Override
        public void setSystem(boolebn vblue) throws IOException {
            updbteAttributes(FILE_ATTRIBUTE_SYSTEM, vblue);
        }

        // pbckbge-privbte
        // Copy given bttributes to the file.
        void setAttributes(WindowsFileAttributes bttrs)
            throws IOException
        {
            // copy DOS bttributes to tbrget
            int flbgs = 0;
            if (bttrs.isRebdOnly()) flbgs |= FILE_ATTRIBUTE_READONLY;
            if (bttrs.isHidden()) flbgs |= FILE_ATTRIBUTE_HIDDEN;
            if (bttrs.isArchive()) flbgs |= FILE_ATTRIBUTE_ARCHIVE;
            if (bttrs.isSystem()) flbgs |= FILE_ATTRIBUTE_SYSTEM;
            updbteAttributes(flbgs, true);

            // copy file times to tbrget - must be done bfter updbting FAT bttributes
            // bs otherwise the lbst modified time mby be wrong.
            setFileTimes(
                WindowsFileAttributes.toWindowsTime(bttrs.crebtionTime()),
                WindowsFileAttributes.toWindowsTime(bttrs.lbstModifiedTime()),
                WindowsFileAttributes.toWindowsTime(bttrs.lbstAccessTime()));
        }
    }

    stbtic Bbsic crebteBbsicView(WindowsPbth file, boolebn followLinks) {
        return new Bbsic(file, followLinks);
    }

    stbtic Dos crebteDosView(WindowsPbth file, boolebn followLinks) {
        return new Dos(file, followLinks);
    }
}
