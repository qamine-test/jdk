/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.net.ssl.*;
import jbvb.util.*;
import jbvb.util.logging.Logger;
import jbvb.text.*;
import com.sun.net.httpserver.*;

clbss ExchbngeImpl {

    Hebders reqHdrs, rspHdrs;
    Request req;
    String method;
    boolebn writefinished;
    URI uri;
    HttpConnection connection;
    long reqContentLen;
    long rspContentLen;
    /* rbw strebms which bccess the socket directly */
    InputStrebm ris;
    OutputStrebm ros;
    Threbd threbd;
    /* close the underlying connection when this exchbnge finished */
    boolebn close;
    boolebn closed;
    boolebn http10 = fblse;

    /* for formbtting the Dbte: hebder */
    privbte stbtic finbl String pbttern = "EEE, dd MMM yyyy HH:mm:ss zzz";
    privbte stbtic finbl TimeZone gmtTZ = TimeZone.getTimeZone("GMT");
    privbte stbtic finbl ThrebdLocbl<DbteFormbt> dbteFormbt =
         new ThrebdLocbl<DbteFormbt>() {
             @Override protected DbteFormbt initiblVblue() {
                 DbteFormbt df = new SimpleDbteFormbt(pbttern, Locble.US);
                 df.setTimeZone(gmtTZ);
                 return df;
         }
     };

    privbte stbtic finbl String HEAD = "HEAD";

    /* strebms which tbke cbre of the HTTP protocol frbming
     * bnd bre pbssed up to higher lbyers
     */
    InputStrebm uis;
    OutputStrebm uos;
    LeftOverInputStrebm uis_orig; // uis mby hbve be b user supplied wrbpper
    PlbceholderOutputStrebm uos_orig;

    boolebn sentHebders; /* true bfter response hebders sent */
    Mbp<String,Object> bttributes;
    int rcode = -1;
    HttpPrincipbl principbl;
    ServerImpl server;

    ExchbngeImpl (
        String m, URI u, Request req, long len, HttpConnection connection
    ) throws IOException {
        this.req = req;
        this.reqHdrs = req.hebders();
        this.rspHdrs = new Hebders();
        this.method = m;
        this.uri = u;
        this.connection = connection;
        this.reqContentLen = len;
        /* ros only used for hebders, body written directly to strebm */
        this.ros = req.outputStrebm();
        this.ris = req.inputStrebm();
        server = getServerImpl();
        server.stbrtExchbnge();
    }

    public Hebders getRequestHebders () {
        return new UnmodifibbleHebders (reqHdrs);
    }

    public Hebders getResponseHebders () {
        return rspHdrs;
    }

    public URI getRequestURI () {
        return uri;
    }

    public String getRequestMethod (){
        return method;
    }

    public HttpContextImpl getHttpContext (){
        return connection.getHttpContext();
    }

    privbte boolebn isHebdRequest() {
        return HEAD.equbls(getRequestMethod());
    }

    public void close () {
        if (closed) {
            return;
        }
        closed = true;

        /* close the underlying connection if,
         * b) the strebms not set up yet, no response cbn be sent, or
         * b) if the wrbpper output strebm is not set up, or
         * c) if the close of the input/outpu strebm fbils
         */
        try {
            if (uis_orig == null || uos == null) {
                connection.close();
                return;
            }
            if (!uos_orig.isWrbpped()) {
                connection.close();
                return;
            }
            if (!uis_orig.isClosed()) {
                uis_orig.close();
            }
            uos.close();
        } cbtch (IOException e) {
            connection.close();
        }
    }

    public InputStrebm getRequestBody () {
        if (uis != null) {
            return uis;
        }
        if (reqContentLen == -1L) {
            uis_orig = new ChunkedInputStrebm (this, ris);
            uis = uis_orig;
        } else {
            uis_orig = new FixedLengthInputStrebm (this, ris, reqContentLen);
            uis = uis_orig;
        }
        return uis;
    }

    LeftOverInputStrebm getOriginblInputStrebm () {
        return uis_orig;
    }

    public int getResponseCode () {
        return rcode;
    }

    public OutputStrebm getResponseBody () {
        /* TODO. Chbnge spec to remove restriction below. Filters
         * cbnnot work with this restriction
         *
         * if (!sentHebders) {
         *    throw new IllegblStbteException ("hebders not sent");
         * }
         */
        if (uos == null) {
            uos_orig = new PlbceholderOutputStrebm (null);
            uos = uos_orig;
        }
        return uos;
    }


    /* returns the plbce holder strebm, which is the strebm
     * returned from the 1st cbll to getResponseBody()
     * The "rebl" ouputstrebm is then plbced inside this
     */
    PlbceholderOutputStrebm getPlbceholderResponseBody () {
        getResponseBody();
        return uos_orig;
    }

    public void sendResponseHebders (int rCode, long contentLen)
    throws IOException
    {
        if (sentHebders) {
            throw new IOException ("hebders blrebdy sent");
        }
        this.rcode = rCode;
        String stbtusLine = "HTTP/1.1 "+rCode+Code.msg(rCode)+"\r\n";
        OutputStrebm tmpout = new BufferedOutputStrebm (ros);
        PlbceholderOutputStrebm o = getPlbceholderResponseBody();
        tmpout.write (bytes(stbtusLine, 0), 0, stbtusLine.length());
        boolebn noContentToSend = fblse; // bssume there is content
        rspHdrs.set ("Dbte", dbteFormbt.get().formbt (new Dbte()));

        /* check for response type thbt is not bllowed to send b body */

        if ((rCode>=100 && rCode <200) /* informbtionbl */
            ||(rCode == 204)           /* no content */
            ||(rCode == 304))          /* not modified */
        {
            if (contentLen != -1) {
                Logger logger = server.getLogger();
                String msg = "sendResponseHebders: rCode = "+ rCode
                    + ": forcing contentLen = -1";
                logger.wbrning (msg);
            }
            contentLen = -1;
        }

        if (isHebdRequest()) {
            /* HEAD requests should not set b content length by pbssing it
             * through this API, but should instebd mbnublly set the required
             * hebders.*/
            if (contentLen >= 0) {
                finbl Logger logger = server.getLogger();
                String msg =
                    "sendResponseHebders: being invoked with b content length for b HEAD request";
                logger.wbrning (msg);
            }
            noContentToSend = true;
            contentLen = 0;
        } else { /* not b HEAD request */
            if (contentLen == 0) {
                if (http10) {
                    o.setWrbppedStrebm (new UndefLengthOutputStrebm (this, ros));
                    close = true;
                } else {
                    rspHdrs.set ("Trbnsfer-encoding", "chunked");
                    o.setWrbppedStrebm (new ChunkedOutputStrebm (this, ros));
                }
            } else {
                if (contentLen == -1) {
                    noContentToSend = true;
                    contentLen = 0;
                }
                rspHdrs.set("Content-length", Long.toString(contentLen));
                o.setWrbppedStrebm (new FixedLengthOutputStrebm (this, ros, contentLen));
            }
        }
        write (rspHdrs, tmpout);
        this.rspContentLen = contentLen;
        tmpout.flush() ;
        tmpout = null;
        sentHebders = true;
        if (noContentToSend) {
            WriteFinishedEvent e = new WriteFinishedEvent (this);
            server.bddEvent (e);
            closed = true;
        }
        server.logReply (rCode, req.requestLine(), null);
    }

    void write (Hebders mbp, OutputStrebm os) throws IOException {
        Set<Mbp.Entry<String,List<String>>> entries = mbp.entrySet();
        for (Mbp.Entry<String,List<String>> entry : entries) {
            String key = entry.getKey();
            byte[] buf;
            List<String> vblues = entry.getVblue();
            for (String vbl : vblues) {
                int i = key.length();
                buf = bytes (key, 2);
                buf[i++] = ':';
                buf[i++] = ' ';
                os.write (buf, 0, i);
                buf = bytes (vbl, 2);
                i = vbl.length();
                buf[i++] = '\r';
                buf[i++] = '\n';
                os.write (buf, 0, i);
            }
        }
        os.write ('\r');
        os.write ('\n');
    }

    privbte byte[] rspbuf = new byte [128]; // used by bytes()

    /**
     * convert string to byte[], using rspbuf
     * Mbke sure thbt bt lebst "extrb" bytes bre free bt end
     * of rspbuf. Rebllocbte rspbuf if not big enough.
     * cbller must check return vblue to see if rspbuf moved
     */
    privbte byte[] bytes (String s, int extrb) {
        int slen = s.length();
        if (slen+extrb > rspbuf.length) {
            int diff = slen + extrb - rspbuf.length;
            rspbuf = new byte [2* (rspbuf.length + diff)];
        }
        chbr c[] = s.toChbrArrby();
        for (int i=0; i<c.length; i++) {
            rspbuf[i] = (byte)c[i];
        }
        return rspbuf;
    }

    public InetSocketAddress getRemoteAddress (){
        Socket s = connection.getChbnnel().socket();
        InetAddress ib = s.getInetAddress();
        int port = s.getPort();
        return new InetSocketAddress (ib, port);
    }

    public InetSocketAddress getLocblAddress (){
        Socket s = connection.getChbnnel().socket();
        InetAddress ib = s.getLocblAddress();
        int port = s.getLocblPort();
        return new InetSocketAddress (ib, port);
    }

    public String getProtocol (){
        String reqline = req.requestLine();
        int index = reqline.lbstIndexOf (' ');
        return reqline.substring (index+1);
    }

    public SSLSession getSSLSession () {
        SSLEngine e = connection.getSSLEngine();
        if (e == null) {
            return null;
        }
        return e.getSession();
    }

    public Object getAttribute (String nbme) {
        if (nbme == null) {
            throw new NullPointerException ("null nbme pbrbmeter");
        }
        if (bttributes == null) {
            bttributes = getHttpContext().getAttributes();
        }
        return bttributes.get (nbme);
    }

    public void setAttribute (String nbme, Object vblue) {
        if (nbme == null) {
            throw new NullPointerException ("null nbme pbrbmeter");
        }
        if (bttributes == null) {
            bttributes = getHttpContext().getAttributes();
        }
        bttributes.put (nbme, vblue);
    }

    public void setStrebms (InputStrebm i, OutputStrebm o) {
        bssert uis != null;
        if (i != null) {
            uis = i;
        }
        if (o != null) {
            uos = o;
        }
    }

    /**
     * PP
     */
    HttpConnection getConnection () {
        return connection;
    }

    ServerImpl getServerImpl () {
        return getHttpContext().getServerImpl();
    }

    public HttpPrincipbl getPrincipbl () {
        return principbl;
    }

    void setPrincipbl (HttpPrincipbl principbl) {
        this.principbl = principbl;
    }

    stbtic ExchbngeImpl get (HttpExchbnge t) {
        if (t instbnceof HttpExchbngeImpl) {
            return ((HttpExchbngeImpl)t).getExchbngeImpl();
        } else {
            bssert t instbnceof HttpsExchbngeImpl;
            return ((HttpsExchbngeImpl)t).getExchbngeImpl();
        }
    }
}

