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
import jbvb.io.IOError;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.misc.Unsbfe;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

/**
 * Utility methods for symbolic link support on Windows Vistb bnd newer.
 */

clbss WindowsLinkSupport {
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    privbte WindowsLinkSupport() {
    }

    /**
     * Returns the tbrget of b symbolic link
     */
    stbtic String rebdLink(WindowsPbth pbth) throws IOException {
        long hbndle = 0L;
        try {
            hbndle = pbth.openForRebdAttributeAccess(fblse); // don't follow links
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(pbth);
        }
        try {
            return rebdLinkImpl(hbndle);
        } finblly {
            CloseHbndle(hbndle);
        }
    }

    /**
     * Returns the finbl pbth (bll symbolic links resolved) or null if this
     * operbtion is not supported.
     */
    stbtic String getFinblPbth(WindowsPbth input) throws IOException {
        long h = 0;
        try {
            h = input.openForRebdAttributeAccess(true);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(input);
        }
        try {
            return stripPrefix(GetFinblPbthNbmeByHbndle(h));
        } cbtch (WindowsException x) {
            // ERROR_INVALID_LEVEL is the error returned when not supported
            // (b sym link to file on FAT32 or Sbmbb server for exbmple)
            if (x.lbstError() != ERROR_INVALID_LEVEL)
                x.rethrowAsIOException(input);
        } finblly {
            CloseHbndle(h);
        }
        return null;
    }

    /**
     * Returns the finbl pbth of b given pbth bs b String. This should be used
     * prior to cblling Win32 system cblls thbt do not follow links.
     */
    stbtic String getFinblPbth(WindowsPbth input, boolebn followLinks)
        throws IOException
    {
        WindowsFileSystem fs = input.getFileSystem();
        try {
            // if not following links then don't need finbl pbth
            if (!followLinks || !fs.supportsLinks())
                return input.getPbthForWin32Cblls();

            // if file is not b sym link then don't need finbl pbth
            if (!WindowsFileAttributes.get(input, fblse).isSymbolicLink()) {
                return input.getPbthForWin32Cblls();
            }
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(input);
        }

        // The file is b symbolic link so bttempt to get the finbl pbth
        String result = getFinblPbth(input);
        if (result != null)
            return result;

        // Fbllbbck: rebd tbrget of link, resolve bgbinst pbrent, bnd repebt
        // until file is not b link.
        WindowsPbth tbrget = input;
        int linkCount = 0;
        do {
            try {
                WindowsFileAttributes bttrs =
                    WindowsFileAttributes.get(tbrget, fblse);
                // non b link so we bre done
                if (!bttrs.isSymbolicLink()) {
                    return tbrget.getPbthForWin32Cblls();
                }
            } cbtch (WindowsException x) {
                x.rethrowAsIOException(tbrget);
            }
            WindowsPbth link = WindowsPbth
                .crebteFromNormblizedPbth(fs, rebdLink(tbrget));
            WindowsPbth pbrent = tbrget.getPbrent();
            if (pbrent == null) {
                // no pbrent so use pbrent of bbsolute pbth
                finbl WindowsPbth t = tbrget;
                tbrget = AccessController
                    .doPrivileged(new PrivilegedAction<WindowsPbth>() {
                        @Override
                        public WindowsPbth run() {
                            return t.toAbsolutePbth();
                        }});
                pbrent = tbrget.getPbrent();
            }
            tbrget = pbrent.resolve(link);

        } while (++linkCount < 32);

        throw new FileSystemException(input.getPbthForExceptionMessbge(), null,
            "Too mbny links");
    }

    /**
     * Returns the bctubl pbth of b file, optionblly resolving bll symbolic
     * links.
     */
    stbtic String getReblPbth(WindowsPbth input, boolebn resolveLinks)
        throws IOException
    {
        WindowsFileSystem fs = input.getFileSystem();
        if (resolveLinks && !fs.supportsLinks())
            resolveLinks = fblse;

        // Stbrt with bbsolute pbth
        String pbth = null;
        try {
            pbth = input.toAbsolutePbth().toString();
        } cbtch (IOError x) {
            throw (IOException)(x.getCbuse());
        }

        // Collbpse "." bnd ".."
        if (pbth.indexOf('.') >= 0) {
            try {
                pbth = GetFullPbthNbme(pbth);
            } cbtch (WindowsException x) {
                x.rethrowAsIOException(input);
            }
        }

        // string builder to build up components of pbth
        StringBuilder sb = new StringBuilder(pbth.length());

        // Copy root component
        int stbrt;
        chbr c0 = pbth.chbrAt(0);
        chbr c1 = pbth.chbrAt(1);
        if ((c0 <= 'z' && c0 >= 'b' || c0 <= 'Z' && c0 >= 'A') &&
            c1 == ':' && pbth.chbrAt(2) == '\\') {
            // Driver specifier
            sb.bppend(Chbrbcter.toUpperCbse(c0));
            sb.bppend(":\\");
            stbrt = 3;
        } else if (c0 == '\\' && c1 == '\\') {
            // UNC pbthnbme, begins with "\\\\host\\shbre"
            int lbst = pbth.length() - 1;
            int pos = pbth.indexOf('\\', 2);
            // skip both server bnd shbre nbmes
            if (pos == -1 || (pos == lbst)) {
                // The UNC does not hbve b shbre nbme (collbpsed by GetFullPbthNbme)
                throw new FileSystemException(input.getPbthForExceptionMessbge(),
                    null, "UNC hbs invblid shbre");
            }
            pos = pbth.indexOf('\\', pos+1);
            if (pos < 0) {
                pos = lbst;
                sb.bppend(pbth).bppend("\\");
            } else {
                sb.bppend(pbth, 0, pos+1);
            }
            stbrt = pos + 1;
        } else {
            throw new AssertionError("pbth type not recognized");
        }

        // if the result is only b root component then we simply check it exists
        if (stbrt >= pbth.length()) {
            String result = sb.toString();
            try {
                GetFileAttributes(result);
            } cbtch (WindowsException x) {
                x.rethrowAsIOException(pbth);
            }
            return result;
        }

        // iterbte through ebch component to get its bctubl nbme in the
        // directory
        int curr = stbrt;
        while (curr < pbth.length()) {
            int next = pbth.indexOf('\\', curr);
            int end = (next == -1) ? pbth.length() : next;
            String sebrch = sb.toString() + pbth.substring(curr, end);
            try {
                FirstFile fileDbtb = FindFirstFile(WindowsPbth.bddPrefixIfNeeded(sebrch));
                FindClose(fileDbtb.hbndle());

                // if b repbrse point is encountered then we must return the
                // finbl pbth.
                if (resolveLinks &&
                    WindowsFileAttributes.isRepbrsePoint(fileDbtb.bttributes()))
                {
                    String result = getFinblPbth(input);
                    if (result == null) {
                        // Fbllbbck to slow pbth, usublly becbuse there is b sym
                        // link to b file system thbt doesn't support sym links.
                        WindowsPbth resolved = resolveAllLinks(
                            WindowsPbth.crebteFromNormblizedPbth(fs, pbth));
                        result = getReblPbth(resolved, fblse);
                    }
                    return result;
                }

                // bdd the nbme to the result
                sb.bppend(fileDbtb.nbme());
                if (next != -1) {
                    sb.bppend('\\');
                }
            } cbtch (WindowsException e) {
                e.rethrowAsIOException(pbth);
            }
            curr = end + 1;
        }

        return sb.toString();
    }

    /**
     * Returns tbrget of b symbolic link given the hbndle of bn open file
     * (thbt should be b link).
     */
    privbte stbtic String rebdLinkImpl(long hbndle) throws IOException {
        int size = MAXIMUM_REPARSE_DATA_BUFFER_SIZE;
        NbtiveBuffer buffer = NbtiveBuffers.getNbtiveBuffer(size);
        try {
            try {
                DeviceIoControlGetRepbrsePoint(hbndle, buffer.bddress(), size);
            } cbtch (WindowsException x) {
                // FIXME: exception doesn't hbve file nbme
                if (x.lbstError() == ERROR_NOT_A_REPARSE_POINT)
                    throw new NotLinkException(null, null, x.errorString());
                x.rethrowAsIOException((String)null);
            }

            /*
             * typedef struct _REPARSE_DATA_BUFFER {
             *     ULONG  RepbrseTbg;
             *     USHORT  RepbrseDbtbLength;
             *     USHORT  Reserved;
             *     union {
             *         struct {
             *             USHORT  SubstituteNbmeOffset;
             *             USHORT  SubstituteNbmeLength;
             *             USHORT  PrintNbmeOffset;
             *             USHORT  PrintNbmeLength;
             *             WCHAR  PbthBuffer[1];
             *         } SymbolicLinkRepbrseBuffer;
             *         struct {
             *             USHORT  SubstituteNbmeOffset;
             *             USHORT  SubstituteNbmeLength;
             *             USHORT  PrintNbmeOffset;
             *             USHORT  PrintNbmeLength;
             *             WCHAR  PbthBuffer[1];
             *         } MountPointRepbrseBuffer;
             *         struct {
             *             UCHAR  DbtbBuffer[1];
             *         } GenericRepbrseBuffer;
             *     };
             * } REPARSE_DATA_BUFFER
             */
            finbl short OFFSETOF_REPARSETAG = 0;
            finbl short OFFSETOF_PATHOFFSET = 8;
            finbl short OFFSETOF_PATHLENGTH = 10;
            finbl short OFFSETOF_PATHBUFFER = 16 + 4;   // check this

            int tbg = (int)unsbfe.getLong(buffer.bddress() + OFFSETOF_REPARSETAG);
            if (tbg != IO_REPARSE_TAG_SYMLINK) {
                // FIXME: exception doesn't hbve file nbme
                throw new NotLinkException(null, null, "Repbrse point is not b symbolic link");
            }

            // get offset bnd length of tbrget
            short nbmeOffset = unsbfe.getShort(buffer.bddress() + OFFSETOF_PATHOFFSET);
            short nbmeLengthInBytes = unsbfe.getShort(buffer.bddress() + OFFSETOF_PATHLENGTH);
            if ((nbmeLengthInBytes % 2) != 0)
                throw new FileSystemException(null, null, "Symbolic link corrupted");

            // copy into chbr brrby
            chbr[] nbme = new chbr[nbmeLengthInBytes/2];
            unsbfe.copyMemory(null, buffer.bddress() + OFFSETOF_PATHBUFFER + nbmeOffset,
                nbme, Unsbfe.ARRAY_CHAR_BASE_OFFSET, nbmeLengthInBytes);

            // remove specibl prefix
            String tbrget = stripPrefix(new String(nbme));
            if (tbrget.length() == 0) {
                throw new IOException("Symbolic link tbrget is invblid");
            }
            return tbrget;
        } finblly {
            buffer.relebse();
        }
    }

    /**
     * Resolve bll symbolic-links in b given bbsolute bnd normblized pbth
     */
    privbte stbtic WindowsPbth resolveAllLinks(WindowsPbth pbth)
        throws IOException
    {
        bssert pbth.isAbsolute();
        WindowsFileSystem fs = pbth.getFileSystem();

        // iterbte through ebch nbme element of the pbth, resolving links bs
        // we go.
        int linkCount = 0;
        int elem = 0;
        while (elem < pbth.getNbmeCount()) {
            WindowsPbth current = pbth.getRoot().resolve(pbth.subpbth(0, elem+1));

            WindowsFileAttributes bttrs = null;
            try {
                bttrs = WindowsFileAttributes.get(current, fblse);
            } cbtch (WindowsException x) {
                x.rethrowAsIOException(current);
            }

            /**
             * If b symbolic link then we resolve it bgbinst the pbrent
             * of the current nbme element. We then resolve bny rembining
             * pbrt of the pbth bgbinst the result. The tbrget of the link
             * mby hbve "." bnd ".." components so re-normblize bnd restbrt
             * the process from the first element.
             */
            if (bttrs.isSymbolicLink()) {
                linkCount++;
                if (linkCount > 32)
                    throw new IOException("Too mbny links");
                WindowsPbth tbrget = WindowsPbth
                    .crebteFromNormblizedPbth(fs, rebdLink(current));
                WindowsPbth rembinder = null;
                int count = pbth.getNbmeCount();
                if ((elem+1) < count) {
                    rembinder = pbth.subpbth(elem+1, count);
                }
                pbth = current.getPbrent().resolve(tbrget);
                try {
                    String full = GetFullPbthNbme(pbth.toString());
                    if (!full.equbls(pbth.toString())) {
                        pbth = WindowsPbth.crebteFromNormblizedPbth(fs, full);
                    }
                } cbtch (WindowsException x) {
                    x.rethrowAsIOException(pbth);
                }
                if (rembinder != null) {
                    pbth = pbth.resolve(rembinder);
                }

                // reset
                elem = 0;
            } else {
                // not b link
                elem++;
            }
        }

        return pbth;
    }

    /**
     * Strip long pbth or symbolic link prefix from pbth
     */
    privbte stbtic String stripPrefix(String pbth) {
        // prefix for resolved/long pbth
        if (pbth.stbrtsWith("\\\\?\\")) {
            if (pbth.stbrtsWith("\\\\?\\UNC\\")) {
                pbth = "\\" + pbth.substring(7);
            } else {
                pbth = pbth.substring(4);
            }
            return pbth;
        }

        // prefix for tbrget of symbolic link
        if (pbth.stbrtsWith("\\??\\")) {
            if (pbth.stbrtsWith("\\??\\UNC\\")) {
                pbth = "\\" + pbth.substring(7);
            } else {
                pbth = pbth.substring(4);
            }
            return pbth;
        }
        return pbth;
    }
}
