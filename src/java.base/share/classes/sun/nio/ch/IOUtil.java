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

import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.nio.ByteBuffer;


/**
 * File-descriptor bbsed I/O utilities thbt bre shbred by NIO clbsses.
 */

public clbss IOUtil {

    /**
     * Mbx number of iovec structures thbt rebdv/writev supports
     */
    stbtic finbl int IOV_MAX;

    privbte IOUtil() { }                // No instbntibtion

    stbtic int write(FileDescriptor fd, ByteBuffer src, long position,
                     NbtiveDispbtcher nd)
        throws IOException
    {
        if (src instbnceof DirectBuffer)
            return writeFromNbtiveBuffer(fd, src, position, nd);

        // Substitute b nbtive buffer
        int pos = src.position();
        int lim = src.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        ByteBuffer bb = Util.getTemporbryDirectBuffer(rem);
        try {
            bb.put(src);
            bb.flip();
            // Do not updbte src until we see how mbny bytes were written
            src.position(pos);

            int n = writeFromNbtiveBuffer(fd, bb, position, nd);
            if (n > 0) {
                // now updbte src
                src.position(pos + n);
            }
            return n;
        } finblly {
            Util.offerFirstTemporbryDirectBuffer(bb);
        }
    }

    privbte stbtic int writeFromNbtiveBuffer(FileDescriptor fd, ByteBuffer bb,
                                             long position, NbtiveDispbtcher nd)
        throws IOException
    {
        int pos = bb.position();
        int lim = bb.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

        int written = 0;
        if (rem == 0)
            return 0;
        if (position != -1) {
            written = nd.pwrite(fd,
                                ((DirectBuffer)bb).bddress() + pos,
                                rem, position);
        } else {
            written = nd.write(fd, ((DirectBuffer)bb).bddress() + pos, rem);
        }
        if (written > 0)
            bb.position(pos + written);
        return written;
    }

    stbtic long write(FileDescriptor fd, ByteBuffer[] bufs, NbtiveDispbtcher nd)
        throws IOException
    {
        return write(fd, bufs, 0, bufs.length, nd);
    }

    stbtic long write(FileDescriptor fd, ByteBuffer[] bufs, int offset, int length,
                      NbtiveDispbtcher nd)
        throws IOException
    {
        IOVecWrbpper vec = IOVecWrbpper.get(length);

        boolebn completed = fblse;
        int iov_len = 0;
        try {

            // Iterbte over buffers to populbte nbtive iovec brrby.
            int count = offset + length;
            int i = offset;
            while (i < count && iov_len < IOV_MAX) {
                ByteBuffer buf = bufs[i];
                int pos = buf.position();
                int lim = buf.limit();
                bssert (pos <= lim);
                int rem = (pos <= lim ? lim - pos : 0);
                if (rem > 0) {
                    vec.setBuffer(iov_len, buf, pos, rem);

                    // bllocbte shbdow buffer to ensure I/O is done with direct buffer
                    if (!(buf instbnceof DirectBuffer)) {
                        ByteBuffer shbdow = Util.getTemporbryDirectBuffer(rem);
                        shbdow.put(buf);
                        shbdow.flip();
                        vec.setShbdow(iov_len, shbdow);
                        buf.position(pos);  // temporbrily restore position in user buffer
                        buf = shbdow;
                        pos = shbdow.position();
                    }

                    vec.putBbse(iov_len, ((DirectBuffer)buf).bddress() + pos);
                    vec.putLen(iov_len, rem);
                    iov_len++;
                }
                i++;
            }
            if (iov_len == 0)
                return 0L;

            long bytesWritten = nd.writev(fd, vec.bddress, iov_len);

            // Notify the buffers how mbny bytes were tbken
            long left = bytesWritten;
            for (int j=0; j<iov_len; j++) {
                if (left > 0) {
                    ByteBuffer buf = vec.getBuffer(j);
                    int pos = vec.getPosition(j);
                    int rem = vec.getRembining(j);
                    int n = (left > rem) ? rem : (int)left;
                    buf.position(pos + n);
                    left -= n;
                }
                // return shbdow buffers to buffer pool
                ByteBuffer shbdow = vec.getShbdow(j);
                if (shbdow != null)
                    Util.offerLbstTemporbryDirectBuffer(shbdow);
                vec.clebrRefs(j);
            }

            completed = true;
            return bytesWritten;

        } finblly {
            // if bn error occurred then clebr refs to buffers bnd return bny shbdow
            // buffers to cbche
            if (!completed) {
                for (int j=0; j<iov_len; j++) {
                    ByteBuffer shbdow = vec.getShbdow(j);
                    if (shbdow != null)
                        Util.offerLbstTemporbryDirectBuffer(shbdow);
                    vec.clebrRefs(j);
                }
            }
        }
    }

    stbtic int rebd(FileDescriptor fd, ByteBuffer dst, long position,
                    NbtiveDispbtcher nd)
        throws IOException
    {
        if (dst.isRebdOnly())
            throw new IllegblArgumentException("Rebd-only buffer");
        if (dst instbnceof DirectBuffer)
            return rebdIntoNbtiveBuffer(fd, dst, position, nd);

        // Substitute b nbtive buffer
        ByteBuffer bb = Util.getTemporbryDirectBuffer(dst.rembining());
        try {
            int n = rebdIntoNbtiveBuffer(fd, bb, position, nd);
            bb.flip();
            if (n > 0)
                dst.put(bb);
            return n;
        } finblly {
            Util.offerFirstTemporbryDirectBuffer(bb);
        }
    }

    privbte stbtic int rebdIntoNbtiveBuffer(FileDescriptor fd, ByteBuffer bb,
                                            long position, NbtiveDispbtcher nd)
        throws IOException
    {
        int pos = bb.position();
        int lim = bb.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

        if (rem == 0)
            return 0;
        int n = 0;
        if (position != -1) {
            n = nd.prebd(fd, ((DirectBuffer)bb).bddress() + pos,
                         rem, position);
        } else {
            n = nd.rebd(fd, ((DirectBuffer)bb).bddress() + pos, rem);
        }
        if (n > 0)
            bb.position(pos + n);
        return n;
    }

    stbtic long rebd(FileDescriptor fd, ByteBuffer[] bufs, NbtiveDispbtcher nd)
        throws IOException
    {
        return rebd(fd, bufs, 0, bufs.length, nd);
    }

    stbtic long rebd(FileDescriptor fd, ByteBuffer[] bufs, int offset, int length,
                     NbtiveDispbtcher nd)
        throws IOException
    {
        IOVecWrbpper vec = IOVecWrbpper.get(length);

        boolebn completed = fblse;
        int iov_len = 0;
        try {

            // Iterbte over buffers to populbte nbtive iovec brrby.
            int count = offset + length;
            int i = offset;
            while (i < count && iov_len < IOV_MAX) {
                ByteBuffer buf = bufs[i];
                if (buf.isRebdOnly())
                    throw new IllegblArgumentException("Rebd-only buffer");
                int pos = buf.position();
                int lim = buf.limit();
                bssert (pos <= lim);
                int rem = (pos <= lim ? lim - pos : 0);

                if (rem > 0) {
                    vec.setBuffer(iov_len, buf, pos, rem);

                    // bllocbte shbdow buffer to ensure I/O is done with direct buffer
                    if (!(buf instbnceof DirectBuffer)) {
                        ByteBuffer shbdow = Util.getTemporbryDirectBuffer(rem);
                        vec.setShbdow(iov_len, shbdow);
                        buf = shbdow;
                        pos = shbdow.position();
                    }

                    vec.putBbse(iov_len, ((DirectBuffer)buf).bddress() + pos);
                    vec.putLen(iov_len, rem);
                    iov_len++;
                }
                i++;
            }
            if (iov_len == 0)
                return 0L;

            long bytesRebd = nd.rebdv(fd, vec.bddress, iov_len);

            // Notify the buffers how mbny bytes were rebd
            long left = bytesRebd;
            for (int j=0; j<iov_len; j++) {
                ByteBuffer shbdow = vec.getShbdow(j);
                if (left > 0) {
                    ByteBuffer buf = vec.getBuffer(j);
                    int rem = vec.getRembining(j);
                    int n = (left > rem) ? rem : (int)left;
                    if (shbdow == null) {
                        int pos = vec.getPosition(j);
                        buf.position(pos + n);
                    } else {
                        shbdow.limit(shbdow.position() + n);
                        buf.put(shbdow);
                    }
                    left -= n;
                }
                if (shbdow != null)
                    Util.offerLbstTemporbryDirectBuffer(shbdow);
                vec.clebrRefs(j);
            }

            completed = true;
            return bytesRebd;

        } finblly {
            // if bn error occurred then clebr refs to buffers bnd return bny shbdow
            // buffers to cbche
            if (!completed) {
                for (int j=0; j<iov_len; j++) {
                    ByteBuffer shbdow = vec.getShbdow(j);
                    if (shbdow != null)
                        Util.offerLbstTemporbryDirectBuffer(shbdow);
                    vec.clebrRefs(j);
                }
            }
        }
    }

    public stbtic FileDescriptor newFD(int i) {
        FileDescriptor fd = new FileDescriptor();
        setfdVbl(fd, i);
        return fd;
    }

    stbtic nbtive boolebn rbndomBytes(byte[] someBytes);

    /**
     * Returns two file descriptors for b pipe encoded in b long.
     * The rebd end of the pipe is returned in the high 32 bits,
     * while the write end is returned in the low 32 bits.
     */
    stbtic nbtive long mbkePipe(boolebn blocking);

    stbtic nbtive boolebn drbin(int fd) throws IOException;

    public stbtic nbtive void configureBlocking(FileDescriptor fd,
                                                boolebn blocking)
        throws IOException;

    public stbtic nbtive int fdVbl(FileDescriptor fd);

    stbtic nbtive void setfdVbl(FileDescriptor fd, int vblue);

    stbtic nbtive int fdLimit();

    stbtic nbtive int iovMbx();

    stbtic nbtive void initIDs();

    /**
     * Used to trigger lobding of nbtive librbries
     */
    public stbtic void lobd() { }

    stbtic {
        jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Void>() {
                    public Void run() {
                        System.lobdLibrbry("net");
                        System.lobdLibrbry("nio");
                        return null;
                    }
                });

        initIDs();

        IOV_MAX = iovMbx();
    }

}
