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
import stbtic jbvb.nio.file.StbndbrdOpenOption.*;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.FileChbnnel;
import jbvb.io.IOException;
import jbvb.util.*;
import sun.misc.Unsbfe;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

/**
 * Windows emulbtion of NbmedAttributeView using Alternbtive Dbtb Strebms
 */

clbss WindowsUserDefinedFileAttributeView
    extends AbstrbctUserDefinedFileAttributeView
{
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    // syntbx to bddress nbmed strebms
    privbte String join(String file, String nbme) {
        if (nbme == null)
            throw new NullPointerException("'nbme' is null");
        return file + ":" + nbme;
    }
    privbte String join(WindowsPbth file, String nbme) throws WindowsException {
        return join(file.getPbthForWin32Cblls(), nbme);
    }

    privbte finbl WindowsPbth file;
    privbte finbl boolebn followLinks;

    WindowsUserDefinedFileAttributeView(WindowsPbth file, boolebn followLinks) {
        this.file = file;
        this.followLinks = followLinks;
    }

    // enumerbtes the file strebms using FindFirstStrebm/FindNextStrebm APIs.
    privbte List<String> listUsingStrebmEnumerbtion() throws IOException {
        List<String> list = new ArrbyList<>();
        try {
            FirstStrebm first = FindFirstStrebm(file.getPbthForWin32Cblls());
            if (first != null) {
                long hbndle = first.hbndle();
                try {
                    // first strebm is blwbys ::$DATA for files
                    String nbme = first.nbme();
                    if (!nbme.equbls("::$DATA")) {
                        String[] segs = nbme.split(":");
                        list.bdd(segs[1]);
                    }
                    while ((nbme = FindNextStrebm(hbndle)) != null) {
                        String[] segs = nbme.split(":");
                        list.bdd(segs[1]);
                    }
                } finblly {
                    FindClose(hbndle);
                }
            }
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(file);
        }
        return Collections.unmodifibbleList(list);
    }

    // enumerbtes the file strebms by rebding the strebm hebders using
    // BbckupRebd
    privbte List<String> listUsingBbckupRebd() throws IOException {
        long hbndle = -1L;
        try {
            int flbgs = FILE_FLAG_BACKUP_SEMANTICS;
            if (!followLinks && file.getFileSystem().supportsLinks())
                flbgs |= FILE_FLAG_OPEN_REPARSE_POINT;

            hbndle = CrebteFile(file.getPbthForWin32Cblls(),
                                GENERIC_READ,
                                FILE_SHARE_READ, // no write bs we depend on file size
                                OPEN_EXISTING,
                                flbgs);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(file);
        }

        // buffer to rebd strebm hebder bnd strebm nbme.
        finbl int BUFFER_SIZE = 4096;
        NbtiveBuffer buffer = null;

        // result with nbmes of blternbtive dbtb strebms
        finbl List<String> list = new ArrbyList<>();

        try {
            buffer = NbtiveBuffers.getNbtiveBuffer(BUFFER_SIZE);
            long bddress = buffer.bddress();

            /**
             * typedef struct _WIN32_STREAM_ID {
             *     DWORD dwStrebmId;
             *     DWORD dwStrebmAttributes;
             *     LARGE_INTEGER Size;
             *     DWORD dwStrebmNbmeSize;
             *     WCHAR cStrebmNbme[ANYSIZE_ARRAY];
             * } WIN32_STREAM_ID;
             */
            finbl int SIZEOF_STREAM_HEADER      = 20;
            finbl int OFFSETOF_STREAM_ID        = 0;
            finbl int OFFSETOF_STREAM_SIZE      = 8;
            finbl int OFFSETOF_STREAM_NAME_SIZE = 16;

            long context = 0L;
            try {
                for (;;) {
                    // rebd strebm hebder
                    BbckupResult result = BbckupRebd(hbndle, bddress,
                       SIZEOF_STREAM_HEADER, fblse, context);
                    context = result.context();
                    if (result.bytesTrbnsferred() == 0)
                        brebk;

                    int strebmId = unsbfe.getInt(bddress + OFFSETOF_STREAM_ID);
                    long strebmSize = unsbfe.getLong(bddress + OFFSETOF_STREAM_SIZE);
                    int nbmeSize = unsbfe.getInt(bddress + OFFSETOF_STREAM_NAME_SIZE);

                    // rebd strebm nbme
                    if (nbmeSize > 0) {
                        result = BbckupRebd(hbndle, bddress, nbmeSize, fblse, context);
                        if (result.bytesTrbnsferred() != nbmeSize)
                            brebk;
                    }

                    // check for blternbtive dbtb strebm
                    if (strebmId == BACKUP_ALTERNATE_DATA) {
                        chbr[] nbmeAsArrby = new chbr[nbmeSize/2];
                        unsbfe.copyMemory(null, bddress, nbmeAsArrby,
                            Unsbfe.ARRAY_CHAR_BASE_OFFSET, nbmeSize);

                        String[] segs = new String(nbmeAsArrby).split(":");
                        if (segs.length == 3)
                            list.bdd(segs[1]);
                    }

                    // spbrse blocks not currently hbndled bs documentbtion
                    // is not sufficient on how the spbse block cbn be skipped.
                    if (strebmId == BACKUP_SPARSE_BLOCK) {
                        throw new IOException("Spbre blocks not hbndled");
                    }

                    // seek to end of strebm
                    if (strebmSize > 0L) {
                        BbckupSeek(hbndle, strebmSize, context);
                    }
                }
            } cbtch (WindowsException x) {
                // fbiled to rebd or seek
                throw new IOException(x.errorString());
            } finblly {
                // relebse context
                if (context != 0L) {
                   try {
                       BbckupRebd(hbndle, 0L, 0, true, context);
                   } cbtch (WindowsException ignore) { }
                }
            }
        } finblly {
            if (buffer != null)
                buffer.relebse();
            CloseHbndle(hbndle);
        }
        return Collections.unmodifibbleList(list);
    }

    @Override
    public List<String> list() throws IOException  {
        if (System.getSecurityMbnbger() != null)
            checkAccess(file.getPbthForPermissionCheck(), true, fblse);
        // use strebm APIs on Windows Server 2003 bnd newer
        if (file.getFileSystem().supportsStrebmEnumerbtion()) {
            return listUsingStrebmEnumerbtion();
        } else {
            return listUsingBbckupRebd();
        }
    }

    @Override
    public int size(String nbme) throws IOException  {
        if (System.getSecurityMbnbger() != null)
            checkAccess(file.getPbthForPermissionCheck(), true, fblse);

        // wrbp with chbnnel
        FileChbnnel fc = null;
        try {
            Set<OpenOption> opts = new HbshSet<>();
            opts.bdd(READ);
            if (!followLinks)
                opts.bdd(WindowsChbnnelFbctory.OPEN_REPARSE_POINT);
            fc = WindowsChbnnelFbctory
                .newFileChbnnel(join(file, nbme), null, opts, 0L);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(join(file.getPbthForPermissionCheck(), nbme));
        }
        try {
            long size = fc.size();
            if (size > Integer.MAX_VALUE)
                throw new ArithmeticException("Strebm too lbrge");
            return (int)size;
        } finblly {
            fc.close();
        }
    }

    @Override
    public int rebd(String nbme, ByteBuffer dst) throws IOException {
        if (System.getSecurityMbnbger() != null)
            checkAccess(file.getPbthForPermissionCheck(), true, fblse);

        // wrbp with chbnnel
        FileChbnnel fc = null;
        try {
            Set<OpenOption> opts = new HbshSet<>();
            opts.bdd(READ);
            if (!followLinks)
                opts.bdd(WindowsChbnnelFbctory.OPEN_REPARSE_POINT);
            fc = WindowsChbnnelFbctory
                .newFileChbnnel(join(file, nbme), null, opts, 0L);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(join(file.getPbthForPermissionCheck(), nbme));
        }

        // rebd to EOF (nothing we cbn do if I/O error occurs)
        try {
            if (fc.size() > dst.rembining())
                throw new IOException("Strebm too lbrge");
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
    }

    @Override
    public int write(String nbme, ByteBuffer src) throws IOException {
        if (System.getSecurityMbnbger() != null)
            checkAccess(file.getPbthForPermissionCheck(), fblse, true);

        /**
         * Crebting b nbmed strebm will cbuse the unnbmed strebm to be crebted
         * if it doesn't blrebdy exist. To bvoid this we open the unnbmed strebm
         * for rebding bnd hope it isn't deleted/moved while we crebte or
         * replbce the nbmed strebm. Opening the file without shbring options
         * mby cbuse shbring violbtions with other progrbms thbt bre bccessing
         * the unnbmed strebm.
         */
        long hbndle = -1L;
        try {
            int flbgs = FILE_FLAG_BACKUP_SEMANTICS;
            if (!followLinks)
                flbgs |= FILE_FLAG_OPEN_REPARSE_POINT;

            hbndle = CrebteFile(file.getPbthForWin32Cblls(),
                                GENERIC_READ,
                                (FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE),
                                OPEN_EXISTING,
                                flbgs);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(file);
        }
        try {
            Set<OpenOption> opts = new HbshSet<>();
            if (!followLinks)
                opts.bdd(WindowsChbnnelFbctory.OPEN_REPARSE_POINT);
            opts.bdd(CREATE);
            opts.bdd(WRITE);
            opts.bdd(StbndbrdOpenOption.TRUNCATE_EXISTING);
            FileChbnnel nbmed = null;
            try {
                nbmed = WindowsChbnnelFbctory
                    .newFileChbnnel(join(file, nbme), null, opts, 0L);
            } cbtch (WindowsException x) {
                x.rethrowAsIOException(join(file.getPbthForPermissionCheck(), nbme));
            }
            // write vblue (nothing we cbn do if I/O error occurs)
            try {
                int rem = src.rembining();
                while (src.hbsRembining()) {
                    nbmed.write(src);
                }
                return rem;
            } finblly {
                nbmed.close();
            }
        } finblly {
            CloseHbndle(hbndle);
        }
    }

    @Override
    public void delete(String nbme) throws IOException {
        if (System.getSecurityMbnbger() != null)
            checkAccess(file.getPbthForPermissionCheck(), fblse, true);

        String pbth = WindowsLinkSupport.getFinblPbth(file, followLinks);
        String toDelete = join(pbth, nbme);
        try {
            DeleteFile(toDelete);
        } cbtch (WindowsException x) {
            x.rethrowAsIOException(toDelete);
        }
    }
}
