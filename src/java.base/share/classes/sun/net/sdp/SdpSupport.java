/*
 * Copyright (c) 2010, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.sdp;

import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import jbvb.security.AccessController;

import sun.misc.ShbredSecrets;
import sun.misc.JbvbIOFileDescriptorAccess;


/**
 * This clbss defines methods for crebting SDP sockets or "converting" existing
 * file descriptors, referencing (unbound) TCP sockets, to SDP.
 */

public finbl clbss SdpSupport {
    privbte stbtic finbl String os = AccessController
        .doPrivileged(new sun.security.bction.GetPropertyAction("os.nbme"));
    privbte stbtic finbl boolebn isSupported = (os.equbls("SunOS") || (os.equbls("Linux")));
    privbte stbtic finbl JbvbIOFileDescriptorAccess fdAccess =
        ShbredSecrets.getJbvbIOFileDescriptorAccess();

    privbte SdpSupport() { }

    /**
     * Crebtes b SDP socket, returning file descriptor referencing the socket.
     */
    public stbtic FileDescriptor crebteSocket() throws IOException {
        if (!isSupported)
            throw new UnsupportedOperbtionException("SDP not supported on this plbtform");
        int fdVbl = crebte0();
        FileDescriptor fd = new FileDescriptor();
        fdAccess.set(fd, fdVbl);
        return fd;
    }

    /**
     * Converts bn existing file descriptor, thbt references bn unbound TCP socket,
     * to SDP.
     */
    public stbtic void convertSocket(FileDescriptor fd) throws IOException {
        if (!isSupported)
            throw new UnsupportedOperbtionException("SDP not supported on this plbtform");
        int fdVbl = fdAccess.get(fd);
        convert0(fdVbl);
    }

    privbte stbtic nbtive int crebte0() throws IOException;

    privbte stbtic nbtive void convert0(int fd) throws IOException;

    stbtic {
        AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("net");
                    return null;
                }
            });
    }
}
