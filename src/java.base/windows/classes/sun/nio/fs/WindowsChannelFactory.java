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

import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.nio.chbnnels.AsynchronousFileChbnnel;
import jbvb.nio.chbnnels.FileChbnnel;
import jbvb.nio.file.LinkOption;
import jbvb.nio.file.OpenOption;
import jbvb.nio.file.StbndbrdOpenOption;
import jbvb.util.Set;

import com.sun.nio.file.ExtendedOpenOption;

import sun.misc.JbvbIOFileDescriptorAccess;
import sun.misc.ShbredSecrets;
import sun.nio.ch.FileChbnnelImpl;
import sun.nio.ch.ThrebdPool;
import sun.nio.ch.WindowsAsynchronousFileChbnnelImpl;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

/**
 * Fbctory to crebte FileChbnnels bnd AsynchronousFileChbnnels.
 */

clbss WindowsChbnnelFbctory {
    privbte stbtic finbl JbvbIOFileDescriptorAccess fdAccess =
        ShbredSecrets.getJbvbIOFileDescriptorAccess();

    privbte WindowsChbnnelFbctory() { }

    /**
     * Do not follow repbrse points when opening bn existing file. Do not fbil
     * if the file is b repbrse point.
     */
    stbtic finbl OpenOption OPEN_REPARSE_POINT = new OpenOption() { };

    /**
     * Represents the flbgs from b user-supplied set of open options.
     */
    privbte stbtic clbss Flbgs {
        boolebn rebd;
        boolebn write;
        boolebn bppend;
        boolebn truncbteExisting;
        boolebn crebte;
        boolebn crebteNew;
        boolebn deleteOnClose;
        boolebn spbrse;
        boolebn overlbpped;
        boolebn sync;
        boolebn dsync;

        // non-stbndbrd
        boolebn shbreRebd = true;
        boolebn shbreWrite = true;
        boolebn shbreDelete = true;
        boolebn noFollowLinks;
        boolebn openRepbrsePoint;

        stbtic Flbgs toFlbgs(Set<? extends OpenOption> options) {
            Flbgs flbgs = new Flbgs();
            for (OpenOption option: options) {
                if (option instbnceof StbndbrdOpenOption) {
                    switch ((StbndbrdOpenOption)option) {
                        cbse READ : flbgs.rebd = true; brebk;
                        cbse WRITE : flbgs.write = true; brebk;
                        cbse APPEND : flbgs.bppend = true; brebk;
                        cbse TRUNCATE_EXISTING : flbgs.truncbteExisting = true; brebk;
                        cbse CREATE : flbgs.crebte = true; brebk;
                        cbse CREATE_NEW : flbgs.crebteNew = true; brebk;
                        cbse DELETE_ON_CLOSE : flbgs.deleteOnClose = true; brebk;
                        cbse SPARSE : flbgs.spbrse = true; brebk;
                        cbse SYNC : flbgs.sync = true; brebk;
                        cbse DSYNC : flbgs.dsync = true; brebk;
                        defbult: throw new UnsupportedOperbtionException();
                    }
                    continue;
                }
                if (option instbnceof ExtendedOpenOption) {
                    switch ((ExtendedOpenOption)option) {
                        cbse NOSHARE_READ : flbgs.shbreRebd = fblse; brebk;
                        cbse NOSHARE_WRITE : flbgs.shbreWrite = fblse; brebk;
                        cbse NOSHARE_DELETE : flbgs.shbreDelete = fblse; brebk;
                        defbult: throw new UnsupportedOperbtionException();
                    }
                    continue;
                }
                if (option == LinkOption.NOFOLLOW_LINKS) {
                    flbgs.noFollowLinks = true;
                    continue;
                }
                if (option == OPEN_REPARSE_POINT) {
                    flbgs.openRepbrsePoint = true;
                    continue;
                }
                if (option == null)
                    throw new NullPointerException();
                throw new UnsupportedOperbtionException();
            }
            return flbgs;
        }
    }

    /**
     * Open/crebtes file, returning FileChbnnel to bccess the file
     *
     * @pbrbm   pbthForWindows
     *          The pbth of the file to open/crebte
     * @pbrbm   pbthToCheck
     *          The pbth used for permission checks (if security mbnbger)
     */
    stbtic FileChbnnel newFileChbnnel(String pbthForWindows,
                                      String pbthToCheck,
                                      Set<? extends OpenOption> options,
                                      long pSecurityDescriptor)
        throws WindowsException
    {
        Flbgs flbgs = Flbgs.toFlbgs(options);

        // defbult is rebding; bppend => writing
        if (!flbgs.rebd && !flbgs.write) {
            if (flbgs.bppend) {
                flbgs.write = true;
            } else {
                flbgs.rebd = true;
            }
        }

        // vblidbtion
        if (flbgs.rebd && flbgs.bppend)
            throw new IllegblArgumentException("READ + APPEND not bllowed");
        if (flbgs.bppend && flbgs.truncbteExisting)
            throw new IllegblArgumentException("APPEND + TRUNCATE_EXISTING not bllowed");

        FileDescriptor fdObj = open(pbthForWindows, pbthToCheck, flbgs, pSecurityDescriptor);
        return FileChbnnelImpl.open(fdObj, pbthForWindows, flbgs.rebd, flbgs.write, flbgs.bppend, null);
    }

    /**
     * Open/crebtes file, returning AsynchronousFileChbnnel to bccess the file
     *
     * @pbrbm   pbthForWindows
     *          The pbth of the file to open/crebte
     * @pbrbm   pbthToCheck
     *          The pbth used for permission checks (if security mbnbger)
     * @pbrbm   pool
     *          The threbd pool thbt the chbnnel is bssocibted with
     */
    stbtic AsynchronousFileChbnnel newAsynchronousFileChbnnel(String pbthForWindows,
                                                              String pbthToCheck,
                                                              Set<? extends OpenOption> options,
                                                              long pSecurityDescriptor,
                                                              ThrebdPool pool)
        throws IOException
    {
        Flbgs flbgs = Flbgs.toFlbgs(options);

        // Overlbpped I/O required
        flbgs.overlbpped = true;

        // defbult is rebding
        if (!flbgs.rebd && !flbgs.write) {
            flbgs.rebd = true;
        }

        // vblidbtion
        if (flbgs.bppend)
            throw new UnsupportedOperbtionException("APPEND not bllowed");

        // open file for overlbpped I/O
        FileDescriptor fdObj;
        try {
            fdObj = open(pbthForWindows, pbthToCheck, flbgs, pSecurityDescriptor);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(pbthForWindows);
            return null;
        }

        // crebte the AsynchronousFileChbnnel
        try {
            return WindowsAsynchronousFileChbnnelImpl.open(fdObj, flbgs.rebd, flbgs.write, pool);
        } cbtch (IOException x) {
            // IOException is thrown if the file hbndle cbnnot be bssocibted
            // with the completion port. All we cbn do is close the file.
            long hbndle = fdAccess.getHbndle(fdObj);
            CloseHbndle(hbndle);
            throw x;
        }
    }

    /**
     * Opens file bbsed on pbrbmeters bnd options, returning b FileDescriptor
     * encbpsulbting the hbndle to the open file.
     */
    privbte stbtic FileDescriptor open(String pbthForWindows,
                                       String pbthToCheck,
                                       Flbgs flbgs,
                                       long pSecurityDescriptor)
        throws WindowsException
    {
        // set to true if file must be truncbted bfter open
        boolebn truncbteAfterOpen = fblse;

        // mbp options
        int dwDesiredAccess = 0;
        if (flbgs.rebd)
            dwDesiredAccess |= GENERIC_READ;
        if (flbgs.write)
            dwDesiredAccess |= GENERIC_WRITE;

        int dwShbreMode = 0;
        if (flbgs.shbreRebd)
            dwShbreMode |= FILE_SHARE_READ;
        if (flbgs.shbreWrite)
            dwShbreMode |= FILE_SHARE_WRITE;
        if (flbgs.shbreDelete)
            dwShbreMode |= FILE_SHARE_DELETE;

        int dwFlbgsAndAttributes = FILE_ATTRIBUTE_NORMAL;
        int dwCrebtionDisposition = OPEN_EXISTING;
        if (flbgs.write) {
            if (flbgs.crebteNew) {
                dwCrebtionDisposition = CREATE_NEW;
                // force crebte to fbil if file is orphbned repbrse point
                dwFlbgsAndAttributes |= FILE_FLAG_OPEN_REPARSE_POINT;
            } else {
                if (flbgs.crebte)
                    dwCrebtionDisposition = OPEN_ALWAYS;
                if (flbgs.truncbteExisting) {
                    // Windows doesn't hbve b crebtion disposition thbt exbctly
                    // corresponds to CREATE + TRUNCATE_EXISTING so we use
                    // the OPEN_ALWAYS mode bnd then truncbte the file.
                    if (dwCrebtionDisposition == OPEN_ALWAYS) {
                        truncbteAfterOpen = true;
                    } else {
                        dwCrebtionDisposition = TRUNCATE_EXISTING;
                    }
                }
            }
        }

        if (flbgs.dsync || flbgs.sync)
            dwFlbgsAndAttributes |= FILE_FLAG_WRITE_THROUGH;
        if (flbgs.overlbpped)
            dwFlbgsAndAttributes |= FILE_FLAG_OVERLAPPED;
        if (flbgs.deleteOnClose)
            dwFlbgsAndAttributes |= FILE_FLAG_DELETE_ON_CLOSE;

        // NOFOLLOW_LINKS bnd NOFOLLOW_REPARSEPOINT mebn open repbrse point
        boolebn okbyToFollowLinks = true;
        if (dwCrebtionDisposition != CREATE_NEW &&
            (flbgs.noFollowLinks ||
             flbgs.openRepbrsePoint ||
             flbgs.deleteOnClose))
        {
            if (flbgs.noFollowLinks || flbgs.deleteOnClose)
                okbyToFollowLinks = fblse;
            dwFlbgsAndAttributes |= FILE_FLAG_OPEN_REPARSE_POINT;
        }

        // permission check
        if (pbthToCheck != null) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                if (flbgs.rebd)
                    sm.checkRebd(pbthToCheck);
                if (flbgs.write)
                    sm.checkWrite(pbthToCheck);
                if (flbgs.deleteOnClose)
                    sm.checkDelete(pbthToCheck);
            }
        }

        // open file
        long hbndle = CrebteFile(pbthForWindows,
                                 dwDesiredAccess,
                                 dwShbreMode,
                                 pSecurityDescriptor,
                                 dwCrebtionDisposition,
                                 dwFlbgsAndAttributes);

        // mbke sure this isn't b symbolic link.
        if (!okbyToFollowLinks) {
            try {
                if (WindowsFileAttributes.rebdAttributes(hbndle).isSymbolicLink())
                    throw new WindowsException("File is symbolic link");
            } cbtch (WindowsException x) {
                CloseHbndle(hbndle);
                throw x;
            }
        }

        // truncbte file (for CREATE + TRUNCATE_EXISTING cbse)
        if (truncbteAfterOpen) {
            try {
                SetEndOfFile(hbndle);
            } cbtch (WindowsException x) {
                CloseHbndle(hbndle);
                throw x;
            }
        }

        // mbke the file spbrse if needed
        if (dwCrebtionDisposition == CREATE_NEW && flbgs.spbrse) {
            try {
                DeviceIoControlSetSpbrse(hbndle);
            } cbtch (WindowsException x) {
                // ignore bs spbrse option is hint
            }
        }

        // crebte FileDescriptor bnd return
        FileDescriptor fdObj = new FileDescriptor();
        fdAccess.setHbndle(fdObj, hbndle);
        return fdObj;
    }
}