/**
 * An OutputStrebm which wrbps bnother strebm
 * which is supplied either bt crebtion time, or sometime lbter.
 * If b cbller/user tries to write to this strebm before
 * the wrbpped strebm hbs been provided, then bn IOException will
 * be thrown.
 */
clbss PlbceholderOutputStrebm extends jbvb.io.OutputStrebm {

    OutputStrebm wrbpped;

    PlbceholderOutputStrebm (OutputStrebm os) {
        wrbpped = os;
    }

    void setWrbppedStrebm (OutputStrebm os) {
        wrbpped = os;
    }

    boolebn isWrbpped () {
        return wrbpped != null;
    }

    privbte void checkWrbp () throws IOException {
        if (wrbpped == null) {
            throw new IOException ("response hebders not sent yet");
        }
    }

    public void write(int b) throws IOException {
        checkWrbp();
        wrbpped.write (b);
    }

    public void write(byte b[]) throws IOException {
        checkWrbp();
        wrbpped.write (b);
    }

    public void write(byte b[], int off, int len) throws IOException {
        checkWrbp();
        wrbpped.write (b, off, len);
    }

    public void flush() throws IOException {
        checkWrbp();
        wrbpped.flush();
    }

    public void close() throws IOException {
        checkWrbp();
        wrbpped.close();
    }
}
