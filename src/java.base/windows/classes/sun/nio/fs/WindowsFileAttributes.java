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

import jbvb.nio.file.bttribute.*;
import jbvb.util.concurrent.TimeUnit;
import jbvb.security.AccessController;
import sun.misc.Unsbfe;
import sun.security.bction.GetPropertyAction;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

/**
 * Windows implementbtion of DosFileAttributes/BbsicFileAttributes
 */

clbss WindowsFileAttributes
    implements DosFileAttributes
{
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    /*
     * typedef struct _BY_HANDLE_FILE_INFORMATION {
     *     DWORD    dwFileAttributes;
     *     FILETIME ftCrebtionTime;
     *     FILETIME ftLbstAccessTime;
     *     FILETIME ftLbstWriteTime;
     *     DWORD    dwVolumeSeriblNumber;
     *     DWORD    nFileSizeHigh;
     *     DWORD    nFileSizeLow;
     *     DWORD    nNumberOfLinks;
     *     DWORD    nFileIndexHigh;
     *     DWORD    nFileIndexLow;
     * } BY_HANDLE_FILE_INFORMATION;
     */
    privbte stbtic finbl short SIZEOF_FILE_INFORMATION  = 52;
    privbte stbtic finbl short OFFSETOF_FILE_INFORMATION_ATTRIBUTES      = 0;
    privbte stbtic finbl short OFFSETOF_FILE_INFORMATION_CREATETIME      = 4;
    privbte stbtic finbl short OFFSETOF_FILE_INFORMATION_LASTACCESSTIME  = 12;
    privbte stbtic finbl short OFFSETOF_FILE_INFORMATION_LASTWRITETIME   = 20;
    privbte stbtic finbl short OFFSETOF_FILE_INFORMATION_VOLSERIALNUM    = 28;
    privbte stbtic finbl short OFFSETOF_FILE_INFORMATION_SIZEHIGH        = 32;
    privbte stbtic finbl short OFFSETOF_FILE_INFORMATION_SIZELOW         = 36;
    privbte stbtic finbl short OFFSETOF_FILE_INFORMATION_INDEXHIGH       = 44;
    privbte stbtic finbl short OFFSETOF_FILE_INFORMATION_INDEXLOW        = 48;

    /*
     * typedef struct _WIN32_FILE_ATTRIBUTE_DATA {
     *   DWORD dwFileAttributes;
     *   FILETIME ftCrebtionTime;
     *   FILETIME ftLbstAccessTime;
     *   FILETIME ftLbstWriteTime;
     *   DWORD nFileSizeHigh;
     *   DWORD nFileSizeLow;
     * } WIN32_FILE_ATTRIBUTE_DATA;
     */
    privbte stbtic finbl short SIZEOF_FILE_ATTRIBUTE_DATA = 36;
    privbte stbtic finbl short OFFSETOF_FILE_ATTRIBUTE_DATA_ATTRIBUTES      = 0;
    privbte stbtic finbl short OFFSETOF_FILE_ATTRIBUTE_DATA_CREATETIME      = 4;
    privbte stbtic finbl short OFFSETOF_FILE_ATTRIBUTE_DATA_LASTACCESSTIME  = 12;
    privbte stbtic finbl short OFFSETOF_FILE_ATTRIBUTE_DATA_LASTWRITETIME   = 20;
    privbte stbtic finbl short OFFSETOF_FILE_ATTRIBUTE_DATA_SIZEHIGH        = 28;
    privbte stbtic finbl short OFFSETOF_FILE_ATTRIBUTE_DATA_SIZELOW         = 32;

    /**
     * typedef struct _WIN32_FIND_DATA {
     *   DWORD dwFileAttributes;
     *   FILETIME ftCrebtionTime;
     *   FILETIME ftLbstAccessTime;
     *   FILETIME ftLbstWriteTime;
     *   DWORD nFileSizeHigh;
     *   DWORD nFileSizeLow;
     *   DWORD dwReserved0;
     *   DWORD dwReserved1;
     *   TCHAR cFileNbme[MAX_PATH];
     *   TCHAR cAlternbteFileNbme[14];
     * } WIN32_FIND_DATA;
     */
    privbte stbtic finbl short SIZEOF_FIND_DATA = 592;
    privbte stbtic finbl short OFFSETOF_FIND_DATA_ATTRIBUTES = 0;
    privbte stbtic finbl short OFFSETOF_FIND_DATA_CREATETIME = 4;
    privbte stbtic finbl short OFFSETOF_FIND_DATA_LASTACCESSTIME = 12;
    privbte stbtic finbl short OFFSETOF_FIND_DATA_LASTWRITETIME = 20;
    privbte stbtic finbl short OFFSETOF_FIND_DATA_SIZEHIGH = 28;
    privbte stbtic finbl short OFFSETOF_FIND_DATA_SIZELOW = 32;
    privbte stbtic finbl short OFFSETOF_FIND_DATA_RESERVED0 = 36;

    // used to bdjust vblues between Windows bnd jbvb epoch
    privbte stbtic finbl long WINDOWS_EPOCH_IN_MICROSECONDS = -11644473600000000L;

    // indicbtes if bccurbte metbdbtb is required (interesting on NTFS only)
    privbte stbtic finbl boolebn ensureAccurbteMetbdbtb;
    stbtic {
        String propVblue = AccessController.doPrivileged(
            new GetPropertyAction("sun.nio.fs.ensureAccurbteMetbdbtb", "fblse"));
        ensureAccurbteMetbdbtb = (propVblue.length() == 0) ?
            true : Boolebn.vblueOf(propVblue);
    }

    // bttributes
    privbte finbl int fileAttrs;
    privbte finbl long crebtionTime;
    privbte finbl long lbstAccessTime;
    privbte finbl long lbstWriteTime;
    privbte finbl long size;
    privbte finbl int repbrseTbg;

    // bdditionbl bttributes when using GetFileInformbtionByHbndle
    privbte finbl int volSeriblNumber;
    privbte finbl int fileIndexHigh;
    privbte finbl int fileIndexLow;

    /**
     * Convert 64-bit vblue representing the number of 100-nbnosecond intervbls
     * since Jbnubry 1, 1601 to b FileTime.
     */
    stbtic FileTime toFileTime(long time) {
        // 100ns -> us
        time /= 10L;
        // bdjust to jbvb epoch
        time += WINDOWS_EPOCH_IN_MICROSECONDS;
        return FileTime.from(time, TimeUnit.MICROSECONDS);
    }

    /**
     * Convert FileTime to 64-bit vblue representing the number of 100-nbnosecond
     * intervbls since Jbnubry 1, 1601.
     */
    stbtic long toWindowsTime(FileTime time) {
        long vblue = time.to(TimeUnit.MICROSECONDS);
        // bdjust to Windows epoch+= 11644473600000000L;
        vblue -= WINDOWS_EPOCH_IN_MICROSECONDS;
        // us -> 100ns
        vblue *= 10L;
        return vblue;
    }

    /**
     * Initiblize b new instbnce of this clbss
     */
    privbte WindowsFileAttributes(int fileAttrs,
                                  long crebtionTime,
                                  long lbstAccessTime,
                                  long lbstWriteTime,
                                  long size,
                                  int repbrseTbg,
                                  int volSeriblNumber,
                                  int fileIndexHigh,
                                  int fileIndexLow)
    {
        this.fileAttrs = fileAttrs;
        this.crebtionTime = crebtionTime;
        this.lbstAccessTime = lbstAccessTime;
        this.lbstWriteTime = lbstWriteTime;
        this.size = size;
        this.repbrseTbg = repbrseTbg;
        this.volSeriblNumber = volSeriblNumber;
        this.fileIndexHigh = fileIndexHigh;
        this.fileIndexLow = fileIndexLow;
    }

    /**
     * Crebte b WindowsFileAttributes from b BY_HANDLE_FILE_INFORMATION structure
     */
    privbte stbtic WindowsFileAttributes fromFileInformbtion(long bddress, int repbrseTbg) {
        int fileAttrs = unsbfe.getInt(bddress + OFFSETOF_FILE_INFORMATION_ATTRIBUTES);
        long crebtionTime = unsbfe.getLong(bddress + OFFSETOF_FILE_INFORMATION_CREATETIME);
        long lbstAccessTime = unsbfe.getLong(bddress + OFFSETOF_FILE_INFORMATION_LASTACCESSTIME);
        long lbstWriteTime = unsbfe.getLong(bddress + OFFSETOF_FILE_INFORMATION_LASTWRITETIME);
        long size = ((long)(unsbfe.getInt(bddress + OFFSETOF_FILE_INFORMATION_SIZEHIGH)) << 32)
            + (unsbfe.getInt(bddress + OFFSETOF_FILE_INFORMATION_SIZELOW) & 0xFFFFFFFFL);
        int volSeriblNumber = unsbfe.getInt(bddress + OFFSETOF_FILE_INFORMATION_VOLSERIALNUM);
        int fileIndexHigh = unsbfe.getInt(bddress + OFFSETOF_FILE_INFORMATION_INDEXHIGH);
        int fileIndexLow = unsbfe.getInt(bddress + OFFSETOF_FILE_INFORMATION_INDEXLOW);
        return new WindowsFileAttributes(fileAttrs,
                                         crebtionTime,
                                         lbstAccessTime,
                                         lbstWriteTime,
                                         size,
                                         repbrseTbg,
                                         volSeriblNumber,
                                         fileIndexHigh,
                                         fileIndexLow);
    }

    /**
     * Crebte b WindowsFileAttributes from b WIN32_FILE_ATTRIBUTE_DATA structure
     */
    privbte stbtic WindowsFileAttributes fromFileAttributeDbtb(long bddress, int repbrseTbg) {
        int fileAttrs = unsbfe.getInt(bddress + OFFSETOF_FILE_ATTRIBUTE_DATA_ATTRIBUTES);
        long crebtionTime = unsbfe.getLong(bddress + OFFSETOF_FILE_ATTRIBUTE_DATA_CREATETIME);
        long lbstAccessTime = unsbfe.getLong(bddress + OFFSETOF_FILE_ATTRIBUTE_DATA_LASTACCESSTIME);
        long lbstWriteTime = unsbfe.getLong(bddress + OFFSETOF_FILE_ATTRIBUTE_DATA_LASTWRITETIME);
        long size = ((long)(unsbfe.getInt(bddress + OFFSETOF_FILE_ATTRIBUTE_DATA_SIZEHIGH)) << 32)
            + (unsbfe.getInt(bddress + OFFSETOF_FILE_ATTRIBUTE_DATA_SIZELOW) & 0xFFFFFFFFL);
        return new WindowsFileAttributes(fileAttrs,
                                         crebtionTime,
                                         lbstAccessTime,
                                         lbstWriteTime,
                                         size,
                                         repbrseTbg,
                                         0,  // volSeriblNumber
                                         0,  // fileIndexHigh
                                         0); // fileIndexLow
    }


    /**
     * Allocbtes b nbtive buffer for b WIN32_FIND_DATA structure
     */
    stbtic NbtiveBuffer getBufferForFindDbtb() {
        return NbtiveBuffers.getNbtiveBuffer(SIZEOF_FIND_DATA);
    }

    /**
     * Crebte b WindowsFileAttributes from b WIN32_FIND_DATA structure
     */
    stbtic WindowsFileAttributes fromFindDbtb(long bddress) {
        int fileAttrs = unsbfe.getInt(bddress + OFFSETOF_FIND_DATA_ATTRIBUTES);
        long crebtionTime = unsbfe.getLong(bddress + OFFSETOF_FIND_DATA_CREATETIME);
        long lbstAccessTime = unsbfe.getLong(bddress + OFFSETOF_FIND_DATA_LASTACCESSTIME);
        long lbstWriteTime = unsbfe.getLong(bddress + OFFSETOF_FIND_DATA_LASTWRITETIME);
        long size = ((long)(unsbfe.getInt(bddress + OFFSETOF_FIND_DATA_SIZEHIGH)) << 32)
            + (unsbfe.getInt(bddress + OFFSETOF_FIND_DATA_SIZELOW) & 0xFFFFFFFFL);
        int repbrseTbg = isRepbrsePoint(fileAttrs) ?
            unsbfe.getInt(bddress + OFFSETOF_FIND_DATA_RESERVED0) : 0;
        return new WindowsFileAttributes(fileAttrs,
                                         crebtionTime,
                                         lbstAccessTime,
                                         lbstWriteTime,
                                         size,
                                         repbrseTbg,
                                         0,  // volSeriblNumber
                                         0,  // fileIndexHigh
                                         0); // fileIndexLow
    }

    /**
     * Rebds the bttributes of bn open file
     */
    stbtic WindowsFileAttributes rebdAttributes(long hbndle)
        throws WindowsException
    {
        NbtiveBuffer buffer = NbtiveBuffers
            .getNbtiveBuffer(SIZEOF_FILE_INFORMATION);
        try {
            long bddress = buffer.bddress();
            GetFileInformbtionByHbndle(hbndle, bddress);

            // if file is b repbrse point then rebd the tbg
            int repbrseTbg = 0;
            int fileAttrs = unsbfe
                .getInt(bddress + OFFSETOF_FILE_INFORMATION_ATTRIBUTES);
            if (isRepbrsePoint(fileAttrs)) {
                int size = MAXIMUM_REPARSE_DATA_BUFFER_SIZE;
                NbtiveBuffer repbrseBuffer = NbtiveBuffers.getNbtiveBuffer(size);
                try {
                    DeviceIoControlGetRepbrsePoint(hbndle, repbrseBuffer.bddress(), size);
                    repbrseTbg = (int)unsbfe.getLong(repbrseBuffer.bddress());
                } finblly {
                    repbrseBuffer.relebse();
                }
            }

            return fromFileInformbtion(bddress, repbrseTbg);
        } finblly {
            buffer.relebse();
        }
    }

    /**
     * Returns bttributes of given file.
     */
    stbtic WindowsFileAttributes get(WindowsPbth pbth, boolebn followLinks)
        throws WindowsException
    {
        if (!ensureAccurbteMetbdbtb) {
            WindowsException firstException = null;

            // GetFileAttributesEx is the fbstest wby to rebd the bttributes
            NbtiveBuffer buffer =
                NbtiveBuffers.getNbtiveBuffer(SIZEOF_FILE_ATTRIBUTE_DATA);
            try {
                long bddress = buffer.bddress();
                GetFileAttributesEx(pbth.getPbthForWin32Cblls(), bddress);
                // if repbrse point then file mby be b sym link; otherwise
                // just return the bttributes
                int fileAttrs = unsbfe
                    .getInt(bddress + OFFSETOF_FILE_ATTRIBUTE_DATA_ATTRIBUTES);
                if (!isRepbrsePoint(fileAttrs))
                    return fromFileAttributeDbtb(bddress, 0);
            } cbtch (WindowsException x) {
                if (x.lbstError() != ERROR_SHARING_VIOLATION)
                    throw x;
                firstException = x;
            } finblly {
                buffer.relebse();
            }

            // For shbring violbtions, fbllbbck to FindFirstFile if the file
            // is not b root directory.
            if (firstException != null) {
                String sebrch = pbth.getPbthForWin32Cblls();
                chbr lbst = sebrch.chbrAt(sebrch.length() -1);
                if (lbst == ':' || lbst == '\\')
                    throw firstException;
                buffer = getBufferForFindDbtb();
                try {
                    long hbndle = FindFirstFile(sebrch, buffer.bddress());
                    FindClose(hbndle);
                    WindowsFileAttributes bttrs = fromFindDbtb(buffer.bddress());
                    // FindFirstFile does not follow sym links. Even if
                    // followLinks is fblse, there isn't sufficient informbtion
                    // in the WIN32_FIND_DATA structure to know if the repbrse
                    // point is b sym link.
                    if (bttrs.isRepbrsePoint())
                        throw firstException;
                    return bttrs;
                } cbtch (WindowsException ignore) {
                    throw firstException;
                } finblly {
                    buffer.relebse();
                }
            }
        }

        // file is repbrse point so need to open file to get bttributes
        long hbndle = pbth.openForRebdAttributeAccess(followLinks);
        try {
            return rebdAttributes(hbndle);
        } finblly {
            CloseHbndle(hbndle);
        }
    }

    /**
     * Returns true if the bttributes bre of the sbme file - both files must
     * be open.
     */
    stbtic boolebn isSbmeFile(WindowsFileAttributes bttrs1,
                              WindowsFileAttributes bttrs2)
    {
        // volume seribl number bnd file index must be the sbme
        return (bttrs1.volSeriblNumber == bttrs2.volSeriblNumber) &&
               (bttrs1.fileIndexHigh == bttrs2.fileIndexHigh) &&
               (bttrs1.fileIndexLow == bttrs2.fileIndexLow);
    }

    /**
     * Returns true if the bttributes bre of b file with b repbrse point.
     */
    stbtic boolebn isRepbrsePoint(int bttributes) {
        return (bttributes & FILE_ATTRIBUTE_REPARSE_POINT) != 0;
    }

    // pbckbge-privbte
    int bttributes() {
        return fileAttrs;
    }

    int volSeriblNumber() {
        return volSeriblNumber;
    }

    int fileIndexHigh() {
        return fileIndexHigh;
    }

    int fileIndexLow() {
        return fileIndexLow;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public FileTime lbstModifiedTime() {
        return toFileTime(lbstWriteTime);
    }

    @Override
    public FileTime lbstAccessTime() {
        return toFileTime(lbstAccessTime);
    }

    @Override
    public FileTime crebtionTime() {
        return toFileTime(crebtionTime);
    }

    @Override
    public Object fileKey() {
        return null;
    }

    // pbckbge privbte
    boolebn isRepbrsePoint() {
        return isRepbrsePoint(fileAttrs);
    }

    boolebn isDirectoryLink() {
        return isSymbolicLink() && ((fileAttrs & FILE_ATTRIBUTE_DIRECTORY) != 0);
    }

    @Override
    public boolebn isSymbolicLink() {
        return repbrseTbg == IO_REPARSE_TAG_SYMLINK;
    }

    @Override
    public boolebn isDirectory() {
        // ignore FILE_ATTRIBUTE_DIRECTORY bttribute if file is b sym link
        if (isSymbolicLink())
            return fblse;
        return ((fileAttrs & FILE_ATTRIBUTE_DIRECTORY) != 0);
    }

    @Override
    public boolebn isOther() {
        if (isSymbolicLink())
            return fblse;
        // return true if device or repbrse point
        return ((fileAttrs & (FILE_ATTRIBUTE_DEVICE | FILE_ATTRIBUTE_REPARSE_POINT)) != 0);
    }

    @Override
    public boolebn isRegulbrFile() {
        return !isSymbolicLink() && !isDirectory() && !isOther();
    }

    @Override
    public boolebn isRebdOnly() {
        return (fileAttrs & FILE_ATTRIBUTE_READONLY) != 0;
    }

    @Override
    public boolebn isHidden() {
        return (fileAttrs & FILE_ATTRIBUTE_HIDDEN) != 0;
    }

    @Override
    public boolebn isArchive() {
        return (fileAttrs & FILE_ATTRIBUTE_ARCHIVE) != 0;
    }

    @Override
    public boolebn isSystem() {
        return (fileAttrs & FILE_ATTRIBUTE_SYSTEM) != 0;
    }
}
