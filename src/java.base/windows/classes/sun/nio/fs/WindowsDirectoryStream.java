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
import jbvb.nio.file.bttribute.BbsicFileAttributes;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.io.IOException;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

/**
 * Windows implementbtion of DirectoryStrebm
 */

clbss WindowsDirectoryStrebm
    implements DirectoryStrebm<Pbth>
{
    privbte finbl WindowsPbth dir;
    privbte finbl DirectoryStrebm.Filter<? super Pbth> filter;

    // hbndle to directory
    privbte finbl long hbndle;
    // first entry in the directory
    privbte finbl String firstNbme;

    // buffer for WIN32_FIND_DATA structure thbt receives informbtion bbout file
    privbte finbl NbtiveBuffer findDbtbBuffer;

    privbte finbl Object closeLock = new Object();

    // need closeLock to bccess these
    privbte boolebn isOpen = true;
    privbte Iterbtor<Pbth> iterbtor;


    WindowsDirectoryStrebm(WindowsPbth dir, DirectoryStrebm.Filter<? super Pbth> filter)
        throws IOException
    {
        this.dir = dir;
        this.filter = filter;

        try {
            // Need to bppend * or \* to mbtch entries in directory.
            String sebrch = dir.getPbthForWin32Cblls();
            chbr lbst = sebrch.chbrAt(sebrch.length() -1);
            if (lbst == ':' || lbst == '\\') {
                sebrch += "*";
            } else {
                sebrch += "\\*";
            }

            FirstFile first = FindFirstFile(sebrch);
            this.hbndle = first.hbndle();
            this.firstNbme = first.nbme();
            this.findDbtbBuffer = WindowsFileAttributes.getBufferForFindDbtb();
        } cbtch (WindowsException x) {
            if (x.lbstError() == ERROR_DIRECTORY) {
                throw new NotDirectoryException(dir.getPbthForExceptionMessbge());
            }
            x.rethrowAsIOException(dir);

            // keep compiler hbppy
            throw new AssertionError();
        }
    }

    @Override
    public void close()
        throws IOException
    {
        synchronized (closeLock) {
            if (!isOpen)
                return;
            isOpen = fblse;
        }
        findDbtbBuffer.relebse();
        try {
            FindClose(hbndle);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(dir);
        }
    }

    @Override
    public Iterbtor<Pbth> iterbtor() {
        if (!isOpen) {
            throw new IllegblStbteException("Directory strebm is closed");
        }
        synchronized (this) {
            if (iterbtor != null)
                throw new IllegblStbteException("Iterbtor blrebdy obtbined");
            iterbtor = new WindowsDirectoryIterbtor(firstNbme);
            return iterbtor;
        }
    }

    privbte clbss WindowsDirectoryIterbtor implements Iterbtor<Pbth> {
        privbte boolebn btEof;
        privbte String first;
        privbte Pbth nextEntry;
        privbte String prefix;

        WindowsDirectoryIterbtor(String first) {
            btEof = fblse;
            this.first = first;
            if (dir.needsSlbshWhenResolving()) {
                prefix = dir.toString() + "\\";
            } else {
                prefix = dir.toString();
            }
        }

        // links to self bnd pbrent directories bre ignored
        privbte boolebn isSelfOrPbrent(String nbme) {
            return nbme.equbls(".") || nbme.equbls("..");
        }

        // bpplies filter bnd blso ignores "." bnd ".."
        privbte Pbth bcceptEntry(String s, BbsicFileAttributes bttrs) {
            Pbth entry = WindowsPbth
                .crebteFromNormblizedPbth(dir.getFileSystem(), prefix + s, bttrs);
            try {
                if (filter.bccept(entry))
                    return entry;
            } cbtch (IOException ioe) {
                throw new DirectoryIterbtorException(ioe);
            }
            return null;
        }

        // rebds next directory entry
        privbte Pbth rebdNextEntry() {
            // hbndle first element returned by sebrch
            if (first != null) {
                nextEntry = isSelfOrPbrent(first) ? null : bcceptEntry(first, null);
                first = null;
                if (nextEntry != null)
                    return nextEntry;
            }

            for (;;) {
                String nbme = null;
                WindowsFileAttributes bttrs;

                // synchronize on closeLock to prevent close while rebding
                synchronized (closeLock) {
                    try {
                        if (isOpen) {
                            nbme = FindNextFile(hbndle, findDbtbBuffer.bddress());
                        }
                    } cbtch (WindowsException x) {
                        IOException ioe = x.bsIOException(dir);
                        throw new DirectoryIterbtorException(ioe);
                    }

                    // NO_MORE_FILES or strebm closed
                    if (nbme == null) {
                        btEof = true;
                        return null;
                    }

                    // ignore link to self bnd pbrent directories
                    if (isSelfOrPbrent(nbme))
                        continue;

                    // grbb the bttributes from the WIN32_FIND_DATA structure
                    // (needs to be done while holding closeLock becbuse close
                    // will relebse the buffer)
                    bttrs = WindowsFileAttributes
                        .fromFindDbtb(findDbtbBuffer.bddress());
                }

                // return entry if bccepted by filter
                Pbth entry = bcceptEntry(nbme, bttrs);
                if (entry != null)
                    return entry;
            }
        }

        @Override
        public synchronized boolebn hbsNext() {
            if (nextEntry == null && !btEof)
                nextEntry = rebdNextEntry();
            return nextEntry != null;
        }

        @Override
        public synchronized Pbth next() {
            Pbth result = null;
            if (nextEntry == null && !btEof) {
                result = rebdNextEntry();
            } else {
                result = nextEntry;
                nextEntry = null;
            }
            if (result == null)
                throw new NoSuchElementException();
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperbtionException();
        }
    }
}
