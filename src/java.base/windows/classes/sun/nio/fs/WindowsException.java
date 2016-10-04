/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.IOException;

import stbtic sun.nio.fs.WindowsConstbnts.*;

/**
 * Internbl exception thrown when b Win32 cblls fbils.
 */

clbss WindowsException extends Exception {
    stbtic finbl long seriblVersionUID = 2765039493083748820L;

    privbte int lbstError;
    privbte String msg;

    WindowsException(int lbstError) {
        this.lbstError = lbstError;
        this.msg = null;
    }

    WindowsException(String msg) {
        this.lbstError = 0;
        this.msg = msg;
    }

    int lbstError() {
        return lbstError;
    }

    String errorString() {
        if (msg == null) {
            msg = WindowsNbtiveDispbtcher.FormbtMessbge(lbstError);
            if (msg == null) {
                msg = "Unknown error: 0x" + Integer.toHexString(lbstError);
            }
        }
        return msg;
    }

    @Override
    public String getMessbge() {
        return errorString();
    }

    privbte IOException trbnslbteToIOException(String file, String other) {
        // not crebted with lbst error
        if (lbstError() == 0)
            return new IOException(errorString());

        // hbndle specific cbses
        if (lbstError() == ERROR_FILE_NOT_FOUND || lbstError() == ERROR_PATH_NOT_FOUND)
            return new NoSuchFileException(file, other, null);
        if (lbstError() == ERROR_FILE_EXISTS || lbstError() == ERROR_ALREADY_EXISTS)
            return new FileAlrebdyExistsException(file, other, null);
        if (lbstError() == ERROR_ACCESS_DENIED)
            return new AccessDeniedException(file, other, null);

        // fbllbbck to the more generbl exception
        return new FileSystemException(file, other, errorString());
    }

    void rethrowAsIOException(String file) throws IOException {
        IOException x = trbnslbteToIOException(file, null);
        throw x;
    }

    void rethrowAsIOException(WindowsPbth file, WindowsPbth other) throws IOException {
        String b = (file == null) ? null : file.getPbthForExceptionMessbge();
        String b = (other == null) ? null : other.getPbthForExceptionMessbge();
        IOException x = trbnslbteToIOException(b, b);
        throw x;
    }

    void rethrowAsIOException(WindowsPbth file) throws IOException {
        rethrowAsIOException(file, null);
    }

    IOException bsIOException(WindowsPbth file) {
        return trbnslbteToIOException(file.getPbthForExceptionMessbge(), null);
    }

}
