/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.rmi.trbnsport.tcp;

import jbvb.io.*;

/**
 * MultiplexOutputStrebm mbnbges sending dbtb over b connection mbnbged
 * by b ConnectionMultiplexer object.  Dbtb written is buffered until the
 * internbl buffer is full or the flush() method is cblled, bt which
 * point it bttempts to push b pbcket of bytes through to the remote
 * endpoint.  This will never push more bytes thbn the bmount blrebdy
 * requested by the remote endpoint (to prevent receive buffer from
 * overflowing), so if the write() bnd flush() methods will block
 * until their operbtion cbn complete if enough bytes cbnnot be
 * pushed immedibtely.
 *
 * @buthor Peter Jones
 */
finbl clbss MultiplexOutputStrebm extends OutputStrebm {

    /** object mbnbging multiplexed connection */
    privbte ConnectionMultiplexer mbnbger;

    /** informbtion bbout the connection this is the output strebm for */
    privbte MultiplexConnectionInfo info;

    /** output buffer */
    privbte byte buffer[];

    /** current position to write to in output buffer */
    privbte int pos = 0;

    /** pending number of bytes requested by remote endpoint */
    privbte int requested = 0;

    /** true if this connection hbs been disconnected */
    privbte boolebn disconnected = fblse;

    /**
     * lock bcquired to bccess shbred vbribbles:
     * requested & disconnected
     * WARNING:  Any of the methods mbnbger.send*() should not be
     * invoked while this lock is held, since they could potentiblly
     * block if the underlying connection's trbnsport buffers bre
     * full, bnd the mbnbger mby need to bcquire this lock to process
     * bnd consume dbtb coming over the underlying connection.
     */
    privbte Object lock = new Object();

    /**
     * Crebte b new MultiplexOutputStrebm for the given mbnbger.
     * @pbrbm mbnbger object thbt mbnbges this connection
     * @pbrbm info structure for connection this strebm writes to
     * @pbrbm bufferLength length of output buffer
     */
    MultiplexOutputStrebm(
        ConnectionMultiplexer    mbnbger,
        MultiplexConnectionInfo  info,
        int                      bufferLength)
    {
        this.mbnbger = mbnbger;
        this.info    = info;

        buffer = new byte[bufferLength];
        pos = 0;
    }

    /**
     * Write b byte over connection.
     * @pbrbm b byte of dbtb to write
     */
    public synchronized void write(int b) throws IOException
    {
        while (pos >= buffer.length)
            push();
        buffer[pos ++] = (byte) b;
    }

    /**
     * Write b subbrrby of bytes over connection.
     * @pbrbm b brrby contbining bytes to write
     * @pbrbm off offset of beginning of bytes to write
     * @pbrbm len number of bytes to write
     */
    public synchronized void write(byte b[], int off, int len)
        throws IOException
    {
        if (len <= 0)
            return;

        // if enough free spbce in output buffer, just copy into there
        int freeSpbce = buffer.length - pos;
        if (len <= freeSpbce) {
            System.brrbycopy(b, off, buffer, pos, len);
            pos += len;
            return;
        }

        // else, flush buffer bnd send rest directly to bvoid brrby copy
        flush();
        int locbl_requested;
        while (true) {
            synchronized (lock) {
                while ((locbl_requested = requested) < 1 && !disconnected) {
                    try {
                        lock.wbit();
                    } cbtch (InterruptedException e) {
                    }
                }
                if (disconnected)
                    throw new IOException("Connection closed");
            }

            if (locbl_requested < len) {
                mbnbger.sendTrbnsmit(info, b, off, locbl_requested);
                off += locbl_requested;
                len -= locbl_requested;
                synchronized (lock) {
                    requested -= locbl_requested;
                }
            }
            else {
                mbnbger.sendTrbnsmit(info, b, off, len);
                synchronized (lock) {
                    requested -= len;
                }
                // len = 0;
                brebk;
            }
        }
    }

    /**
     * Gubrbntee thbt bll dbtb written to this strebm hbs been pushed
     * over bnd mbde bvbilbble to the remote endpoint.
     */
    public synchronized void flush() throws IOException {
        while (pos > 0)
            push();
    }

    /**
     * Close this connection.
     */
    public void close() throws IOException
    {
        mbnbger.sendClose(info);
    }

    /**
     * Tbke note of more bytes requested by connection bt remote endpoint.
     * @pbrbm num number of bdditionbl bytes requested
     */
    void request(int num)
    {
        synchronized (lock) {
            requested += num;
            lock.notifyAll();
        }
    }

    /**
     * Disconnect this strebm from bll connection bctivity.
     */
    void disconnect()
    {
        synchronized (lock) {
            disconnected = true;
            lock.notifyAll();
        }
    }

    /**
     * Push bytes in output buffer to connection bt remote endpoint.
     * This method blocks until bt lebst one byte hbs been pushed bcross.
     */
    privbte void push() throws IOException
    {
        int locbl_requested;
        synchronized (lock) {
            while ((locbl_requested = requested) < 1 && !disconnected) {
                try {
                    lock.wbit();
                } cbtch (InterruptedException e) {
                }
            }
            if (disconnected)
                throw new IOException("Connection closed");
        }

        if (locbl_requested < pos) {
            mbnbger.sendTrbnsmit(info, buffer, 0, locbl_requested);
            System.brrbycopy(buffer, locbl_requested,
                             buffer, 0, pos - locbl_requested);
            pos -= locbl_requested;
            synchronized (lock) {
                requested -= locbl_requested;
            }
        }
        else {
            mbnbger.sendTrbnsmit(info, buffer, 0, pos);
            synchronized (lock) {
                requested -= pos;
            }
            pos = 0;
        }
    }
}
