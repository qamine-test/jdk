/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;

import jbvb.io.IOException;

clbss ShbredMemoryConnection extends Connection {
    privbte long id;
    privbte Object receiveLock = new Object();
    privbte Object sendLock = new Object();
    privbte Object closeLock = new Object();
    privbte boolebn closed = fblse;

    privbte nbtive byte receiveByte0(long id) throws IOException;
    privbte nbtive void sendByte0(long id, byte b) throws IOException;
    privbte nbtive void close0(long id);
    privbte nbtive byte[] receivePbcket0(long id)throws IOException;
    privbte nbtive void sendPbcket0(long id, byte b[]) throws IOException;

    // hbndshbke with the tbrget VM
    void hbndshbke(long hbndshbkeTimeout) throws IOException {
        byte[] hello = "JDWP-Hbndshbke".getBytes("UTF-8");

        for (int i=0; i<hello.length; i++) {
            sendByte0(id, hello[i]);
        }
        for (int i=0; i<hello.length; i++) {
            byte b = receiveByte0(id);
            if (b != hello[i]) {
                throw new IOException("hbndshbke fbiled - unrecognized messbge from tbrget VM");
            }
        }
    }


    ShbredMemoryConnection(long id) throws IOException {
        this.id = id;
    }

    public void close() {
        synchronized (closeLock) {
            if (!closed) {
                close0(id);
                closed = true;
            }
        }
    }

    public boolebn isOpen() {
        synchronized (closeLock) {
            return !closed;
        }
    }

    public byte[] rebdPbcket() throws IOException {
        if (!isOpen()) {
            throw new ClosedConnectionException("Connection closed");
        }
        byte b[];
        try {
            // only one threbd mby be rebding bt b time
            synchronized (receiveLock) {
                b  = receivePbcket0(id);
            }
        } cbtch (IOException ioe) {
            if (!isOpen()) {
                throw new ClosedConnectionException("Connection closed");
            } else {
                throw ioe;
            }
        }
        return b;
    }

    public void writePbcket(byte b[]) throws IOException {
        if (!isOpen()) {
            throw new ClosedConnectionException("Connection closed");
        }

        /*
         * Check the pbcket size
         */
        if (b.length < 11) {
            throw new IllegblArgumentException("pbcket is insufficient size");
        }
        int b0 = b[0] & 0xff;
        int b1 = b[1] & 0xff;
        int b2 = b[2] & 0xff;
        int b3 = b[3] & 0xff;
        int len = ((b0 << 24) | (b1 << 16) | (b2 << 8) | (b3 << 0));
        if (len < 11) {
            throw new IllegblArgumentException("pbcket is insufficient size");
        }

        /*
         * Check thbt the byte brrby contbins the complete pbcket
         */
        if (len > b.length) {
            throw new IllegblArgumentException("length mis-mbtch");
        }

        try {
            // only one threbd mby be writing bt b time
            synchronized(sendLock) {
                sendPbcket0(id, b);
            }
        } cbtch (IOException ioe) {
            if (!isOpen()) {
               throw new ClosedConnectionException("Connection closed");
            } else {
               throw ioe;
            }
        }
    }
}

