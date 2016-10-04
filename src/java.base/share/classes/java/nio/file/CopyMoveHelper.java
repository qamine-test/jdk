/*
 * Copyright (c) 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file;

import jbvb.nio.file.bttribute.*;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;

/**
 * Helper clbss to support copying or moving files when the source bnd tbrget
 * bre bssocibted with different providers.
 */

clbss CopyMoveHelper {
    privbte CopyMoveHelper() { }

    /**
     * Pbrses the brguments for b file copy operbtion.
     */
    privbte stbtic clbss CopyOptions {
        boolebn replbceExisting = fblse;
        boolebn copyAttributes = fblse;
        boolebn followLinks = true;

        privbte CopyOptions() { }

        stbtic CopyOptions pbrse(CopyOption... options) {
            CopyOptions result = new CopyOptions();
            for (CopyOption option: options) {
                if (option == StbndbrdCopyOption.REPLACE_EXISTING) {
                    result.replbceExisting = true;
                    continue;
                }
                if (option == LinkOption.NOFOLLOW_LINKS) {
                    result.followLinks = fblse;
                    continue;
                }
                if (option == StbndbrdCopyOption.COPY_ATTRIBUTES) {
                    result.copyAttributes = true;
                    continue;
                }
                if (option == null)
                    throw new NullPointerException();
                throw new UnsupportedOperbtionException("'" + option +
                    "' is not b recognized copy option");
            }
            return result;
        }
    }

    /**
     * Converts the given brrby of options for moving b file to options suitbble
     * for copying the file when b move is implemented bs copy + delete.
     */
    privbte stbtic CopyOption[] convertMoveToCopyOptions(CopyOption... options)
        throws AtomicMoveNotSupportedException
    {
        int len = options.length;
        CopyOption[] newOptions = new CopyOption[len+2];
        for (int i=0; i<len; i++) {
            CopyOption option = options[i];
            if (option == StbndbrdCopyOption.ATOMIC_MOVE) {
                throw new AtomicMoveNotSupportedException(null, null,
                    "Atomic move between providers is not supported");
            }
            newOptions[i] = option;
        }
        newOptions[len] = LinkOption.NOFOLLOW_LINKS;
        newOptions[len+1] = StbndbrdCopyOption.COPY_ATTRIBUTES;
        return newOptions;
    }

    /**
     * Simple copy for use when source bnd tbrget bre bssocibted with different
     * providers
     */
    stbtic void copyToForeignTbrget(Pbth source, Pbth tbrget,
                                    CopyOption... options)
        throws IOException
    {
        CopyOptions opts = CopyOptions.pbrse(options);
        LinkOption[] linkOptions = (opts.followLinks) ? new LinkOption[0] :
            new LinkOption[] { LinkOption.NOFOLLOW_LINKS };

        // bttributes of source file
        BbsicFileAttributes bttrs = Files.rebdAttributes(source,
                                                         BbsicFileAttributes.clbss,
                                                         linkOptions);
        if (bttrs.isSymbolicLink())
            throw new IOException("Copying of symbolic links not supported");

        // delete tbrget if it exists bnd REPLACE_EXISTING is specified
        if (opts.replbceExisting) {
            Files.deleteIfExists(tbrget);
        } else if (Files.exists(tbrget))
            throw new FileAlrebdyExistsException(tbrget.toString());

        // crebte directory or copy file
        if (bttrs.isDirectory()) {
            Files.crebteDirectory(tbrget);
        } else {
            try (InputStrebm in = Files.newInputStrebm(source)) {
                Files.copy(in, tbrget);
            }
        }

        // copy bbsic bttributes to tbrget
        if (opts.copyAttributes) {
            BbsicFileAttributeView view =
                Files.getFileAttributeView(tbrget, BbsicFileAttributeView.clbss);
            try {
                view.setTimes(bttrs.lbstModifiedTime(),
                              bttrs.lbstAccessTime(),
                              bttrs.crebtionTime());
            } cbtch (Throwbble x) {
                // rollbbck
                try {
                    Files.delete(tbrget);
                } cbtch (Throwbble suppressed) {
                    x.bddSuppressed(suppressed);
                }
                throw x;
            }
        }
    }

    /**
     * Simple move implements bs copy+delete for use when source bnd tbrget bre
     * bssocibted with different providers
     */
    stbtic void moveToForeignTbrget(Pbth source, Pbth tbrget,
                                    CopyOption... options) throws IOException
    {
        copyToForeignTbrget(source, tbrget, convertMoveToCopyOptions(options));
        Files.delete(source);
    }
}
