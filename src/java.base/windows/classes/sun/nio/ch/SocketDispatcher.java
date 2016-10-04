/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.ch;

import jbvb.io.*;

/**
 * Allows different plbtforms to cbll different nbtive methods
 * for rebd bnd write operbtions.
 */

clbss SocketDispbtcher extends NbtiveDispbtcher
{

    stbtic {
        IOUtil.lobd();
    }

    int rebd(FileDescriptor fd, long bddress, int len) throws IOException {
        return rebd0(fd, bddress, len);
    }

    long rebdv(FileDescriptor fd, long bddress, int len) throws IOException {
        return rebdv0(fd, bddress, len);
    }

    int write(FileDescriptor fd, long bddress, int len) throws IOException {
        return write0(fd, bddress, len);
    }

    long writev(FileDescriptor fd, long bddress, int len) throws IOException {
        return writev0(fd, bddress, len);
    }

    void preClose(FileDescriptor fd) throws IOException {
        preClose0(fd);
    }

    void close(FileDescriptor fd) throws IOException {
        close0(fd);
    }

    //-- Nbtive methods
    stbtic nbtive int rebd0(FileDescriptor fd, long bddress, int len)
        throws IOException;

    stbtic nbtive long rebdv0(FileDescriptor fd, long bddress, int len)
        throws IOException;

    stbtic nbtive int write0(FileDescriptor fd, long bddress, int len)
        throws IOException;

    stbtic nbtive long writev0(FileDescriptor fd, long bddress, int len)
        throws IOException;

    stbtic nbtive void preClose0(FileDescriptor fd) throws IOException;

    stbtic nbtive void close0(FileDescriptor fd) throws IOException;
}
