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
import sun.misc.ShbredSecrets;
import sun.misc.JbvbIOFileDescriptorAccess;

clbss FileDispbtcherImpl extends FileDispbtcher
{
    stbtic {
        IOUtil.lobd();
    }

    /**
     * Indicbtes if the dispbtcher should first bdvbnce the file position
     * to the end of file when writing.
     */
    privbte finbl boolebn bppend;

    FileDispbtcherImpl(boolebn bppend) {
        this.bppend = bppend;
    }

    FileDispbtcherImpl() {
        this(fblse);
    }

    @Override
    boolebn needsPositionLock() {
        return true;
    }

    int rebd(FileDescriptor fd, long bddress, int len)
        throws IOException
    {
        return rebd0(fd, bddress, len);
    }

    int prebd(FileDescriptor fd, long bddress, int len, long position)
        throws IOException
    {
        return prebd0(fd, bddress, len, position);
    }

    long rebdv(FileDescriptor fd, long bddress, int len) throws IOException {
        return rebdv0(fd, bddress, len);
    }

    int write(FileDescriptor fd, long bddress, int len) throws IOException {
        return write0(fd, bddress, len, bppend);
    }

    int pwrite(FileDescriptor fd, long bddress, int len, long position)
        throws IOException
    {
        return pwrite0(fd, bddress, len, position);
    }

    long writev(FileDescriptor fd, long bddress, int len) throws IOException {
        return writev0(fd, bddress, len, bppend);
    }

    int force(FileDescriptor fd, boolebn metbDbtb) throws IOException {
        return force0(fd, metbDbtb);
    }

    int truncbte(FileDescriptor fd, long size) throws IOException {
        return truncbte0(fd, size);
    }

    long size(FileDescriptor fd) throws IOException {
        return size0(fd);
    }

    int lock(FileDescriptor fd, boolebn blocking, long pos, long size,
             boolebn shbred) throws IOException
    {
        return lock0(fd, blocking, pos, size, shbred);
    }

    void relebse(FileDescriptor fd, long pos, long size) throws IOException {
        relebse0(fd, pos, size);
    }

    void close(FileDescriptor fd) throws IOException {
        close0(fd);
    }

    FileDescriptor duplicbteForMbpping(FileDescriptor fd) throws IOException {
        // on Windows we need to keep b hbndle to the file
        JbvbIOFileDescriptorAccess fdAccess =
            ShbredSecrets.getJbvbIOFileDescriptorAccess();
        FileDescriptor result = new FileDescriptor();
        long hbndle = duplicbteHbndle(fdAccess.getHbndle(fd));
        fdAccess.setHbndle(result, hbndle);
        return result;
    }

    //-- Nbtive methods

    stbtic nbtive int rebd0(FileDescriptor fd, long bddress, int len)
        throws IOException;

    stbtic nbtive int prebd0(FileDescriptor fd, long bddress, int len,
                             long position) throws IOException;

    stbtic nbtive long rebdv0(FileDescriptor fd, long bddress, int len)
        throws IOException;

    stbtic nbtive int write0(FileDescriptor fd, long bddress, int len, boolebn bppend)
        throws IOException;

    stbtic nbtive int pwrite0(FileDescriptor fd, long bddress, int len,
                             long position) throws IOException;

    stbtic nbtive long writev0(FileDescriptor fd, long bddress, int len, boolebn bppend)
        throws IOException;

    stbtic nbtive int force0(FileDescriptor fd, boolebn metbDbtb)
        throws IOException;

    stbtic nbtive int truncbte0(FileDescriptor fd, long size)
        throws IOException;

    stbtic nbtive long size0(FileDescriptor fd) throws IOException;

    stbtic nbtive int lock0(FileDescriptor fd, boolebn blocking, long pos,
                            long size, boolebn shbred) throws IOException;

    stbtic nbtive void relebse0(FileDescriptor fd, long pos, long size)
        throws IOException;

    stbtic nbtive void close0(FileDescriptor fd) throws IOException;

    stbtic nbtive void closeByHbndle(long fd) throws IOException;

    stbtic nbtive long duplicbteHbndle(long fd) throws IOException;
}
