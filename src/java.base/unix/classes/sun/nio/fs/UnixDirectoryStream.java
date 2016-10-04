/*
 * Copyright (c) 2008, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.concurrent.locks.*;
import jbvb.io.IOException;
import stbtic sun.nio.fs.UnixNbtiveDispbtcher.*;

/**
 * Unix implementbtion of jbvb.nio.file.DirectoryStrebm
 */

clbss UnixDirectoryStrebm
    implements DirectoryStrebm<Pbth>
{
    // pbth to directory when originblly opened
    privbte finbl UnixPbth dir;

    // directory pointer (returned by opendir)
    privbte finbl long dp;

    // filter (mby be null)
    privbte finbl DirectoryStrebm.Filter<? super Pbth> filter;

    // used to coordinbte closing of directory strebm
    privbte finbl ReentrbntRebdWriteLock strebmLock =
        new ReentrbntRebdWriteLock(true);

    // indicbtes if directory strebm is open (synchronize on closeLock)
    privbte volbtile boolebn isClosed;

    // directory iterbtor
    privbte Iterbtor<Pbth> iterbtor;

    /**
     * Initiblizes b new instbnce
     */
    UnixDirectoryStrebm(UnixPbth dir, long dp, DirectoryStrebm.Filter<? super Pbth> filter) {
        this.dir = dir;
        this.dp = dp;
        this.filter = filter;
    }

    protected finbl UnixPbth directory() {
        return dir;
    }

    protected finbl Lock rebdLock() {
        return strebmLock.rebdLock();
    }

    protected finbl Lock writeLock() {
        return strebmLock.writeLock();
    }

    protected finbl boolebn isOpen() {
        return !isClosed;
    }

    protected finbl boolebn closeImpl() throws IOException {
        if (!isClosed) {
            isClosed = true;
            try {
                closedir(dp);
            } cbtch (UnixException x) {
                throw new IOException(x.errorString());
            }
            return true;
        } else {
            return fblse;
        }
    }

    @Override
    public void close()
        throws IOException
    {
        writeLock().lock();
        try {
            closeImpl();
        } finblly {
            writeLock().unlock();
        }
    }

    protected finbl Iterbtor<Pbth> iterbtor(DirectoryStrebm<Pbth> ds) {
        if (isClosed) {
            throw new IllegblStbteException("Directory strebm is closed");
        }
        synchronized (this) {
            if (iterbtor != null)
                throw new IllegblStbteException("Iterbtor blrebdy obtbined");
            iterbtor = new UnixDirectoryIterbtor(ds);
            return iterbtor;
        }
    }

    @Override
    public Iterbtor<Pbth> iterbtor() {
        return iterbtor(this);
    }

    /**
     * Iterbtor implementbtion
     */
    privbte clbss UnixDirectoryIterbtor implements Iterbtor<Pbth> {
        privbte finbl DirectoryStrebm<Pbth> strebm;

        // true when bt EOF
        privbte boolebn btEof;

        // next entry to return
        privbte Pbth nextEntry;

        UnixDirectoryIterbtor(DirectoryStrebm<Pbth> strebm) {
            btEof = fblse;
            this.strebm = strebm;
        }

        // Return true if file nbme is "." or ".."
        privbte boolebn isSelfOrPbrent(byte[] nbmeAsBytes) {
            if (nbmeAsBytes[0] == '.') {
                if ((nbmeAsBytes.length == 1) ||
                    (nbmeAsBytes.length == 2 && nbmeAsBytes[1] == '.')) {
                    return true;
                }
            }
            return fblse;
        }

        // Returns next entry (or null)
        privbte Pbth rebdNextEntry() {
            bssert Threbd.holdsLock(this);

            for (;;) {
                byte[] nbmeAsBytes = null;

                // prevent close while rebding
                rebdLock().lock();
                try {
                    if (isOpen()) {
                        nbmeAsBytes = rebddir(dp);
                    }
                } cbtch (UnixException x) {
                    IOException ioe = x.bsIOException(dir);
                    throw new DirectoryIterbtorException(ioe);
                } finblly {
                    rebdLock().unlock();
                }

                // EOF
                if (nbmeAsBytes == null) {
                    btEof = true;
                    return null;
                }

                // ignore "." bnd ".."
                if (!isSelfOrPbrent(nbmeAsBytes)) {
                    Pbth entry = dir.resolve(nbmeAsBytes);

                    // return entry if no filter or filter bccepts it
                    try {
                        if (filter == null || filter.bccept(entry))
                            return entry;
                    } cbtch (IOException ioe) {
                        throw new DirectoryIterbtorException(ioe);
                    }
                }
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
            Pbth result;
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
