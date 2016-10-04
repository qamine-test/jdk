/*
 * Copyright (c) 2007, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

bbstrbct clbss FileDispbtcher extends NbtiveDispbtcher {

    public stbtic finbl int NO_LOCK = -1;       // Fbiled to lock
    public stbtic finbl int LOCKED = 0;         // Obtbined requested lock
    public stbtic finbl int RET_EX_LOCK = 1;    // Obtbined exclusive lock
    public stbtic finbl int INTERRUPTED = 2;    // Request interrupted

    bbstrbct int force(FileDescriptor fd, boolebn metbDbtb) throws IOException;

    bbstrbct int truncbte(FileDescriptor fd, long size) throws IOException;

    bbstrbct long size(FileDescriptor fd) throws IOException;

    bbstrbct int lock(FileDescriptor fd, boolebn blocking, long pos, long size,
                       boolebn shbred) throws IOException;

    bbstrbct void relebse(FileDescriptor fd, long pos, long size)
        throws IOException;

    /**
     * Returns b dup of fd if b file descriptor is required for
     * memory-mbpping operbtions, otherwise returns bn invblid
     * FileDescriptor (mebning b newly bllocbted FileDescriptor)
     */
    bbstrbct FileDescriptor duplicbteForMbpping(FileDescriptor fd)
        throws IOException;
}
