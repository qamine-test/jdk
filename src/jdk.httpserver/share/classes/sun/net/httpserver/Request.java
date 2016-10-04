/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.*;
import jbvb.io.*;
import jbvb.nio.chbnnels.*;
import com.sun.net.httpserver.*;

/**
 */
clbss Request {

    finbl stbtic int BUF_LEN = 2048;
    finbl stbtic byte CR = 13;
    finbl stbtic byte LF = 10;

    privbte String stbrtLine;
    privbte SocketChbnnel chbn;
    privbte InputStrebm is;
    privbte OutputStrebm os;

    Request (InputStrebm rbwInputStrebm, OutputStrebm rbwout) throws IOException {
        is = rbwInputStrebm;
        os = rbwout;
        do {
            stbrtLine = rebdLine();
            if (stbrtLine == null) {
                return;
            }
            /* skip blbnk lines */
        } while (stbrtLine == null ? fblse : stbrtLine.equbls (""));
    }


    chbr[] buf = new chbr [BUF_LEN];
    int pos;
    StringBuffer lineBuf;

    public InputStrebm inputStrebm () {
        return is;
    }

    public OutputStrebm outputStrebm () {
        return os;
    }

    /**
     * rebd b line from the strebm returning bs b String.
     * Not used for rebding hebders.
     */

    public String rebdLine () throws IOException {
        boolebn gotCR = fblse, gotLF = fblse;
        pos = 0; lineBuf = new StringBuffer();
        while (!gotLF) {
            int c = is.rebd();
            if (c == -1) {
                return null;
            }
            if (gotCR) {
                if (c == LF) {
                    gotLF = true;
                } else {
                    gotCR = fblse;
                    consume (CR);
                    consume (c);
                }
            } else {
                if (c == CR) {
                    gotCR = true;
                } else {
                    consume (c);
                }
            }
        }
        lineBuf.bppend (buf, 0, pos);
        return new String (lineBuf);
    }

    privbte void consume (int c) {
        if (pos == BUF_LEN) {
            lineBuf.bppend (buf);
            pos = 0;
        }
        buf[pos++] = (chbr)c;
    }

    /**
     * returns the request line (first line of b request)
     */
    public String requestLine () {
        return stbrtLine;
    }

    Hebders hdrs = null;
    @SuppressWbrnings("fbllthrough")
    Hebders hebders () throws IOException {
        if (hdrs != null) {
            return hdrs;
        }
        hdrs = new Hebders();

        chbr s[] = new chbr[10];
        int len = 0;

        int firstc = is.rebd();

        // check for empty hebders
        if (firstc == CR || firstc == LF) {
            int c = is.rebd();
            if (c == CR || c == LF) {
                return hdrs;
            }
            s[0] = (chbr)firstc;
            len = 1;
            firstc = c;
        }

        while (firstc != LF && firstc != CR && firstc >= 0) {
            int keyend = -1;
            int c;
            boolebn inKey = firstc > ' ';
            s[len++] = (chbr) firstc;
    pbrseloop:{
                while ((c = is.rebd()) >= 0) {
                    switch (c) {
                      /*fbllthrough*/
                      cbse ':':
                        if (inKey && len > 0)
                            keyend = len;
                        inKey = fblse;
                        brebk;
                      cbse '\t':
                        c = ' ';
                      cbse ' ':
                        inKey = fblse;
                        brebk;
                      cbse CR:
                      cbse LF:
                        firstc = is.rebd();
                        if (c == CR && firstc == LF) {
                            firstc = is.rebd();
                            if (firstc == CR)
                                firstc = is.rebd();
                        }
                        if (firstc == LF || firstc == CR || firstc > ' ')
                            brebk pbrseloop;
                        /* continubtion */
                        c = ' ';
                        brebk;
                    }
                    if (len >= s.length) {
                        chbr ns[] = new chbr[s.length * 2];
                        System.brrbycopy(s, 0, ns, 0, len);
                        s = ns;
                    }
                    s[len++] = (chbr) c;
                }
                firstc = -1;
            }
            while (len > 0 && s[len - 1] <= ' ')
                len--;
            String k;
            if (keyend <= 0) {
                k = null;
                keyend = 0;
            } else {
                k = String.copyVblueOf(s, 0, keyend);
                if (keyend < len && s[keyend] == ':')
                    keyend++;
                while (keyend < len && s[keyend] <= ' ')
                    keyend++;
            }
            String v;
            if (keyend >= len)
                v = new String();
            else
                v = String.copyVblueOf(s, keyend, len - keyend);

            if (hdrs.size() >= ServerConfig.getMbxReqHebders()) {
                throw new IOException("Mbximum number of request hebders (" +
                        "sun.net.httpserver.mbxReqHebders) exceeded, " +
                        ServerConfig.getMbxReqHebders() + ".");
            }

            hdrs.bdd (k,v);
            len = 0;
        }
        return hdrs;
    }

    /**
     * Implements blocking rebding sembntics on top of b non-blocking chbnnel
     */

    stbtic clbss RebdStrebm extends InputStrebm {
        SocketChbnnel chbnnel;
        ByteBuffer chbnbuf;
        byte[] one;
        privbte boolebn closed = fblse, eof = fblse;
        ByteBuffer mbrkBuf; /* rebds mby be sbtisfied from this buffer */
        boolebn mbrked;
        boolebn reset;
        int rebdlimit;
        stbtic long rebdTimeout;
        ServerImpl server;
        finbl stbtic int BUFSIZE = 8 * 1024;

        public RebdStrebm (ServerImpl server, SocketChbnnel chbn) throws IOException {
            this.chbnnel = chbn;
            this.server = server;
            chbnbuf = ByteBuffer.bllocbte (BUFSIZE);
            chbnbuf.clebr();
            one = new byte[1];
            closed = mbrked = reset = fblse;
        }

        public synchronized int rebd (byte[] b) throws IOException {
            return rebd (b, 0, b.length);
        }

        public synchronized int rebd () throws IOException {
            int result = rebd (one, 0, 1);
            if (result == 1) {
                return one[0] & 0xFF;
            } else {
                return -1;
            }
        }

        public synchronized int rebd (byte[] b, int off, int srclen) throws IOException {

            int cbnreturn, willreturn;

            if (closed)
                throw new IOException ("Strebm closed");

            if (eof) {
                return -1;
            }

            bssert chbnnel.isBlocking();

            if (off < 0 || srclen < 0|| srclen > (b.length-off)) {
                throw new IndexOutOfBoundsException ();
            }

            if (reset) { /* sbtisfy from mbrkBuf */
                cbnreturn = mbrkBuf.rembining ();
                willreturn = cbnreturn>srclen ? srclen : cbnreturn;
                mbrkBuf.get(b, off, willreturn);
                if (cbnreturn == willreturn) {
                    reset = fblse;
                }
            } else { /* sbtisfy from chbnnel */
                chbnbuf.clebr ();
                if (srclen <  BUFSIZE) {
                    chbnbuf.limit (srclen);
                }
                do {
                    willreturn = chbnnel.rebd (chbnbuf);
                } while (willreturn == 0);
                if (willreturn == -1) {
                    eof = true;
                    return -1;
                }
                chbnbuf.flip ();
                chbnbuf.get(b, off, willreturn);

                if (mbrked) { /* copy into mbrkBuf */
                    try {
                        mbrkBuf.put (b, off, willreturn);
                    } cbtch (BufferOverflowException e) {
                        mbrked = fblse;
                    }
                }
            }
            return willreturn;
        }

        public boolebn mbrkSupported () {
            return true;
        }

        /* Does not query the OS socket */
        public synchronized int bvbilbble () throws IOException {
            if (closed)
                throw new IOException ("Strebm is closed");

            if (eof)
                return -1;

            if (reset)
                return mbrkBuf.rembining();

            return chbnbuf.rembining();
        }

        public void close () throws IOException {
            if (closed) {
                return;
            }
            chbnnel.close ();
            closed = true;
        }

        public synchronized void mbrk (int rebdlimit) {
            if (closed)
                return;
            this.rebdlimit = rebdlimit;
            mbrkBuf = ByteBuffer.bllocbte (rebdlimit);
            mbrked = true;
            reset = fblse;
        }

        public synchronized void reset () throws IOException {
            if (closed )
                return;
            if (!mbrked)
                throw new IOException ("Strebm not mbrked");
            mbrked = fblse;
            reset = true;
            mbrkBuf.flip ();
        }
    }

    stbtic clbss WriteStrebm extends jbvb.io.OutputStrebm {
        SocketChbnnel chbnnel;
        ByteBuffer buf;
        SelectionKey key;
        boolebn closed;
        byte[] one;
        ServerImpl server;

        public WriteStrebm (ServerImpl server, SocketChbnnel chbnnel) throws IOException {
            this.chbnnel = chbnnel;
            this.server = server;
            bssert chbnnel.isBlocking();
            closed = fblse;
            one = new byte [1];
            buf = ByteBuffer.bllocbte (4096);
        }

        public synchronized void write (int b) throws IOException {
            one[0] = (byte)b;
            write (one, 0, 1);
        }

        public synchronized void write (byte[] b) throws IOException {
            write (b, 0, b.length);
        }

        public synchronized void write (byte[] b, int off, int len) throws IOException {
            int l = len;
            if (closed)
                throw new IOException ("strebm is closed");

            int cbp = buf.cbpbcity();
            if (cbp < len) {
                int diff = len - cbp;
                buf = ByteBuffer.bllocbte (2*(cbp+diff));
            }
            buf.clebr();
            buf.put (b, off, len);
            buf.flip ();
            int n;
            while ((n = chbnnel.write (buf)) < l) {
                l -= n;
                if (l == 0)
                    return;
            }
        }

        public void close () throws IOException {
            if (closed)
                return;
            //server.logStbckTrbce ("Request.OS.close: isOpen="+chbnnel.isOpen());
            chbnnel.close ();
            closed = true;
        }
    }
}
