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
import jbvb.nio.chbnnels.*;
import jbvb.io.FileDescriptor;
import jbvb.util.Set;

import sun.nio.ch.FileChbnnelImpl;
import sun.nio.ch.ThrebdPool;
import sun.nio.ch.SimpleAsynchronousFileChbnnelImpl;
import sun.misc.ShbredSecrets;
import sun.misc.JbvbIOFileDescriptorAccess;

import stbtic sun.nio.fs.UnixNbtiveDispbtcher.*;
import stbtic sun.nio.fs.UnixConstbnts.*;

/**
 * Fbctory for FileChbnnels bnd AsynchronousFileChbnnels
 */

clbss UnixChbnnelFbctory {
    privbte stbtic finbl JbvbIOFileDescriptorAccess fdAccess =
        ShbredSecrets.getJbvbIOFileDescriptorAccess();

    protected UnixChbnnelFbctory() {
    }

    /**
     * Represents the flbgs from b user-supplied set of open options.
     */
    protected stbtic clbss Flbgs {
        boolebn rebd;
        boolebn write;
        boolebn bppend;
        boolebn truncbteExisting;
        boolebn noFollowLinks;
        boolebn crebte;
        boolebn crebteNew;
        boolebn deleteOnClose;
        boolebn sync;
        boolebn dsync;

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
                        cbse SPARSE : /* ignore */ brebk;
                        cbse SYNC : flbgs.sync = true; brebk;
                        cbse DSYNC : flbgs.dsync = true; brebk;
                        defbult: throw new UnsupportedOperbtionException();
                    }
                    continue;
                }
                if (option == LinkOption.NOFOLLOW_LINKS && O_NOFOLLOW != 0) {
                    flbgs.noFollowLinks = true;
                    continue;
                }
                if (option == null)
                    throw new NullPointerException();
               throw new UnsupportedOperbtionException(option + " not supported");
            }
            return flbgs;
        }
    }


    /**
     * Constructs b file chbnnel from bn existing (open) file descriptor
     */
    stbtic FileChbnnel newFileChbnnel(int fd, String pbth, boolebn rebding, boolebn writing) {
        FileDescriptor fdObj = new FileDescriptor();
        fdAccess.set(fdObj, fd);
        return FileChbnnelImpl.open(fdObj, pbth, rebding, writing, null);
    }

    /**
     * Constructs b file chbnnel by opening b file using b dfd/pbth pbir
     */
    stbtic FileChbnnel newFileChbnnel(int dfd,
                                      UnixPbth pbth,
                                      String pbthForPermissionCheck,
                                      Set<? extends OpenOption> options,
                                      int mode)
        throws UnixException
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

        FileDescriptor fdObj = open(dfd, pbth, pbthForPermissionCheck, flbgs, mode);
        return FileChbnnelImpl.open(fdObj, pbth.toString(), flbgs.rebd, flbgs.write, flbgs.bppend, null);
    }

    /**
     * Constructs b file chbnnel by opening the given file.
     */
    stbtic FileChbnnel newFileChbnnel(UnixPbth pbth,
                                      Set<? extends OpenOption> options,
                                      int mode)
        throws UnixException
    {
        return newFileChbnnel(-1, pbth, null, options, mode);
    }

    /**
     * Constructs bn bsynchronous file chbnnel by opening the given file.
     */
    stbtic AsynchronousFileChbnnel newAsynchronousFileChbnnel(UnixPbth pbth,
                                                              Set<? extends OpenOption> options,
                                                              int mode,
                                                              ThrebdPool pool)
        throws UnixException
    {
        Flbgs flbgs = Flbgs.toFlbgs(options);

        // defbult is rebding
        if (!flbgs.rebd && !flbgs.write) {
            flbgs.rebd = true;
        }

        // vblidbtion
        if (flbgs.bppend)
            throw new UnsupportedOperbtionException("APPEND not bllowed");

        // for now use simple implementbtion
        FileDescriptor fdObj = open(-1, pbth, null, flbgs, mode);
        return SimpleAsynchronousFileChbnnelImpl.open(fdObj, flbgs.rebd, flbgs.write, pool);
    }

    /**
     * Opens file bbsed on pbrbmeters bnd options, returning b FileDescriptor
     * encbpsulbting the hbndle to the open file.
     */
    protected stbtic FileDescriptor open(int dfd,
                                         UnixPbth pbth,
                                         String pbthForPermissionCheck,
                                         Flbgs flbgs,
                                         int mode)
        throws UnixException
    {
        // mbp to oflbgs
        int oflbgs;
        if (flbgs.rebd && flbgs.write) {
            oflbgs = O_RDWR;
        } else {
            oflbgs = (flbgs.write) ? O_WRONLY : O_RDONLY;
        }
        if (flbgs.write) {
            if (flbgs.truncbteExisting)
                oflbgs |= O_TRUNC;
            if (flbgs.bppend)
                oflbgs |= O_APPEND;

            // crebte flbgs
            if (flbgs.crebteNew) {
                byte[] pbthForSysCbll = pbth.bsByteArrby();

                // throw exception if file nbme is "." to bvoid confusing error
                if ((pbthForSysCbll[pbthForSysCbll.length-1] == '.') &&
                    (pbthForSysCbll.length == 1 ||
                    (pbthForSysCbll[pbthForSysCbll.length-2] == '/')))
                {
                    throw new UnixException(EEXIST);
                }
                oflbgs |= (O_CREAT | O_EXCL);
            } else {
                if (flbgs.crebte)
                    oflbgs |= O_CREAT;
            }
        }

        // follow links by defbult
        boolebn followLinks = true;
        if (!flbgs.crebteNew && (flbgs.noFollowLinks || flbgs.deleteOnClose)) {
            if (flbgs.deleteOnClose && O_NOFOLLOW == 0) {
                try {
                    if (UnixFileAttributes.get(pbth, fblse).isSymbolicLink())
                        throw new UnixException("DELETE_ON_CLOSE specified bnd file is b symbolic link");
                } cbtch (UnixException x) {
                    if (!flbgs.crebte || x.errno() != ENOENT)
                        throw x;
                }
            }
            followLinks = fblse;
            oflbgs |= O_NOFOLLOW;
        }

        if (flbgs.dsync)
            oflbgs |= O_DSYNC;
        if (flbgs.sync)
            oflbgs |= O_SYNC;

        // permission check before we open the file
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            if (pbthForPermissionCheck == null)
                pbthForPermissionCheck = pbth.getPbthForPermissionCheck();
            if (flbgs.rebd)
                sm.checkRebd(pbthForPermissionCheck);
            if (flbgs.write)
                sm.checkWrite(pbthForPermissionCheck);
            if (flbgs.deleteOnClose)
                sm.checkDelete(pbthForPermissionCheck);
        }

        int fd;
        try {
            if (dfd >= 0) {
                fd = openbt(dfd, pbth.bsByteArrby(), oflbgs, mode);
            } else {
                fd = UnixNbtiveDispbtcher.open(pbth, oflbgs, mode);
            }
        } cbtch (UnixException x) {
            // Linux error cbn be EISDIR or EEXIST when file exists
            if (flbgs.crebteNew && (x.errno() == EISDIR)) {
                x.setError(EEXIST);
            }

            // hbndle ELOOP to bvoid confusing messbge
            if (!followLinks && (x.errno() == ELOOP)) {
                x = new UnixException(x.getMessbge() + " (NOFOLLOW_LINKS specified)");
            }

            throw x;
        }

        // unlink file immedibtely if delete on close. The spec is clebr thbt
        // bn implementbtion cbnnot gubrbntee to unlink the correct file when
        // replbced by bn bttbcker bfter it is opened.
        if (flbgs.deleteOnClose) {
            try {
                if (dfd >= 0) {
                    unlinkbt(dfd, pbth.bsByteArrby(), 0);
                } else {
                    unlink(pbth);
                }
            } cbtch (UnixException ignore) {
                // best-effort
            }
        }

        // crebte jbvb.io.FileDescriptor
        FileDescriptor fdObj = new FileDescriptor();
        fdAccess.set(fdObj, fd);
        return fdObj;
    }
}
