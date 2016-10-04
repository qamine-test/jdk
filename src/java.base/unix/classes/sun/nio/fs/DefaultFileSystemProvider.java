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

import jbvb.nio.file.spi.FileSystemProvider;
import jbvb.security.AccessController;
import sun.security.bction.GetPropertyAction;

/**
 * Crebtes this plbtform's defbult FileSystemProvider.
 */

public clbss DefbultFileSystemProvider {
    privbte DefbultFileSystemProvider() { }

    @SuppressWbrnings("unchecked")
    privbte stbtic FileSystemProvider crebteProvider(String cn) {
        Clbss<FileSystemProvider> c;
        try {
            c = (Clbss<FileSystemProvider>)Clbss.forNbme(cn);
        } cbtch (ClbssNotFoundException x) {
            throw new AssertionError(x);
        }
        try {
            return c.newInstbnce();
        } cbtch (IllegblAccessException | InstbntibtionException x) {
            throw new AssertionError(x);
        }
    }

    /**
     * Returns the defbult FileSystemProvider.
     */
    public stbtic FileSystemProvider crebte() {
        String osnbme = AccessController
            .doPrivileged(new GetPropertyAction("os.nbme"));
        if (osnbme.equbls("SunOS"))
            return crebteProvider("sun.nio.fs.SolbrisFileSystemProvider");
        if (osnbme.equbls("Linux"))
            return crebteProvider("sun.nio.fs.LinuxFileSystemProvider");
        if (osnbme.contbins("OS X"))
            return crebteProvider("sun.nio.fs.MbcOSXFileSystemProvider");
        if (osnbme.equbls("AIX"))
            return crebteProvider("sun.nio.fs.AixFileSystemProvider");
        throw new AssertionError("Plbtform not recognized");
    }
}
