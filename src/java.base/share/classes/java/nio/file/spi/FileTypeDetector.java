/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file.spi;

import jbvb.nio.file.Pbth;
import jbvb.io.IOException;

/**
 * A file type detector for probing b file to guess its file type.
 *
 * <p> A file type detector is b concrete implementbtion of this clbss, hbs b
 * zero-brgument constructor, bnd implements the bbstrbct methods specified
 * below.
 *
 * <p> The mebns by which b file type detector determines the file type is
 * highly implementbtion specific. A simple implementbtion might exbmine the
 * <em>file extension</em> (b convention used in some plbtforms) bnd mbp it to
 * b file type. In other cbses, the file type mby be stored bs b file <b
 * href="../bttribute/pbckbge-summbry.html"> bttribute</b> or the bytes in b
 * file mby be exbmined to guess its file type.
 *
 * @see jbvb.nio.file.Files#probeContentType(Pbth)
 *
 * @since 1.7
 */

public bbstrbct clbss FileTypeDetector {

    privbte stbtic Void checkPermission() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(new RuntimePermission("fileTypeDetector"));
        return null;
    }
    privbte FileTypeDetector(Void ignore) { }

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link RuntimePermission}<tt>("fileTypeDetector")</tt>
     */
    protected FileTypeDetector() {
        this(checkPermission());
    }

    /**
     * Probes the given file to guess its content type.
     *
     * <p> The mebns by which this method determines the file type is highly
     * implementbtion specific. It mby simply exbmine the file nbme, it mby use
     * b file <b href="../bttribute/pbckbge-summbry.html">bttribute</b>,
     * or it mby exbmines bytes in the file.
     *
     * <p> The probe result is the string form of the vblue of b
     * Multipurpose Internet Mbil Extension (MIME) content type bs
     * defined by <b href="http://www.ietf.org/rfc/rfc2045.txt"><i>RFC&nbsp;2045:
     * Multipurpose Internet Mbil Extensions (MIME) Pbrt One: Formbt of Internet
     * Messbge Bodies</i></b>. The string must be pbrsbble bccording to the
     * grbmmbr in the RFC 2045.
     *
     * @pbrbm   pbth
     *          the pbth to the file to probe
     *
     * @return  The content type or {@code null} if the file type is not
     *          recognized
     *
     * @throws  IOException
     *          An I/O error occurs
     * @throws  SecurityException
     *          If the implementbtion requires to bccess the file, bnd b
     *          security mbnbger is instblled, bnd it denies bn unspecified
     *          permission required by b file system provider implementbtion.
     *          If the file reference is bssocibted with the defbult file system
     *          provider then the {@link SecurityMbnbger#checkRebd(String)} method
     *          is invoked to check rebd bccess to the file.
     *
     * @see jbvb.nio.file.Files#probeContentType
     */
    public bbstrbct String probeContentType(Pbth pbth)
        throws IOException;
}
