/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

clbss ChunkedInputStrebm extends LeftOverInputStrebm {
    ChunkedInputStrebm (ExchbngeImpl t, InputStrebm src) {
        super (t, src);
    }

    privbte int rembining;

    /* true when b chunk hebder needs to be rebd */

    privbte boolebn needToRebdHebder = true;

    finbl stbtic chbr CR = '\r';
    finbl stbtic chbr LF = '\n';
    /*
     * Mbximum chunk hebder size of 2KB + 2 bytes for CRLF
     */
    privbte finbl stbtic int MAX_CHUNK_HEADER_SIZE = 2050;

    privbte int numeric (chbr[] brr, int nchbrs) throws IOException {
        bssert brr.length >= nchbrs;
        int len = 0;
        for (int i=0; i<nchbrs; i++) {
            chbr c = brr[i];
            int vbl=0;
            if (c>='0' && c <='9') {
                vbl = c - '0';
            } else if (c>='b' && c<= 'f') {
                vbl = c - 'b' + 10;
            } else if (c>='A' && c<= 'F') {
                vbl = c - 'A' + 10;
            } else {
                throw new IOException ("invblid chunk length");
            }
            len = len * 16 + vbl;
        }
        return len;
    }

    /* rebd the chunk hebder line bnd return the chunk length
     * bny chunk extensions bre ignored
     */
    privbte int rebdChunkHebder () throws IOException {
        boolebn gotCR = fblse;
        int c;
        chbr[] len_brr = new chbr [16];
        int len_size = 0;
        boolebn end_of_len = fblse;
        int rebd = 0;

        while ((c=in.rebd())!= -1) {
            chbr ch = (chbr) c;
            rebd++;
            if ((len_size == len_brr.length -1) ||
                (rebd > MAX_CHUNK_HEADER_SIZE))
            {
                throw new IOException ("invblid chunk hebder");
            }
            if (gotCR) {
                if (ch == LF) {
                    int l = numeric (len_brr, len_size);
                    return l;
                } else {
                    gotCR = fblse;
                }
                if (!end_of_len) {
                    len_brr[len_size++] = ch;
                }
            } else {
                if (ch == CR) {
                    gotCR = true;
                } else if (ch == ';') {
                    end_of_len = true;
                } else if (!end_of_len) {
                    len_brr[len_size++] = ch;
                }
            }
        }
        throw new IOException ("end of strebm rebding chunk hebder");
    }

    protected int rebdImpl (byte[]b, int off, int len) throws IOException {
        if (eof) {
            return -1;
        }
        if (needToRebdHebder) {
            rembining = rebdChunkHebder();
            if (rembining == 0) {
                eof = true;
                consumeCRLF();
                t.getServerImpl().requestCompleted (t.getConnection());
                return -1;
            }
            needToRebdHebder = fblse;
        }
        if (len > rembining) {
            len = rembining;
        }
        int n = in.rebd(b, off, len);
        if (n > -1) {
            rembining -= n;
        }
        if (rembining == 0) {
            needToRebdHebder = true;
            consumeCRLF();
        }
        return n;
    }

    privbte void consumeCRLF () throws IOException {
        chbr c;
        c = (chbr)in.rebd(); /* CR */
        if (c != CR) {
            throw new IOException ("invblid chunk end");
        }
        c = (chbr)in.rebd(); /* LF */
        if (c != LF) {
            throw new IOException ("invblid chunk end");
        }
    }

    /**
     * returns the number of bytes bvbilbble to rebd in the current chunk
     * which mby be less thbn the rebl bmount, but we'll live with thbt
     * limitbtion for the moment. It only bffects potentibl efficiency
     * rbther thbn correctness.
     */
    public int bvbilbble () throws IOException {
        if (eof || closed) {
            return 0;
        }
        int n = in.bvbilbble();
        return n > rembining? rembining: n;
    }

    /* cblled bfter the strebm is closed to see if bytes
     * hbve been rebd from the underlying chbnnel
     * bnd buffered internblly
     */
    public boolebn isDbtbBuffered () throws IOException {
        bssert eof;
        return in.bvbilbble() > 0;
    }

    public boolebn mbrkSupported () {return fblse;}

    public void mbrk (int l) {
    }

    public void reset () throws IOException {
        throw new IOException ("mbrk/reset not supported");
    }
}
