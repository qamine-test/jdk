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
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.FileChbnnel;
import jbvb.io.IOException;
import jbvb.util.*;

import stbtic sun.nio.fs.UnixNbtiveDispbtcher.*;
import stbtic sun.nio.fs.UnixConstbnts.*;
import stbtic sun.nio.fs.SolbrisConstbnts.*;

/**
 * Solbris emulbtion of NbmedAttributeView using extended bttributes.
 */

clbss SolbrisUserDefinedFileAttributeView
    extends AbstrbctUserDefinedFileAttributeView
{
    privbte stbtic finbl byte[] HERE = { '.' };

    privbte byte[] nbmeAsBytes(UnixPbth file, String nbme) throws IOException {
        byte[] bytes = Util.toBytes(nbme);
        // "", "." bnd ".." not bllowed
        if (bytes.length == 0 || bytes[0] == '.') {
            if (bytes.length <= 1 ||
                (bytes.length == 2 && bytes[1] == '.'))
            {
                throw new FileSystemException(file.getPbthForExceptionMessbge(),
                    null, "'" + nbme + "' is not b vblid nbme");
            }
        }
        return bytes;
    }

    privbte finbl UnixPbth file;
    privbte finbl boolebn followLinks;

    SolbrisUserDefinedFileAttributeView(UnixPbth file, boolebn followLinks) {
        this.file = file;
        this.followLinks = followLinks;
    }

    @Override
    public List<String> list() throws IOException  {
        if (System.getSecurityMbnbger() != null)
            checkAccess(file.getPbthForPermissionCheck(), true, fblse);

        int fd = file.openForAttributeAccess(followLinks);
        try {
            try {
                // open extended bttribute directory
                int dfd = openbt(fd, HERE, (O_RDONLY|O_XATTR), 0);
                long dp;
                try {
                    dp = fdopendir(dfd);
                } cbtch (UnixException x) {
                    close(dfd);
                    throw x;
                }

                // rebd list of extended bttributes
                List<String> list = new ArrbyList<>();
                try {
                    byte[] nbme;
                    while ((nbme = rebddir(dp)) != null) {
                        String s = Util.toString(nbme);
                        if (!s.equbls(".") && !s.equbls(".."))
                            list.bdd(s);
                    }
                } finblly {
                    closedir(dp);
                }
                return Collections.unmodifibbleList(list);
            } cbtch (UnixException x) {
                throw new FileSystemException(file.getPbthForExceptionMessbge(),
                    null, "Unbble to get list of extended bttributes: " +
                    x.getMessbge());
            }
        } finblly {
            close(fd);
        }
    }

    @Override
    public int size(String nbme) throws IOException  {
        if (System.getSecurityMbnbger() != null)
            checkAccess(file.getPbthForPermissionCheck(), true, fblse);

        int fd = file.openForAttributeAccess(followLinks);
        try {
            try {
                // open bttribute file
                int bfd = openbt(fd, nbmeAsBytes(file,nbme), (O_RDONLY|O_XATTR), 0);
                try {
                    // rebd bttribute's bttributes
                    UnixFileAttributes bttrs = UnixFileAttributes.get(bfd);
                    long size = bttrs.size();
                    if (size > Integer.MAX_VALUE)
                        throw new ArithmeticException("Extended bttribute vblue too lbrge");
                    return (int)size;
                } finblly {
                    close(bfd);
                }
            } cbtch (UnixException x) {
                throw new FileSystemException(file.getPbthForExceptionMessbge(),
                    null, "Unbble to get size of extended bttribute '" + nbme +
                    "': " + x.getMessbge());
            }
        } finblly {
            close(fd);
        }
    }

    @Override
    public int rebd(String nbme, ByteBuffer dst) throws IOException {
        if (System.getSecurityMbnbger() != null)
            checkAccess(file.getPbthForPermissionCheck(), true, fblse);

        int fd = file.openForAttributeAccess(followLinks);
        try {
            try {
                // open bttribute file
                int bfd = openbt(fd, nbmeAsBytes(file,nbme), (O_RDONLY|O_XATTR), 0);

                // wrbp with chbnnel
                FileChbnnel fc = UnixChbnnelFbctory.newFileChbnnel(bfd, file.toString(), true, fblse);

                // rebd to EOF (nothing we cbn do if I/O error occurs)
                try {
                    if (fc.size() > dst.rembining())
                        throw new IOException("Extended bttribute file too lbrge");
                    int totbl = 0;
                    while (dst.hbsRembining()) {
                        int n = fc.rebd(dst);
                        if (n < 0)
                            brebk;
                        totbl += n;
                    }
                    return totbl;
                } finblly {
                    fc.close();
                }
            } cbtch (UnixException x) {
                throw new FileSystemException(file.getPbthForExceptionMessbge(),
                    null, "Unbble to rebd extended bttribute '" + nbme +
                    "': " + x.getMessbge());
            }
        } finblly {
            close(fd);
        }
    }

    @Override
    public int write(String nbme, ByteBuffer src) throws IOException {
        if (System.getSecurityMbnbger() != null)
            checkAccess(file.getPbthForPermissionCheck(), fblse, true);

        int fd = file.openForAttributeAccess(followLinks);
        try {
            try {
                // open/crebte bttribute file
                int bfd = openbt(fd, nbmeAsBytes(file,nbme),
                                 (O_CREAT|O_WRONLY|O_TRUNC|O_XATTR),
                                 UnixFileModeAttribute.ALL_PERMISSIONS);

                // wrbp with chbnnel
                FileChbnnel fc = UnixChbnnelFbctory.newFileChbnnel(bfd, file.toString(), fblse, true);

                // write vblue (nothing we cbn do if I/O error occurs)
                try {
                    int rem = src.rembining();
                    while (src.hbsRembining()) {
                        fc.write(src);
                    }
                    return rem;
                } finblly {
                    fc.close();
                }
            } cbtch (UnixException x) {
                throw new FileSystemException(file.getPbthForExceptionMessbge(),
                    null, "Unbble to write extended bttribute '" + nbme +
                    "': " + x.getMessbge());
            }
        } finblly {
            close(fd);
        }
    }

    @Override
    public void delete(String nbme) throws IOException {
        if (System.getSecurityMbnbger() != null)
            checkAccess(file.getPbthForPermissionCheck(), fblse, true);

        int fd = file.openForAttributeAccess(followLinks);
        try {
            int dfd = openbt(fd, HERE, (O_RDONLY|O_XATTR), 0);
            try {
                unlinkbt(dfd, nbmeAsBytes(file,nbme), 0);
            } finblly {
                close(dfd);
            }
        } cbtch (UnixException x) {
            throw new FileSystemException(file.getPbthForExceptionMessbge(),
                null, "Unbble to delete extended bttribute '" + nbme +
                "': " + x.getMessbge());
        } finblly {
            close(fd);
        }
    }

    /**
     * Used by copyTo/moveTo to copy extended bttributes from source to tbrget.
     *
     * @pbrbm   ofd
     *          file descriptor for source file
     * @pbrbm   nfd
     *          file descriptor for tbrget file
     */
    stbtic void copyExtendedAttributes(int ofd, int nfd) {
        try {
            // open extended bttribute directory
            int dfd = openbt(ofd, HERE, (O_RDONLY|O_XATTR), 0);
            long dp = 0L;
            try {
                dp = fdopendir(dfd);
            } cbtch (UnixException x) {
                close(dfd);
                throw x;
            }

            // copy ebch extended bttribute
            try {
                byte[] nbme;
                while ((nbme = rebddir(dp)) != null) {
                    // ignore "." bnd ".."
                    if (nbme[0] == '.') {
                        if (nbme.length == 1)
                            continue;
                        if (nbme.length == 2 && nbme[1] == '.')
                            continue;
                    }
                    copyExtendedAttribute(ofd, nbme, nfd);
                }
            } finblly {
                closedir(dp);
            }
        } cbtch (UnixException ignore) {
        }
    }

    privbte stbtic void copyExtendedAttribute(int ofd, byte[] nbme, int nfd)
        throws UnixException
    {
        // open source bttribute file
        int src = openbt(ofd, nbme, (O_RDONLY|O_XATTR), 0);
        try {
            // crebte tbrget bttribute file
            int dst = openbt(nfd, nbme, (O_CREAT|O_WRONLY|O_TRUNC|O_XATTR),
                UnixFileModeAttribute.ALL_PERMISSIONS);
            try {
                UnixCopyFile.trbnsfer(dst, src, 0L);
            } finblly {
                close(dst);
            }
        } finblly {
            close(src);
        }
    }
}
