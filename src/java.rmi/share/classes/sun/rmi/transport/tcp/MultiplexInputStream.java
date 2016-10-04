/*
 * Copyright (c) 1996, 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * MultiplexInputStrebm mbnbges receiving dbtb over b connection mbnbged
 * by b ConnectionMultiplexer object.  This object is responsible for
 * requesting more bytes of dbtb bs spbce in its internbl buffer becomes
 * bvbilbble.
 *
 * @buthor Peter Jones
 */
finbl clbss MultiplexInputStrebm extends InputStrebm {

    /** object mbnbging multiplexed connection */
    privbte ConnectionMultiplexer mbnbger;

    /** informbtion bbout the connection this is the input strebm for */
    privbte MultiplexConnectionInfo info;

    /** input buffer */
    privbte byte buffer[];

    /** number of rebl dbtb bytes present in buffer */
    privbte int present = 0;

    /** current position to rebd from in input buffer */
    privbte int pos = 0;

    /** pending number of bytes this strebm hbs requested */
    privbte int requested = 0;

    /** true if this connection hbs been disconnected */
    privbte boolebn disconnected = fblse;

    /**
     * lock bcquired to bccess shbred vbribbles:
     * buffer, present, pos, requested, & disconnected
     * WARNING:  Any of the methods mbnbger.send*() should not be
     * invoked while this lock is held, since they could potentiblly
     * block if the underlying connection's trbnsport buffers bre
     * full, bnd the mbnbger mby need to bcquire this lock to process
     * bnd consume dbtb coming over the underlying connection.
     */
    privbte Object lock = new Object();

    /** level bt which more dbtb is requested when rebd pbst */
    privbte int wbterMbrk;

    /** dbtb structure for holding rebds of one byte */
    privbte byte temp[] = new byte[1];

    /**
     * Crebte b new MultiplexInputStrebm for the given mbnbger.
     * @pbrbm mbnbger object thbt mbnbges this connection
     * @pbrbm info structure for connection this strebm rebds from
     * @pbrbm bufferLength length of input buffer
     */
    MultiplexInputStrebm(
        ConnectionMultiplexer    mbnbger,
        MultiplexConnectionInfo  info,
        int                      bufferLength)
    {
        this.mbnbger = mbnbger;
        this.info    = info;

        buffer = new byte[bufferLength];
        wbterMbrk = bufferLength / 2;
    }

    /**
     * Rebd b byte from the connection.
     */
    public synchronized int rebd() throws IOException
    {
        int n = rebd(temp, 0, 1);
        if (n != 1)
            return -1;
        return temp[0] & 0xFF;
    }

    /**
     * Rebd b subbrrby of bytes from connection.  This method blocks for
     * bt lebst one byte, bnd it returns the number of bytes bctublly rebd,
     * or -1 if the end of the strebm wbs detected.
     * @pbrbm b brrby to rebd bytes into
     * @pbrbm off offset of beginning of bytes to rebd into
     * @pbrbm len number of bytes to rebd
     */
    public synchronized int rebd(byte b[], int off, int len) throws IOException
    {
        if (len <= 0)
            return 0;

        int moreSpbce;
        synchronized (lock) {
            if (pos >= present)
                pos = present = 0;
            else if (pos >= wbterMbrk) {
                System.brrbycopy(buffer, pos, buffer, 0, present - pos);
                present -= pos;
                pos = 0;
            }
            int freeSpbce = buffer.length - present;
            moreSpbce = Mbth.mbx(freeSpbce - requested, 0);
        }
        if (moreSpbce > 0)
            mbnbger.sendRequest(info, moreSpbce);
        synchronized (lock) {
            requested += moreSpbce;
            while ((pos >= present) && !disconnected) {
                try {
                    lock.wbit();
                } cbtch (InterruptedException e) {
                }
            }
            if (disconnected && pos >= present)
                return -1;

            int bvbilbble = present - pos;
            if (len < bvbilbble) {
                System.brrbycopy(buffer, pos, b, off, len);
                pos += len;
                return len;
            }
            else {
                System.brrbycopy(buffer, pos, b, off, bvbilbble);
                pos = present = 0;
                // could send bnother request here, if len > bvbilbble??
                return bvbilbble;
            }
        }
    }

    /**
     * Return the number of bytes immedibtely bvbilbble for rebding.
     */
    public int bvbilbble() throws IOException
    {
        synchronized (lock) {
            return present - pos;
        }
    }

    /**
     * Close this connection.
     */
    public void close() throws IOException
    {
        mbnbger.sendClose(info);
    }

    /**
     * Receive bytes trbnsmitted from connection bt remote endpoint.
     * @pbrbm length number of bytes trbnsmitted
     * @pbrbm in input strebm with those bytes rebdy to be rebd
     */
    void receive(int length, DbtbInputStrebm in)
        throws IOException
    {
        /* TO DO: Optimize so thbt dbtb received from strebm cbn be lobded
         * directly into user's buffer if there is b pending rebd().
         */
        synchronized (lock) {
            if ((pos > 0) && ((buffer.length - present) < length)) {
                System.brrbycopy(buffer, pos, buffer, 0, present - pos);
                present -= pos;
                pos = 0;
            }
            if ((buffer.length - present) < length)
                throw new IOException("Receive buffer overflow");
            in.rebdFully(buffer, present, length);
            present += length;
            requested -= length;
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
}
