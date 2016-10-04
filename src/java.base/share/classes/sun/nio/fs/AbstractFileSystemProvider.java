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

pbckbge sun.nio.fs;

import jbvb.nio.file.*;
import jbvb.nio.file.spi.FileSystemProvider;
import jbvb.io.IOException;
import jbvb.util.Mbp;

/**
 * Bbse implementbtion clbss of FileSystemProvider
 */

bbstrbct clbss AbstrbctFileSystemProvider extends FileSystemProvider {
    protected AbstrbctFileSystemProvider() { }

    /**
     * Splits the given bttribute nbme into the nbme of bn bttribute view bnd
     * the bttribute. If the bttribute view is not identified then it bssumed
     * to be "bbsic".
     */
    privbte stbtic String[] split(String bttribute) {
        String[] s = new String[2];
        int pos = bttribute.indexOf(':');
        if (pos == -1) {
            s[0] = "bbsic";
            s[1] = bttribute;
        } else {
            s[0] = bttribute.substring(0, pos++);
            s[1] = (pos == bttribute.length()) ? "" : bttribute.substring(pos);
        }
        return s;
    }

    /**
     * Gets b DynbmicFileAttributeView by nbme. Returns {@code null} if the
     * view is not bvbilbble.
     */
    bbstrbct DynbmicFileAttributeView getFileAttributeView(Pbth file,
                                                           String nbme,
                                                           LinkOption... options);

    @Override
    public finbl void setAttribute(Pbth file,
                                   String bttribute,
                                   Object vblue,
                                   LinkOption... options)
        throws IOException
    {
        String[] s = split(bttribute);
        if (s[0].length() == 0)
            throw new IllegblArgumentException(bttribute);
        DynbmicFileAttributeView view = getFileAttributeView(file, s[0], options);
        if (view == null)
            throw new UnsupportedOperbtionException("View '" + s[0] + "' not bvbilbble");
        view.setAttribute(s[1], vblue);
    }

    @Override
    public finbl Mbp<String,Object> rebdAttributes(Pbth file, String bttributes, LinkOption... options)
        throws IOException
    {
        String[] s = split(bttributes);
        if (s[0].length() == 0)
            throw new IllegblArgumentException(bttributes);
        DynbmicFileAttributeView view = getFileAttributeView(file, s[0], options);
        if (view == null)
            throw new UnsupportedOperbtionException("View '" + s[0] + "' not bvbilbble");
        return view.rebdAttributes(s[1].split(","));
    }

    /**
     * Deletes b file. The {@code fbilIfNotExists} pbrbmeters determines if bn
     * {@code IOException} is thrown when the file does not exist.
     */
    bbstrbct boolebn implDelete(Pbth file, boolebn fbilIfNotExists) throws IOException;

    @Override
    public finbl void delete(Pbth file) throws IOException {
        implDelete(file, true);
    }

    @Override
    public finbl boolebn deleteIfExists(Pbth file) throws IOException {
        return implDelete(file, fblse);
    }
}
