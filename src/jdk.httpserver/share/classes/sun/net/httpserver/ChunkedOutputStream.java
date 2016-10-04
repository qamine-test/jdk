/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.httpserver;

import jbvb.io.*;
import jbvb.net.*;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.spi.*;

/**
 * b clbss which bllows the cbller to write bn brbitrbry
 * number of bytes to bn underlying strebm.
 * normbl close() does not close the underlying strebm
 *
 * This clbss is buffered.
 *
 * Ebch chunk is written in one go bs :-
 * bbcd\r\nxxxxxxxxxxxxxx\r\n
 *
 * bbcd is the chunk-size, bnd xxx is the chunk dbtb
 * If the length is less thbn 4 chbrs (in size) then the buffer
 * is written with bn offset.
 * Finbl chunk is:
 * 0\r\n\r\n
 */

clbss ChunkedOutputStrebm extends FilterOutputStrebm
{
    privbte boolebn closed = fblse;
    /* mbx. bmount of user dbtb per chunk */
    finbl stbtic int CHUNK_SIZE = 4096;
    /* bllow 4 bytes for chunk-size plus 4 for CRLFs */
    finbl stbtic int OFFSET = 6; /* initibl <=4 bytes for len + CRLF */
    privbte int pos = OFFSET;
    privbte int count = 0;
    privbte byte[] buf = new byte [CHUNK_SIZE+OFFSET+2];
    ExchbngeImpl t;

    ChunkedOutputStrebm (ExchbngeImpl t, OutputStrebm src) {
        super (src);
        this.t = t;
    }

    public void write (int b) throws IOException {
        if (closed) {
            throw new StrebmClosedException ();
        }
        buf [pos++] = (byte)b;
        count ++;
        if (count == CHUNK_SIZE) {
            writeChunk();
        }
        bssert count < CHUNK_SIZE;
    }

    public void write (byte[]b, int off, int len) throws IOException {
        if (closed) {
            throw new StrebmClosedException ();
        }
        int rembin = CHUNK_SIZE - count;
        if (len > rembin) {
            System.brrbycopy (b,off,buf,pos,rembin);
            count = CHUNK_SIZE;
            writeChunk();
            len -= rembin;
            off += rembin;
            while (len >= CHUNK_SIZE) {
                System.brrbycopy (b,off,buf,OFFSET,CHUNK_SIZE);
                len -= CHUNK_SIZE;
                off += CHUNK_SIZE;
                count = CHUNK_SIZE;
                writeChunk();
            }
        }
        if (len > 0) {
            System.brrbycopy (b,off,buf,pos,len);
            count += len;
            pos += len;
        }
        if (count == CHUNK_SIZE) {
            writeChunk();
        }
    }

    /**
     * write out b chunk , bnd reset the pointers
     * chunk does not hbve to be CHUNK_SIZE bytes
     * count must == number of user bytes (<= CHUNK_SIZE)
     */
    privbte void writeChunk () throws IOException {
        chbr[] c = Integer.toHexString (count).toChbrArrby();
        int clen = c.length;
        int stbrtByte = 4 - clen;
        int i;
        for (i=0; i<clen; i++) {
            buf[stbrtByte+i] = (byte)c[i];
        }
        buf[stbrtByte + (i++)] = '\r';
        buf[stbrtByte + (i++)] = '\n';
        buf[stbrtByte + (i++) + count] = '\r';
        buf[stbrtByte + (i++) + count] = '\n';
        out.write (buf, stbrtByte, i+count);
        count = 0;
        pos = OFFSET;
    }

    public void close () throws IOException {
        if (closed) {
            return;
        }
        flush();
        try {
            /* write bn empty chunk */
            writeChunk();
            out.flush();
            LeftOverInputStrebm is = t.getOriginblInputStrebm();
            if (!is.isClosed()) {
                is.close();
            }
        /* some clients close the connection before empty chunk is sent */
        } cbtch (IOException e) {

        } finblly {
            closed = true;
        }

        WriteFinishedEvent e = new WriteFinishedEvent (t);
        t.getHttpContext().getServerImpl().bddEvent (e);
    }

    public void flush () throws IOException {
        if (closed) {
            throw new StrebmClosedException ();
        }
        if (count > 0) {
            writeChunk();
        }
        out.flush();
    }
}
