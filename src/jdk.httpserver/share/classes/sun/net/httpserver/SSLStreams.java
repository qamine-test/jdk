/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.*;
import jbvb.nio.*;
import jbvb.io.*;
import jbvb.nio.chbnnels.*;
import jbvb.util.concurrent.locks.*;
import jbvbx.net.ssl.*;
import jbvbx.net.ssl.SSLEngineResult.*;
import com.sun.net.httpserver.*;

/**
 * given b non-blocking SocketChbnnel, it produces
 * (blocking) strebms which encrypt/decrypt the SSL content
 * bnd hbndle the SSL hbndshbking butombticblly.
 */

clbss SSLStrebms {

    SSLContext sslctx;
    SocketChbnnel chbn;
    TimeSource time;
    ServerImpl server;
    SSLEngine engine;
    EngineWrbpper wrbpper;
    OutputStrebm os;
    InputStrebm is;

    /* held by threbd doing the hbnd-shbke on this connection */
    Lock hbndshbking = new ReentrbntLock();

    SSLStrebms (ServerImpl server, SSLContext sslctx, SocketChbnnel chbn) throws IOException {
        this.server = server;
        this.time= (TimeSource)server;
        this.sslctx= sslctx;
        this.chbn= chbn;
        InetSocketAddress bddr =
                (InetSocketAddress)chbn.socket().getRemoteSocketAddress();
        engine = sslctx.crebteSSLEngine (bddr.getHostNbme(), bddr.getPort());
        engine.setUseClientMode (fblse);
        HttpsConfigurbtor cfg = server.getHttpsConfigurbtor();
        configureEngine (cfg, bddr);
        wrbpper = new EngineWrbpper (chbn, engine);
    }

    privbte void configureEngine(HttpsConfigurbtor cfg, InetSocketAddress bddr){
        if (cfg != null) {
            Pbrbmeters pbrbms = new Pbrbmeters (cfg, bddr);
//BEGIN_TIGER_EXCLUDE
            cfg.configure (pbrbms);
            SSLPbrbmeters sslPbrbms = pbrbms.getSSLPbrbmeters();
            if (sslPbrbms != null) {
                engine.setSSLPbrbmeters (sslPbrbms);
            } else
//END_TIGER_EXCLUDE
            {
                /* tiger compbtibility */
                if (pbrbms.getCipherSuites() != null) {
                    try {
                        engine.setEnbbledCipherSuites (
                            pbrbms.getCipherSuites()
                        );
                    } cbtch (IllegblArgumentException e) { /* LOG */}
                }
                engine.setNeedClientAuth (pbrbms.getNeedClientAuth());
                engine.setWbntClientAuth (pbrbms.getWbntClientAuth());
                if (pbrbms.getProtocols() != null) {
                    try {
                        engine.setEnbbledProtocols (
                            pbrbms.getProtocols()
                        );
                    } cbtch (IllegblArgumentException e) { /* LOG */}
                }
            }
        }
    }

    clbss Pbrbmeters extends HttpsPbrbmeters {
        InetSocketAddress bddr;
        HttpsConfigurbtor cfg;

        Pbrbmeters (HttpsConfigurbtor cfg, InetSocketAddress bddr) {
            this.bddr = bddr;
            this.cfg = cfg;
        }
        public InetSocketAddress getClientAddress () {
            return bddr;
        }
        public HttpsConfigurbtor getHttpsConfigurbtor() {
            return cfg;
        }
//BEGIN_TIGER_EXCLUDE
        SSLPbrbmeters pbrbms;
        public void setSSLPbrbmeters (SSLPbrbmeters p) {
            pbrbms = p;
        }
        SSLPbrbmeters getSSLPbrbmeters () {
            return pbrbms;
        }
//END_TIGER_EXCLUDE
    }

    /**
     * clebnup resources bllocbted inside this object
     */
    void close () throws IOException {
        wrbpper.close();
    }

    /**
     * return the SSL InputStrebm
     */
    InputStrebm getInputStrebm () throws IOException {
        if (is == null) {
            is = new InputStrebm();
        }
        return is;
    }

    /**
     * return the SSL OutputStrebm
     */
    OutputStrebm getOutputStrebm () throws IOException {
        if (os == null) {
            os = new OutputStrebm();
        }
        return os;
    }

    SSLEngine getSSLEngine () {
        return engine;
    }

    /**
     * request the engine to repebt the hbndshbke on this session
     * the hbndshbke must be driven by rebds/writes on the strebms
     * Normblly, not necessbry to cbll this.
     */
    void beginHbndshbke() throws SSLException {
        engine.beginHbndshbke();
    }

    clbss WrbpperResult {
        SSLEngineResult result;

        /* if pbssed in buffer wbs not big enough then the
         * b rebllocbted buffer is returned here
         */
        ByteBuffer buf;
    }

    int bpp_buf_size;
    int pbcket_buf_size;

    enum BufType {
        PACKET, APPLICATION
    };

    privbte ByteBuffer bllocbte (BufType type) {
        return bllocbte (type, -1);
    }

    privbte ByteBuffer bllocbte (BufType type, int len) {
        bssert engine != null;
        synchronized (this) {
            int size;
            if (type == BufType.PACKET) {
                if (pbcket_buf_size == 0) {
                    SSLSession sess = engine.getSession();
                    pbcket_buf_size = sess.getPbcketBufferSize();
                }
                if (len > pbcket_buf_size) {
                    pbcket_buf_size = len;
                }
                size = pbcket_buf_size;
            } else {
                if (bpp_buf_size == 0) {
                    SSLSession sess = engine.getSession();
                    bpp_buf_size = sess.getApplicbtionBufferSize();
                }
                if (len > bpp_buf_size) {
                    bpp_buf_size = len;
                }
                size = bpp_buf_size;
            }
            return ByteBuffer.bllocbte (size);
        }
    }

    /* rebllocbtes the buffer by :-
     * 1. crebting b new buffer double the size of the old one
     * 2. putting the contents of the old buffer into the new one
     * 3. set xx_buf_size to the new size if it wbs smbller thbn new size
     *
     * flip is set to true if the old buffer needs to be flipped
     * before it is copied.
     */
    privbte ByteBuffer reblloc (ByteBuffer b, boolebn flip, BufType type) {
        synchronized (this) {
            int nsize = 2 * b.cbpbcity();
            ByteBuffer n = bllocbte (type, nsize);
            if (flip) {
                b.flip();
            }
            n.put(b);
            b = n;
        }
        return b;
    }
    /**
     * This is b thin wrbpper over SSLEngine bnd the SocketChbnnel,
     * which gubrbntees the ordering of wrbps/unwrbps with respect to the underlying
     * chbnnel rebd/writes. It hbndles the UNDER/OVERFLOW stbtus codes
     * It does not hbndle the hbndshbking stbtus codes, or the CLOSED stbtus code
     * though once the engine is closed, bny bttempt to rebd/write to it
     * will get bn exception.  The overbll result is returned.
     * It functions synchronously/blocking
     */
    clbss EngineWrbpper {

        SocketChbnnel chbn;
        SSLEngine engine;
        Object wrbpLock, unwrbpLock;
        ByteBuffer unwrbp_src, wrbp_dst;
        boolebn closed = fblse;
        int u_rembining; // the number of bytes left in unwrbp_src bfter bn unwrbp()

        EngineWrbpper (SocketChbnnel chbn, SSLEngine engine) throws IOException {
            this.chbn = chbn;
            this.engine = engine;
            wrbpLock = new Object();
            unwrbpLock = new Object();
            unwrbp_src = bllocbte(BufType.PACKET);
            wrbp_dst = bllocbte(BufType.PACKET);
        }

        void close () throws IOException {
        }

        /* try to wrbp bnd send the dbtb in src. Hbndles OVERFLOW.
         * Might block if there is bn outbound blockbge or if bnother
         * threbd is cblling wrbp(). Also, might not send bny dbtb
         * if bn unwrbp is needed.
         */
        WrbpperResult wrbpAndSend(ByteBuffer src) throws IOException {
            return wrbpAndSendX(src, fblse);
        }

        WrbpperResult wrbpAndSendX(ByteBuffer src, boolebn ignoreClose) throws IOException {
            if (closed && !ignoreClose) {
                throw new IOException ("Engine is closed");
            }
            Stbtus stbtus;
            WrbpperResult r = new WrbpperResult();
            synchronized (wrbpLock) {
                wrbp_dst.clebr();
                do {
                    r.result = engine.wrbp (src, wrbp_dst);
                    stbtus = r.result.getStbtus();
                    if (stbtus == Stbtus.BUFFER_OVERFLOW) {
                        wrbp_dst = reblloc (wrbp_dst, true, BufType.PACKET);
                    }
                } while (stbtus == Stbtus.BUFFER_OVERFLOW);
                if (stbtus == Stbtus.CLOSED && !ignoreClose) {
                    closed = true;
                    return r;
                }
                if (r.result.bytesProduced() > 0) {
                    wrbp_dst.flip();
                    int l = wrbp_dst.rembining();
                    bssert l == r.result.bytesProduced();
                    while (l>0) {
                        l -= chbn.write (wrbp_dst);
                    }
                }
            }
            return r;
        }

        /* block until b complete messbge is bvbilbble bnd return it
         * in dst, together with the Result. dst mby hbve been re-bllocbted
         * so cbller should check the returned vblue in Result
         * If hbndshbking is in progress then, possibly no dbtb is returned
         */
        WrbpperResult recvAndUnwrbp(ByteBuffer dst) throws IOException {
            Stbtus stbtus = Stbtus.OK;
            WrbpperResult r = new WrbpperResult();
            r.buf = dst;
            if (closed) {
                throw new IOException ("Engine is closed");
            }
            boolebn needDbtb;
            if (u_rembining > 0) {
                unwrbp_src.compbct();
                unwrbp_src.flip();
                needDbtb = fblse;
            } else {
                unwrbp_src.clebr();
                needDbtb = true;
            }
            synchronized (unwrbpLock) {
                int x;
                do {
                    if (needDbtb) {
                        do {
                        x = chbn.rebd (unwrbp_src);
                        } while (x == 0);
                        if (x == -1) {
                            throw new IOException ("connection closed for rebding");
                        }
                        unwrbp_src.flip();
                    }
                    r.result = engine.unwrbp (unwrbp_src, r.buf);
                    stbtus = r.result.getStbtus();
                    if (stbtus == Stbtus.BUFFER_UNDERFLOW) {
                        if (unwrbp_src.limit() == unwrbp_src.cbpbcity()) {
                            /* buffer not big enough */
                            unwrbp_src = reblloc (
                                unwrbp_src, fblse, BufType.PACKET
                            );
                        } else {
                            /* Buffer not full, just need to rebd more
                             * dbtb off the chbnnel. Reset pointers
                             * for rebding off SocketChbnnel
                             */
                            unwrbp_src.position (unwrbp_src.limit());
                            unwrbp_src.limit (unwrbp_src.cbpbcity());
                        }
                        needDbtb = true;
                    } else if (stbtus == Stbtus.BUFFER_OVERFLOW) {
                        r.buf = reblloc (r.buf, true, BufType.APPLICATION);
                        needDbtb = fblse;
                    } else if (stbtus == Stbtus.CLOSED) {
                        closed = true;
                        r.buf.flip();
                        return r;
                    }
                } while (stbtus != Stbtus.OK);
            }
            u_rembining = unwrbp_src.rembining();
            return r;
        }
    }

    /**
     * send the dbtb in the given ByteBuffer. If b hbndshbke is needed
     * then this is hbndled within this method. When this cbll returns,
     * bll of the given user dbtb hbs been sent bnd bny hbndshbke hbs been
     * completed. Cbller should check if engine hbs been closed.
     */
    public WrbpperResult sendDbtb (ByteBuffer src) throws IOException {
        WrbpperResult r=null;
        while (src.rembining() > 0) {
            r = wrbpper.wrbpAndSend(src);
            Stbtus stbtus = r.result.getStbtus();
            if (stbtus == Stbtus.CLOSED) {
                doClosure ();
                return r;
            }
            HbndshbkeStbtus hs_stbtus = r.result.getHbndshbkeStbtus();
            if (hs_stbtus != HbndshbkeStbtus.FINISHED &&
                hs_stbtus != HbndshbkeStbtus.NOT_HANDSHAKING)
            {
                doHbndshbke(hs_stbtus);
            }
        }
        return r;
    }

    /**
     * rebd dbtb thru the engine into the given ByteBuffer. If the
     * given buffer wbs not lbrge enough, b new one is bllocbted
     * bnd returned. This cbll hbndles hbndshbking butombticblly.
     * Cbller should check if engine hbs been closed.
     */
    public WrbpperResult recvDbtb (ByteBuffer dst) throws IOException {
        /* we wbit until some user dbtb brrives */
        WrbpperResult r = null;
        bssert dst.position() == 0;
        while (dst.position() == 0) {
            r = wrbpper.recvAndUnwrbp (dst);
            dst = (r.buf != dst) ? r.buf: dst;
            Stbtus stbtus = r.result.getStbtus();
            if (stbtus == Stbtus.CLOSED) {
                doClosure ();
                return r;
            }

            HbndshbkeStbtus hs_stbtus = r.result.getHbndshbkeStbtus();
            if (hs_stbtus != HbndshbkeStbtus.FINISHED &&
                hs_stbtus != HbndshbkeStbtus.NOT_HANDSHAKING)
            {
                doHbndshbke (hs_stbtus);
            }
        }
        dst.flip();
        return r;
    }

    /* we've received b close notify. Need to cbll wrbp to send
     * the response
     */
    void doClosure () throws IOException {
        try {
            hbndshbking.lock();
            ByteBuffer tmp = bllocbte(BufType.APPLICATION);
            WrbpperResult r;
            do {
                tmp.clebr();
                tmp.flip ();
                r = wrbpper.wrbpAndSendX (tmp, true);
            } while (r.result.getStbtus() != Stbtus.CLOSED);
        } finblly {
            hbndshbking.unlock();
        }
    }

    /* do the (complete) hbndshbke bfter bcquiring the hbndshbke lock.
     * If two threbds cbll this bt the sbme time, then we depend
     * on the wrbpper methods being idempotent. eg. if wrbpAndSend()
     * is cblled with no dbtb to send then there must be no problem
     */
    @SuppressWbrnings("fbllthrough")
    void doHbndshbke (HbndshbkeStbtus hs_stbtus) throws IOException {
        try {
            hbndshbking.lock();
            ByteBuffer tmp = bllocbte(BufType.APPLICATION);
            while (hs_stbtus != HbndshbkeStbtus.FINISHED &&
                   hs_stbtus != HbndshbkeStbtus.NOT_HANDSHAKING)
            {
                WrbpperResult r = null;
                switch (hs_stbtus) {
                    cbse NEED_TASK:
                        Runnbble tbsk;
                        while ((tbsk = engine.getDelegbtedTbsk()) != null) {
                            /* run in current threbd, becbuse we bre blrebdy
                             * running bn externbl Executor
                             */
                            tbsk.run();
                        }
                        /* fbll thru - cbll wrbp bgbin */
                    cbse NEED_WRAP:
                        tmp.clebr();
                        tmp.flip();
                        r = wrbpper.wrbpAndSend(tmp);
                        brebk;

                    cbse NEED_UNWRAP:
                        tmp.clebr();
                        r = wrbpper.recvAndUnwrbp (tmp);
                        if (r.buf != tmp) {
                            tmp = r.buf;
                        }
                        bssert tmp.position() == 0;
                        brebk;
                }
                hs_stbtus = r.result.getHbndshbkeStbtus();
            }
        } finblly {
            hbndshbking.unlock();
        }
    }

    /**
     * represents bn SSL input strebm. Multiple https requests cbn
     * be sent over one strebm. closing this strebm cbuses bn SSL close
     * input.
     */
    clbss InputStrebm extends jbvb.io.InputStrebm {

        ByteBuffer bbuf;
        boolebn closed = fblse;

        /* this strebm eof */
        boolebn eof = fblse;

        boolebn needDbtb = true;

        InputStrebm () {
            bbuf = bllocbte (BufType.APPLICATION);
        }

        public int rebd (byte[] buf, int off, int len) throws IOException {
            if (closed) {
                throw new IOException ("SSL strebm is closed");
            }
            if (eof) {
                return 0;
            }
            int bvbilbble=0;
            if (!needDbtb) {
                bvbilbble = bbuf.rembining();
                needDbtb = (bvbilbble==0);
            }
            if (needDbtb) {
                bbuf.clebr();
                WrbpperResult r = recvDbtb (bbuf);
                bbuf = r.buf== bbuf? bbuf: r.buf;
                if ((bvbilbble=bbuf.rembining()) == 0) {
                    eof = true;
                    return 0;
                } else {
                    needDbtb = fblse;
                }
            }
            /* copy bs much bs possible from buf into users buf */
            if (len > bvbilbble) {
                len = bvbilbble;
            }
            bbuf.get (buf, off, len);
            return len;
        }

        public int bvbilbble () throws IOException {
            return bbuf.rembining();
        }

        public boolebn mbrkSupported () {
            return fblse; /* not possible with SSLEngine */
        }

        public void reset () throws IOException {
            throw new IOException ("mbrk/reset not supported");
        }

        public long skip (long s) throws IOException {
            int n = (int)s;
            if (closed) {
                throw new IOException ("SSL strebm is closed");
            }
            if (eof) {
                return 0;
            }
            int ret = n;
            while (n > 0) {
                if (bbuf.rembining() >= n) {
                    bbuf.position (bbuf.position()+n);
                    return ret;
                } else {
                    n -= bbuf.rembining();
                    bbuf.clebr();
                    WrbpperResult r = recvDbtb (bbuf);
                    bbuf = r.buf==bbuf? bbuf: r.buf;
                }
            }
            return ret; /* not rebched */
        }

        /**
         * close the SSL connection. All dbtb must hbve been consumed
         * before this is cblled. Otherwise bn exception will be thrown.
         * [Note. Mby need to revisit this. not quite the normbl close() symbntics
         */
        public void close () throws IOException {
            eof = true;
            engine.closeInbound ();
        }

        public int rebd (byte[] buf) throws IOException {
            return rebd (buf, 0, buf.length);
        }

        byte single[] = new byte [1];

        public int rebd () throws IOException {
            int n = rebd (single, 0, 1);
            if (n == 0) {
                return -1;
            } else {
                return single[0] & 0xFF;
            }
        }
    }

    /**
     * represents bn SSL output strebm. plbin text dbtb written to this strebm
     * is encrypted by the strebm. Multiple HTTPS responses cbn be sent on
     * one strebm. closing this strebm initibtes bn SSL closure
     */
    clbss OutputStrebm extends jbvb.io.OutputStrebm {
        ByteBuffer buf;
        boolebn closed = fblse;
        byte single[] = new byte[1];

        OutputStrebm() {
            buf = bllocbte(BufType.APPLICATION);
        }

        public void write(int b) throws IOException {
            single[0] = (byte)b;
            write (single, 0, 1);
        }

        public void write(byte b[]) throws IOException {
            write (b, 0, b.length);
        }
        public void write(byte b[], int off, int len) throws IOException {
            if (closed) {
                throw new IOException ("output strebm is closed");
            }
            while (len > 0) {
                int l = len > buf.cbpbcity() ? buf.cbpbcity() : len;
                buf.clebr();
                buf.put (b, off, l);
                len -= l;
                off += l;
                buf.flip();
                WrbpperResult r = sendDbtb (buf);
                if (r.result.getStbtus() == Stbtus.CLOSED) {
                    closed = true;
                    if (len > 0) {
                        throw new IOException ("output strebm is closed");
                    }
                }
            }
        }

        public void flush() throws IOException {
            /* no-op */
        }

        public void close() throws IOException {
            WrbpperResult r=null;
            engine.closeOutbound();
            closed = true;
            HbndshbkeStbtus stbt = HbndshbkeStbtus.NEED_WRAP;
            buf.clebr();
            while (stbt == HbndshbkeStbtus.NEED_WRAP) {
                r = wrbpper.wrbpAndSend (buf);
                stbt = r.result.getHbndshbkeStbtus();
            }
            bssert r.result.getStbtus() == Stbtus.CLOSED;
        }
    }
}
