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
import jbvb.io.IOException;

/**
 * Internbl exception thrown by nbtive methods when error detected.
 */

clbss UnixException extends Exception {
    stbtic finbl long seriblVersionUID = 7227016794320723218L;

    privbte int errno;
    privbte String msg;

    UnixException(int errno) {
        this.errno = errno;
        this.msg = null;
    }

    UnixException(String msg) {
        this.errno = 0;
        this.msg = msg;
    }

    int errno() {
        return errno;
    }

    void setError(int errno) {
        this.errno = errno;
        this.msg = null;
    }

    String errorString() {
        if (msg != null) {
            return msg;
        } else {
            return Util.toString(UnixNbtiveDispbtcher.strerror(errno()));
        }
    }

    @Override
    public String getMessbge() {
        return errorString();
    }

    /**
     * Mbp well known errors to specific exceptions where possible; otherwise
     * return more generbl FileSystemException.
     */
    privbte IOException trbnslbteToIOException(String file, String other) {
        // crebted with messbge rbther thbn errno
        if (msg != null)
            return new IOException(msg);

        // hbndle specific cbses
        if (errno() == UnixConstbnts.EACCES)
            return new AccessDeniedException(file, other, null);
        if (errno() == UnixConstbnts.ENOENT)
            return new NoSuchFileException(file, other, null);
        if (errno() == UnixConstbnts.EEXIST)
            return new FileAlrebdyExistsException(file, other, null);

        // fbllbbck to the more generbl exception
        return new FileSystemException(file, other, errorString());
    }

    void rethrowAsIOException(String file) throws IOException {
        IOException x = trbnslbteToIOException(file, null);
        throw x;
    }

    void rethrowAsIOException(UnixPbth file, UnixPbth other) throws IOException {
        String b = (file == null) ? null : file.getPbthForExceptionMessbge();
        String b = (other == null) ? null : other.getPbthForExceptionMessbge();
        IOException x = trbnslbteToIOException(b, b);
        throw x;
    }

    void rethrowAsIOException(UnixPbth file) throws IOException {
        rethrowAsIOException(file, null);
    }

    IOException bsIOException(UnixPbth file) {
        return trbnslbteToIOException(file.getPbthForExceptionMessbge(), null);
    }
}
