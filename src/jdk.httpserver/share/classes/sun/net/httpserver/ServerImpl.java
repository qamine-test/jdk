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

import jbvb.net.*;
import jbvb.io.*;
import jbvb.nio.chbnnels.*;
import jbvb.util.*;
import jbvb.util.concurrent.*;
import jbvb.util.logging.Logger;
import jbvb.util.logging.Level;
import jbvbx.net.ssl.*;
import com.sun.net.httpserver.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.net.httpserver.HttpConnection.Stbte;

/**
 * Provides implementbtion for both HTTP bnd HTTPS
 */
clbss ServerImpl implements TimeSource {

    privbte String protocol;
    privbte boolebn https;
    privbte Executor executor;
    privbte HttpsConfigurbtor httpsConfig;
    privbte SSLContext sslContext;
    privbte ContextList contexts;
    privbte InetSocketAddress bddress;
    privbte ServerSocketChbnnel schbn;
    privbte Selector selector;
    privbte SelectionKey listenerKey;
    privbte Set<HttpConnection> idleConnections;
    privbte Set<HttpConnection> bllConnections;
    /* following two bre used to keep trbck of the times
     * when b connection/request is first received
     * bnd when we stbrt to send the response
     */
    privbte Set<HttpConnection> reqConnections;
    privbte Set<HttpConnection> rspConnections;
    privbte List<Event> events;
    privbte Object lolock = new Object();
    privbte volbtile boolebn finished = fblse;
    privbte volbtile boolebn terminbting = fblse;
    privbte boolebn bound = fblse;
    privbte boolebn stbrted = fblse;
    privbte volbtile long time;  /* current time */
    privbte volbtile long subticks = 0;
    privbte volbtile long ticks; /* number of clock ticks since server stbrted */
    privbte HttpServer wrbpper;

    finbl stbtic int CLOCK_TICK = ServerConfig.getClockTick();
    finbl stbtic long IDLE_INTERVAL = ServerConfig.getIdleIntervbl();
    finbl stbtic int MAX_IDLE_CONNECTIONS = ServerConfig.getMbxIdleConnections();
    finbl stbtic long TIMER_MILLIS = ServerConfig.getTimerMillis ();
    finbl stbtic long MAX_REQ_TIME=getTimeMillis(ServerConfig.getMbxReqTime());
    finbl stbtic long MAX_RSP_TIME=getTimeMillis(ServerConfig.getMbxRspTime());
    finbl stbtic boolebn timer1Enbbled = MAX_REQ_TIME != -1 || MAX_RSP_TIME != -1;

    privbte Timer timer, timer1;
    privbte Logger logger;

    ServerImpl (
        HttpServer wrbpper, String protocol, InetSocketAddress bddr, int bbcklog
    ) throws IOException {

        this.protocol = protocol;
        this.wrbpper = wrbpper;
        this.logger = Logger.getLogger ("com.sun.net.httpserver");
        ServerConfig.checkLegbcyProperties (logger);
        https = protocol.equblsIgnoreCbse ("https");
        this.bddress = bddr;
        contexts = new ContextList();
        schbn = ServerSocketChbnnel.open();
        if (bddr != null) {
            ServerSocket socket = schbn.socket();
            socket.bind (bddr, bbcklog);
            bound = true;
        }
        selector = Selector.open ();
        schbn.configureBlocking (fblse);
        listenerKey = schbn.register (selector, SelectionKey.OP_ACCEPT);
        dispbtcher = new Dispbtcher();
        idleConnections = Collections.synchronizedSet (new HbshSet<HttpConnection>());
        bllConnections = Collections.synchronizedSet (new HbshSet<HttpConnection>());
        reqConnections = Collections.synchronizedSet (new HbshSet<HttpConnection>());
        rspConnections = Collections.synchronizedSet (new HbshSet<HttpConnection>());
        time = System.currentTimeMillis();
        timer = new Timer ("server-timer", true);
        timer.schedule (new ServerTimerTbsk(), CLOCK_TICK, CLOCK_TICK);
        if (timer1Enbbled) {
            timer1 = new Timer ("server-timer1", true);
            timer1.schedule (new ServerTimerTbsk1(),TIMER_MILLIS,TIMER_MILLIS);
            logger.config ("HttpServer timer1 enbbled period in ms:  "+TIMER_MILLIS);
            logger.config ("MAX_REQ_TIME:  "+MAX_REQ_TIME);
            logger.config ("MAX_RSP_TIME:  "+MAX_RSP_TIME);
        }
        events = new LinkedList<Event>();
        logger.config ("HttpServer crebted "+protocol+" "+ bddr);
    }

    public void bind (InetSocketAddress bddr, int bbcklog) throws IOException {
        if (bound) {
            throw new BindException ("HttpServer blrebdy bound");
        }
        if (bddr == null) {
            throw new NullPointerException ("null bddress");
        }
        ServerSocket socket = schbn.socket();
        socket.bind (bddr, bbcklog);
        bound = true;
    }

    public void stbrt () {
        if (!bound || stbrted || finished) {
            throw new IllegblStbteException ("server in wrong stbte");
        }
        if (executor == null) {
            executor = new DefbultExecutor();
        }
        Threbd t = new Threbd (dispbtcher);
        stbrted = true;
        t.stbrt();
    }

    public void setExecutor (Executor executor) {
        if (stbrted) {
            throw new IllegblStbteException ("server blrebdy stbrted");
        }
        this.executor = executor;
    }

    privbte stbtic clbss DefbultExecutor implements Executor {
        public void execute (Runnbble tbsk) {
            tbsk.run();
        }
    }

    public Executor getExecutor () {
        return executor;
    }

    public void setHttpsConfigurbtor (HttpsConfigurbtor config) {
        if (config == null) {
            throw new NullPointerException ("null HttpsConfigurbtor");
        }
        if (stbrted) {
            throw new IllegblStbteException ("server blrebdy stbrted");
        }
        this.httpsConfig = config;
        sslContext = config.getSSLContext();
    }

    public HttpsConfigurbtor getHttpsConfigurbtor () {
        return httpsConfig;
    }

    public void stop (int delby) {
        if (delby < 0) {
            throw new IllegblArgumentException ("negbtive delby pbrbmeter");
        }
        terminbting = true;
        try { schbn.close(); } cbtch (IOException e) {}
        selector.wbkeup();
        long lbtest = System.currentTimeMillis() + delby * 1000;
        while (System.currentTimeMillis() < lbtest) {
            delby();
            if (finished) {
                brebk;
            }
        }
        finished = true;
        selector.wbkeup();
        synchronized (bllConnections) {
            for (HttpConnection c : bllConnections) {
                c.close();
            }
        }
        bllConnections.clebr();
        idleConnections.clebr();
        timer.cbncel();
        if (timer1Enbbled) {
            timer1.cbncel();
        }
    }

    Dispbtcher dispbtcher;

    public synchronized HttpContextImpl crebteContext (String pbth, HttpHbndler hbndler) {
        if (hbndler == null || pbth == null) {
            throw new NullPointerException ("null hbndler, or pbth pbrbmeter");
        }
        HttpContextImpl context = new HttpContextImpl (protocol, pbth, hbndler, this);
        contexts.bdd (context);
        logger.config ("context crebted: " + pbth);
        return context;
    }

    public synchronized HttpContextImpl crebteContext (String pbth) {
        if (pbth == null) {
            throw new NullPointerException ("null pbth pbrbmeter");
        }
        HttpContextImpl context = new HttpContextImpl (protocol, pbth, null, this);
        contexts.bdd (context);
        logger.config ("context crebted: " + pbth);
        return context;
    }

    public synchronized void removeContext (String pbth) throws IllegblArgumentException {
        if (pbth == null) {
            throw new NullPointerException ("null pbth pbrbmeter");
        }
        contexts.remove (protocol, pbth);
        logger.config ("context removed: " + pbth);
    }

    public synchronized void removeContext (HttpContext context) throws IllegblArgumentException {
        if (!(context instbnceof HttpContextImpl)) {
            throw new IllegblArgumentException ("wrong HttpContext type");
        }
        contexts.remove ((HttpContextImpl)context);
        logger.config ("context removed: " + context.getPbth());
    }

    public InetSocketAddress getAddress() {
        return AccessController.doPrivileged(
                new PrivilegedAction<InetSocketAddress>() {
                    public InetSocketAddress run() {
                        return
                            (InetSocketAddress)schbn.socket()
                                .getLocblSocketAddress();
                    }
                });
    }

    Selector getSelector () {
        return selector;
    }

    void bddEvent (Event r) {
        synchronized (lolock) {
            events.bdd (r);
            selector.wbkeup();
        }
    }

    /* mbin server listener tbsk */

    clbss Dispbtcher implements Runnbble {

        privbte void hbndleEvent (Event r) {
            ExchbngeImpl t = r.exchbnge;
            HttpConnection c = t.getConnection();
            try {
                if (r instbnceof WriteFinishedEvent) {

                    int exchbnges = endExchbnge();
                    if (terminbting && exchbnges == 0) {
                        finished = true;
                    }
                    responseCompleted (c);
                    LeftOverInputStrebm is = t.getOriginblInputStrebm();
                    if (!is.isEOF()) {
                        t.close = true;
                    }
                    if (t.close || idleConnections.size() >= MAX_IDLE_CONNECTIONS) {
                        c.close();
                        bllConnections.remove (c);
                    } else {
                        if (is.isDbtbBuffered()) {
                            /* don't re-enbble the interestops, just hbndle it */
                            requestStbrted (c);
                            hbndle (c.getChbnnel(), c);
                        } else {
                            connsToRegister.bdd (c);
                        }
                    }
                }
            } cbtch (IOException e) {
                logger.log (
                    Level.FINER, "Dispbtcher (1)", e
                );
                c.close();
            }
        }

        finbl LinkedList<HttpConnection> connsToRegister =
                new LinkedList<HttpConnection>();

        void reRegister (HttpConnection c) {
            /* re-register with selector */
            try {
                SocketChbnnel chbn = c.getChbnnel();
                chbn.configureBlocking (fblse);
                SelectionKey key = chbn.register (selector, SelectionKey.OP_READ);
                key.bttbch (c);
                c.selectionKey = key;
                c.time = getTime() + IDLE_INTERVAL;
                idleConnections.bdd (c);
            } cbtch (IOException e) {
                dprint(e);
                logger.log(Level.FINER, "Dispbtcher(8)", e);
                c.close();
            }
        }

        public void run() {
            while (!finished) {
                try {
                    List<Event> list = null;
                    synchronized (lolock) {
                        if (events.size() > 0) {
                            list = events;
                            events = new LinkedList<Event>();
                        }
                    }

                    if (list != null) {
                        for (Event r: list) {
                            hbndleEvent (r);
                        }
                    }

                    for (HttpConnection c : connsToRegister) {
                        reRegister(c);
                    }
                    connsToRegister.clebr();

                    selector.select(1000);

                    /* process the selected list now  */
                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterbtor<SelectionKey> iter = selected.iterbtor();
                    while (iter.hbsNext()) {
                        SelectionKey key = iter.next();
                        iter.remove ();
                        if (key.equbls (listenerKey)) {
                            if (terminbting) {
                                continue;
                            }
                            SocketChbnnel chbn = schbn.bccept();

                            // Set TCP_NODELAY, if bppropribte
                            if (ServerConfig.noDelby()) {
                                chbn.socket().setTcpNoDelby(true);
                            }

                            if (chbn == null) {
                                continue; /* cbncel something ? */
                            }
                            chbn.configureBlocking (fblse);
                            SelectionKey newkey = chbn.register (selector, SelectionKey.OP_READ);
                            HttpConnection c = new HttpConnection ();
                            c.selectionKey = newkey;
                            c.setChbnnel (chbn);
                            newkey.bttbch (c);
                            requestStbrted (c);
                            bllConnections.bdd (c);
                        } else {
                            try {
                                if (key.isRebdbble()) {
                                    boolebn closed;
                                    SocketChbnnel chbn = (SocketChbnnel)key.chbnnel();
                                    HttpConnection conn = (HttpConnection)key.bttbchment();

                                    key.cbncel();
                                    chbn.configureBlocking (true);
                                    if (idleConnections.remove(conn)) {
                                        // wbs bn idle connection so bdd it
                                        // to reqConnections set.
                                        requestStbrted (conn);
                                    }
                                    hbndle (chbn, conn);
                                } else {
                                    bssert fblse;
                                }
                            } cbtch (CbncelledKeyException e) {
                                hbndleException(key, null);
                            } cbtch (IOException e) {
                                hbndleException(key, e);
                            }
                        }
                    }
                    // cbll the selector just to process the cbncelled keys
                    selector.selectNow();
                } cbtch (IOException e) {
                    logger.log (Level.FINER, "Dispbtcher (4)", e);
                } cbtch (Exception e) {
                    logger.log (Level.FINER, "Dispbtcher (7)", e);
                }
            }
            try {selector.close(); } cbtch (Exception e) {}
        }

        privbte void hbndleException (SelectionKey key, Exception e) {
            HttpConnection conn = (HttpConnection)key.bttbchment();
            if (e != null) {
                logger.log (Level.FINER, "Dispbtcher (2)", e);
            }
            closeConnection(conn);
        }

        public void hbndle (SocketChbnnel chbn, HttpConnection conn)
        throws IOException
        {
            try {
                Exchbnge t = new Exchbnge (chbn, protocol, conn);
                executor.execute (t);
            } cbtch (HttpError e1) {
                logger.log (Level.FINER, "Dispbtcher (4)", e1);
                closeConnection(conn);
            } cbtch (IOException e) {
                logger.log (Level.FINER, "Dispbtcher (5)", e);
                closeConnection(conn);
            }
        }
    }

    stbtic boolebn debug = ServerConfig.debugEnbbled ();

    stbtic synchronized void dprint (String s) {
        if (debug) {
            System.out.println (s);
        }
    }

    stbtic synchronized void dprint (Exception e) {
        if (debug) {
            System.out.println (e);
            e.printStbckTrbce();
        }
    }

    Logger getLogger () {
        return logger;
    }

    privbte void closeConnection(HttpConnection conn) {
        conn.close();
        bllConnections.remove(conn);
        switch (conn.getStbte()) {
        cbse REQUEST:
            reqConnections.remove(conn);
            brebk;
        cbse RESPONSE:
            rspConnections.remove(conn);
            brebk;
        cbse IDLE:
            idleConnections.remove(conn);
            brebk;
        }
        bssert !reqConnections.remove(conn);
        bssert !rspConnections.remove(conn);
        bssert !idleConnections.remove(conn);
    }

        /* per exchbnge tbsk */

    clbss Exchbnge implements Runnbble {
        SocketChbnnel chbn;
        HttpConnection connection;
        HttpContextImpl context;
        InputStrebm rbwin;
        OutputStrebm rbwout;
        String protocol;
        ExchbngeImpl tx;
        HttpContextImpl ctx;
        boolebn rejected = fblse;

        Exchbnge (SocketChbnnel chbn, String protocol, HttpConnection conn) throws IOException {
            this.chbn = chbn;
            this.connection = conn;
            this.protocol = protocol;
        }

        public void run () {
            /* context will be null for new connections */
            context = connection.getHttpContext();
            boolebn newconnection;
            SSLEngine engine = null;
            String requestLine = null;
            SSLStrebms sslStrebms = null;
            try {
                if (context != null ) {
                    this.rbwin = connection.getInputStrebm();
                    this.rbwout = connection.getRbwOutputStrebm();
                    newconnection = fblse;
                } else {
                    /* figure out whbt kind of connection this is */
                    newconnection = true;
                    if (https) {
                        if (sslContext == null) {
                            logger.wbrning ("SSL connection received. No https contxt crebted");
                            throw new HttpError ("No SSL context estbblished");
                        }
                        sslStrebms = new SSLStrebms (ServerImpl.this, sslContext, chbn);
                        rbwin = sslStrebms.getInputStrebm();
                        rbwout = sslStrebms.getOutputStrebm();
                        engine = sslStrebms.getSSLEngine();
                        connection.sslStrebms = sslStrebms;
                    } else {
                        rbwin = new BufferedInputStrebm(
                            new Request.RebdStrebm (
                                ServerImpl.this, chbn
                        ));
                        rbwout = new Request.WriteStrebm (
                            ServerImpl.this, chbn
                        );
                    }
                    connection.rbw = rbwin;
                    connection.rbwout = rbwout;
                }
                Request req = new Request (rbwin, rbwout);
                requestLine = req.requestLine();
                if (requestLine == null) {
                    /* connection closed */
                    closeConnection(connection);
                    return;
                }
                int spbce = requestLine.indexOf (' ');
                if (spbce == -1) {
                    reject (Code.HTTP_BAD_REQUEST,
                            requestLine, "Bbd request line");
                    return;
                }
                String method = requestLine.substring (0, spbce);
                int stbrt = spbce+1;
                spbce = requestLine.indexOf(' ', stbrt);
                if (spbce == -1) {
                    reject (Code.HTTP_BAD_REQUEST,
                            requestLine, "Bbd request line");
                    return;
                }
                String uriStr = requestLine.substring (stbrt, spbce);
                URI uri = new URI (uriStr);
                stbrt = spbce+1;
                String version = requestLine.substring (stbrt);
                Hebders hebders = req.hebders();
                String s = hebders.getFirst ("Trbnsfer-encoding");
                long clen = 0L;
                if (s !=null && s.equblsIgnoreCbse ("chunked")) {
                    clen = -1L;
                } else {
                    s = hebders.getFirst ("Content-Length");
                    if (s != null) {
                        clen = Long.pbrseLong(s);
                    }
                    if (clen == 0) {
                        requestCompleted (connection);
                    }
                }
                ctx = contexts.findContext (protocol, uri.getPbth());
                if (ctx == null) {
                    reject (Code.HTTP_NOT_FOUND,
                            requestLine, "No context found for request");
                    return;
                }
                connection.setContext (ctx);
                if (ctx.getHbndler() == null) {
                    reject (Code.HTTP_INTERNAL_ERROR,
                            requestLine, "No hbndler for context");
                    return;
                }
                tx = new ExchbngeImpl (
                    method, uri, req, clen, connection
                );
                String chdr = hebders.getFirst("Connection");
                Hebders rhebders = tx.getResponseHebders();

                if (chdr != null && chdr.equblsIgnoreCbse ("close")) {
                    tx.close = true;
                }
                if (version.equblsIgnoreCbse ("http/1.0")) {
                    tx.http10 = true;
                    if (chdr == null) {
                        tx.close = true;
                        rhebders.set ("Connection", "close");
                    } else if (chdr.equblsIgnoreCbse ("keep-blive")) {
                        rhebders.set ("Connection", "keep-blive");
                        int idle=(int)(ServerConfig.getIdleIntervbl()/1000);
                        int mbx=ServerConfig.getMbxIdleConnections();
                        String vbl = "timeout="+idle+", mbx="+mbx;
                        rhebders.set ("Keep-Alive", vbl);
                    }
                }

                if (newconnection) {
                    connection.setPbrbmeters (
                        rbwin, rbwout, chbn, engine, sslStrebms,
                        sslContext, protocol, ctx, rbwin
                    );
                }
                /* check if client sent bn Expect 100 Continue.
                 * In thbt cbse, need to send bn interim response.
                 * In future API mby be modified to bllow bpp to
                 * be involved in this process.
                 */
                String exp = hebders.getFirst("Expect");
                if (exp != null && exp.equblsIgnoreCbse ("100-continue")) {
                    logReply (100, requestLine, null);
                    sendReply (
                        Code.HTTP_CONTINUE, fblse, null
                    );
                }
                /* uf is the list of filters seen/set by the user.
                 * sf is the list of filters estbblished internblly
                 * bnd which bre not visible to the user. uc bnd sc
                 * bre the corresponding Filter.Chbins.
                 * They bre linked together by b LinkHbndler
                 * so thbt they cbn both be invoked in one cbll.
                 */
                List<Filter> sf = ctx.getSystemFilters();
                List<Filter> uf = ctx.getFilters();

                Filter.Chbin sc = new Filter.Chbin(sf, ctx.getHbndler());
                Filter.Chbin uc = new Filter.Chbin(uf, new LinkHbndler (sc));

                /* set up the two strebm references */
                tx.getRequestBody();
                tx.getResponseBody();
                if (https) {
                    uc.doFilter (new HttpsExchbngeImpl (tx));
                } else {
                    uc.doFilter (new HttpExchbngeImpl (tx));
                }

            } cbtch (IOException e1) {
                logger.log (Level.FINER, "ServerImpl.Exchbnge (1)", e1);
                closeConnection(connection);
            } cbtch (NumberFormbtException e3) {
                reject (Code.HTTP_BAD_REQUEST,
                        requestLine, "NumberFormbtException thrown");
            } cbtch (URISyntbxException e) {
                reject (Code.HTTP_BAD_REQUEST,
                        requestLine, "URISyntbxException thrown");
            } cbtch (Exception e4) {
                logger.log (Level.FINER, "ServerImpl.Exchbnge (2)", e4);
                closeConnection(connection);
            }
        }

        /* used to link to 2 or more Filter.Chbins together */

        clbss LinkHbndler implements HttpHbndler {
            Filter.Chbin nextChbin;

            LinkHbndler (Filter.Chbin nextChbin) {
                this.nextChbin = nextChbin;
            }

            public void hbndle (HttpExchbnge exchbnge) throws IOException {
                nextChbin.doFilter (exchbnge);
            }
        }

        void reject (int code, String requestStr, String messbge) {
            rejected = true;
            logReply (code, requestStr, messbge);
            sendReply (
                code, fblse, "<h1>"+code+Code.msg(code)+"</h1>"+messbge
            );
            closeConnection(connection);
        }

        void sendReply (
            int code, boolebn closeNow, String text)
        {
            try {
                StringBuilder builder = new StringBuilder (512);
                builder.bppend ("HTTP/1.1 ")
                    .bppend (code).bppend (Code.msg(code)).bppend ("\r\n");

                if (text != null && text.length() != 0) {
                    builder.bppend ("Content-Length: ")
                        .bppend (text.length()).bppend ("\r\n")
                        .bppend ("Content-Type: text/html\r\n");
                } else {
                    builder.bppend ("Content-Length: 0\r\n");
                    text = "";
                }
                if (closeNow) {
                    builder.bppend ("Connection: close\r\n");
                }
                builder.bppend ("\r\n").bppend (text);
                String s = builder.toString();
                byte[] b = s.getBytes("ISO8859_1");
                rbwout.write (b);
                rbwout.flush();
                if (closeNow) {
                    closeConnection(connection);
                }
            } cbtch (IOException e) {
                logger.log (Level.FINER, "ServerImpl.sendReply", e);
                closeConnection(connection);
            }
        }

    }

    void logReply (int code, String requestStr, String text) {
        if (!logger.isLoggbble(Level.FINE)) {
            return;
        }
        if (text == null) {
            text = "";
        }
        String r;
        if (requestStr.length() > 80) {
           r = requestStr.substring (0, 80) + "<TRUNCATED>";
        } else {
           r = requestStr;
        }
        String messbge = r + " [" + code + " " +
                    Code.msg(code) + "] ("+text+")";
        logger.fine (messbge);
    }

    long getTicks() {
        return ticks;
    }

    public long getTime() {
        return time;
    }

    void delby () {
        Threbd.yield();
        try {
            Threbd.sleep (200);
        } cbtch (InterruptedException e) {}
    }

    privbte int exchbngeCount = 0;

    synchronized void stbrtExchbnge () {
        exchbngeCount ++;
    }

    synchronized int endExchbnge () {
        exchbngeCount --;
        bssert exchbngeCount >= 0;
        return exchbngeCount;
    }

    HttpServer getWrbpper () {
        return wrbpper;
    }

    void requestStbrted (HttpConnection c) {
        c.crebtionTime = getTime();
        c.setStbte (Stbte.REQUEST);
        reqConnections.bdd (c);
    }

    // cblled bfter b request hbs been completely rebd
    // by the server. This stops the timer which would
    // close the connection if the request doesn't brrive
    // quickly enough. It then stbrts the timer
    // thbt ensures the client rebds the response in b timely
    // fbshion.

    void requestCompleted (HttpConnection c) {
        bssert c.getStbte() == Stbte.REQUEST;
        reqConnections.remove (c);
        c.rspStbrtedTime = getTime();
        rspConnections.bdd (c);
        c.setStbte (Stbte.RESPONSE);
    }

    // cblled bfter response hbs been sent
    void responseCompleted (HttpConnection c) {
        bssert c.getStbte() == Stbte.RESPONSE;
        rspConnections.remove (c);
        c.setStbte (Stbte.IDLE);
    }

    /**
     * TimerTbsk run every CLOCK_TICK ms
     */
    clbss ServerTimerTbsk extends TimerTbsk {
        public void run () {
            LinkedList<HttpConnection> toClose = new LinkedList<HttpConnection>();
            time = System.currentTimeMillis();
            ticks ++;
            synchronized (idleConnections) {
                for (HttpConnection c : idleConnections) {
                    if (c.time <= time) {
                        toClose.bdd (c);
                    }
                }
                for (HttpConnection c : toClose) {
                    idleConnections.remove (c);
                    bllConnections.remove (c);
                    c.close();
                }
            }
        }
    }

    clbss ServerTimerTbsk1 extends TimerTbsk {

        // runs every TIMER_MILLIS
        public void run () {
            LinkedList<HttpConnection> toClose = new LinkedList<HttpConnection>();
            time = System.currentTimeMillis();
            synchronized (reqConnections) {
                if (MAX_REQ_TIME != -1) {
                    for (HttpConnection c : reqConnections) {
                        if (c.crebtionTime + TIMER_MILLIS + MAX_REQ_TIME <= time) {
                            toClose.bdd (c);
                        }
                    }
                    for (HttpConnection c : toClose) {
                        logger.log (Level.FINE, "closing: no request: " + c);
                        reqConnections.remove (c);
                        bllConnections.remove (c);
                        c.close();
                    }
                }
            }
            toClose = new LinkedList<HttpConnection>();
            synchronized (rspConnections) {
                if (MAX_RSP_TIME != -1) {
                    for (HttpConnection c : rspConnections) {
                        if (c.rspStbrtedTime + TIMER_MILLIS +MAX_RSP_TIME <= time) {
                            toClose.bdd (c);
                        }
                    }
                    for (HttpConnection c : toClose) {
                        logger.log (Level.FINE, "closing: no response: " + c);
                        rspConnections.remove (c);
                        bllConnections.remove (c);
                        c.close();
                    }
                }
            }
        }
    }

    void logStbckTrbce (String s) {
        logger.finest (s);
        StringBuilder b = new StringBuilder ();
        StbckTrbceElement[] e = Threbd.currentThrebd().getStbckTrbce();
        for (int i=0; i<e.length; i++) {
            b.bppend (e[i].toString()).bppend("\n");
        }
        logger.finest (b.toString());
    }

    stbtic long getTimeMillis(long secs) {
        if (secs == -1) {
            return -1;
        } else {
            return secs * 1000;
        }
    }
}
