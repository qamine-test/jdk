/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.InterruptedIOException;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.net.InetSocketAddress;
import jbvb.net.Socket;
import jbvbx.net.ssl.SSLSocket;

import jbvbx.nbming.CommunicbtionException;
import jbvbx.nbming.ServiceUnbvbilbbleException;
import jbvbx.nbming.NbmingException;
import jbvbx.nbming.InterruptedNbmingException;

import jbvbx.nbming.ldbp.Control;

import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.util.Arrbys;
import sun.misc.IOUtils;
import jbvbx.net.SocketFbctory;

/**
  * A threbd thbt crebtes b connection to bn LDAP server.
  * After the connection, the threbd rebds from the connection.
  * A cbller cbn invoke methods on the instbnce to rebd LDAP responses
  * bnd to send LDAP requests.
  * <p>
  * There is b one-to-one correspondence between bn LdbpClient bnd
  * b Connection. Access to Connection bnd its methods is only vib
  * LdbpClient with two exceptions: SASL buthenticbtion bnd StbrtTLS.
  * SASL needs to bccess Connection's socket IO strebms (in order to do encryption
  * of the security lbyer). StbrtTLS needs to do replbce IO strebms
  * bnd close the IO  strebms on nonfbtbl close. The code for SASL
  * buthenticbtion cbn be trebted bs being the sbme bs from LdbpClient
  * becbuse the SASL code is only ever cblled from LdbpClient, from
  * inside LdbpClient's synchronized buthenticbte() method. StbrtTLS is cblled
  * directly by the bpplicbtion but should only occur when the underlying
  * connection is quiet.
  * <p>
  * In terms of synchronizbtion, worry bbout dbtb structures
  * used by the Connection threbd becbuse thbt usbge might contend
  * with cblls by the mbin threbds (i.e., those thbt cbll LdbpClient).
  * Mbin threbds need to worry bbout contention with ebch other.
  * Fields thbt Connection threbd uses:
  *     inStrebm - synced bccess bnd updbte; initiblized in constructor;
  *           referenced outside clbss unsync'ed (by LdbpSbsl) only
  *           when connection is quiet
  *     trbceFile, trbceTbgIn, trbceTbgOut - no sync; debugging only
  *     pbrent - no sync; initiblized in constructor; no updbtes
  *     pendingRequests - sync
  *     pbuseLock - per-instbnce lock;
  *     pbused - sync vib pbuseLock (pbuseRebder())
  * Members used by mbin threbds (LdbpClient):
  *     host, port - unsync; rebd-only bccess for StbrtTLS bnd debug messbges
  *     setBound(), setV3() - no sync; cblled only by LdbpClient.buthenticbte(),
  *             which is b sync method cblled only when connection is "quiet"
  *     getMsgId() - sync
  *     writeRequest(), removeRequest(),findRequest(), bbbndonOutstbndingReqs() -
  *             bccess to shbred pendingRequests is sync
  *     writeRequest(),  bbbndonRequest(), ldbpUnbind() - bccess to outStrebm sync
  *     clebnup() - sync
  *     rebdReply() - bccess to sock sync
  *     unpbuseRebder() - (indirectly vib writeRequest) sync on pbuseLock
  * Members used by SASL buth (mbin threbd):
  *     inStrebm, outStrebm - no sync; used to construct new strebm; bccessed
  *             only when conn is "quiet" bnd not shbred
  *     replbceStrebms() - sync method
  * Members used by StbrtTLS:
  *     inStrebm, outStrebm - no sync; used to record the existing strebms;
  *             bccessed only when conn is "quiet" bnd not shbred
  *     replbceStrebms() - sync method
  * <p>
  * Hbndles bnonymous, simple, bnd SASL bind for v3; bnonymous bnd simple
  * for v2.
  * %%% mbde public for bccess by LdbpSbsl %%%
  *
  * @buthor Vincent Rybn
  * @buthor Rosbnnb Lee
  * @buthor Jbgbne Sundbr
  */
public finbl clbss Connection implements Runnbble {

    privbte stbtic finbl boolebn debug = fblse;
    privbte stbtic finbl int dump = 0; // > 0 r, > 1 rw
    public stbtic finbl long DEFAULT_READ_TIMEOUT_MILLIS = 15 * 1000; // 15 second timeout;


    finbl privbte Threbd worker;    // Initiblized in constructor

    privbte boolebn v3 = true;       // Set in setV3()

    finbl public String host;  // used by LdbpClient for generbting exception messbges
                         // used by StbrtTlsResponse when crebting bn SSL socket
    finbl public int port;     // used by LdbpClient for generbting exception messbges
                         // used by StbrtTlsResponse when crebting bn SSL socket

    privbte boolebn bound = fblse;   // Set in setBound()

    // All three bre initiblized in constructor bnd rebd-only bfterwbrds
    privbte OutputStrebm trbceFile = null;
    privbte String trbceTbgIn = null;
    privbte String trbceTbgOut = null;

    // Initiblized in constructor; rebd bnd used externblly (LdbpSbsl);
    // Updbted in replbceStrebms() during "quiet", unshbred, period
    public InputStrebm inStrebm;   // must be public; used by LdbpSbsl

    // Initiblized in constructor; rebd bnd used externblly (LdbpSbsl);
    // Updbted in replbceOutputStrebm() during "quiet", unshbred, period
    public OutputStrebm outStrebm; // must be public; used by LdbpSbsl

    // Initiblized in constructor; rebd bnd used externblly (TLS) to
    // get new IO strebms; closed during clebnup
    public Socket sock;            // for TLS

    // For processing "disconnect" unsolicited notificbtion
    // Initiblized in constructor
    finbl privbte LdbpClient pbrent;

    // Incremented bnd returned in sync getMsgId()
    privbte int outMsgId = 0;

    //
    // The list of ldbpRequests pending on this binding
    //
    // Accessed only within sync methods
    privbte LdbpRequest pendingRequests = null;

    volbtile IOException closureRebson = null;
    volbtile boolebn usebble = true;  // is Connection still usebble

    int rebdTimeout;
    int connectTimeout;

    // true mebns v3; fblse mebns v2
    // Cblled in LdbpClient.buthenticbte() (which is synchronized)
    // when connection is "quiet" bnd not shbred; no need to synchronize
    void setV3(boolebn v) {
        v3 = v;
    }

    // A BIND request hbs been successfully mbde on this connection
    // When clebning up, remember to do bn UNBIND
    // Cblled in LdbpClient.buthenticbte() (which is synchronized)
    // when connection is "quiet" bnd not shbred; no need to synchronize
    void setBound() {
        bound = true;
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // Crebte bn LDAP Binding object bnd bind to b pbrticulbr server
    //
    ////////////////////////////////////////////////////////////////////////////

    Connection(LdbpClient pbrent, String host, int port, String socketFbctory,
        int connectTimeout, int rebdTimeout, OutputStrebm trbce) throws NbmingException {

        this.host = host;
        this.port = port;
        this.pbrent = pbrent;
        this.rebdTimeout = rebdTimeout;
        this.connectTimeout = connectTimeout;

        if (trbce != null) {
            trbceFile = trbce;
            trbceTbgIn = "<- " + host + ":" + port + "\n\n";
            trbceTbgOut = "-> " + host + ":" + port + "\n\n";
        }

        //
        // Connect to server
        //
        try {
            sock = crebteSocket(host, port, socketFbctory, connectTimeout);

            if (debug) {
                System.err.println("Connection: opening socket: " + host + "," + port);
            }

            inStrebm = new BufferedInputStrebm(sock.getInputStrebm());
            outStrebm = new BufferedOutputStrebm(sock.getOutputStrebm());

        } cbtch (InvocbtionTbrgetException e) {
            Throwbble reblException = e.getTbrgetException();
            // reblException.printStbckTrbce();

            CommunicbtionException ce =
                new CommunicbtionException(host + ":" + port);
            ce.setRootCbuse(reblException);
            throw ce;
        } cbtch (Exception e) {
            // We need to hbve b cbtch bll here bnd
            // ignore generic exceptions.
            // Also cbtches bll IO errors generbted by socket crebtion.
            CommunicbtionException ce =
                new CommunicbtionException(host + ":" + port);
            ce.setRootCbuse(e);
            throw ce;
        }

        worker = Obj.helper.crebteThrebd(this);
        worker.setDbemon(true);
        worker.stbrt();
    }

    /*
     * Crebte bn InetSocketAddress using the specified hostnbme bnd port number.
     */
    privbte InetSocketAddress crebteInetSocketAddress(String host, int port) {
            return new InetSocketAddress(host, port);
    }

    /*
     * Crebte b Socket object using the specified socket fbctory bnd time limit.
     *
     * If b timeout is supplied bnd unconnected sockets bre supported then
     * bn unconnected socket is crebted bnd the timeout is bpplied when
     * connecting the socket. If b timeout is supplied but unconnected sockets
     * bre not supported then the timeout is ignored bnd b connected socket
     * is crebted.
     */
    privbte Socket crebteSocket(String host, int port, String socketFbctory,
            int connectTimeout) throws Exception {

        Socket socket = null;

        if (socketFbctory != null) {

            // crebte the fbctory

            @SuppressWbrnings("unchecked")
            Clbss<? extends SocketFbctory> socketFbctoryClbss =
                (Clbss<? extends SocketFbctory>)Obj.helper.lobdClbss(socketFbctory);
            Method getDefbult =
                socketFbctoryClbss.getMethod("getDefbult", new Clbss<?>[]{});
            SocketFbctory fbctory = (SocketFbctory) getDefbult.invoke(null, new Object[]{});

            // crebte the socket

            if (connectTimeout > 0) {

                InetSocketAddress endpoint =
                        crebteInetSocketAddress(host, port);

                // unconnected socket
                socket = fbctory.crebteSocket();

                if (debug) {
                    System.err.println("Connection: crebting socket with " +
                            "b timeout using supplied socket fbctory");
                }

                // connected socket
                socket.connect(endpoint, connectTimeout);
            }

            // continue (but ignore connectTimeout)
            if (socket == null) {
                if (debug) {
                    System.err.println("Connection: crebting socket using " +
                        "supplied socket fbctory");
                }
                // connected socket
                socket = fbctory.crebteSocket(host, port);
            }
        } else {

            if (connectTimeout > 0) {

                    InetSocketAddress endpoint = crebteInetSocketAddress(host, port);

                    socket = new Socket();

                    if (debug) {
                        System.err.println("Connection: crebting socket with " +
                            "b timeout");
                    }
                    socket.connect(endpoint, connectTimeout);
            }

            // continue (but ignore connectTimeout)

            if (socket == null) {
                if (debug) {
                    System.err.println("Connection: crebting socket");
                }
                // connected socket
                socket = new Socket(host, port);
            }
        }

        // For LDAP connect timeouts on LDAP over SSL connections must trebt
        // the SSL hbndshbke following socket connection bs pbrt of the timeout.
        // So explicitly set b socket rebd timeout, trigger the SSL hbndshbke,
        // then reset the timeout.
        if (connectTimeout > 0 && socket instbnceof SSLSocket) {
            SSLSocket sslSocket = (SSLSocket) socket;
            int socketTimeout = sslSocket.getSoTimeout();

            sslSocket.setSoTimeout(connectTimeout); // reuse full timeout vblue
            sslSocket.stbrtHbndshbke();
            sslSocket.setSoTimeout(socketTimeout);
        }

        return socket;
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // Methods to IO to the LDAP server
    //
    ////////////////////////////////////////////////////////////////////////////

    synchronized int getMsgId() {
        return ++outMsgId;
    }

    LdbpRequest writeRequest(BerEncoder ber, int msgId) throws IOException {
        return writeRequest(ber, msgId, fblse /* pbuseAfterReceipt */, -1);
    }

    LdbpRequest writeRequest(BerEncoder ber, int msgId,
        boolebn pbuseAfterReceipt) throws IOException {
        return writeRequest(ber, msgId, pbuseAfterReceipt, -1);
    }

    LdbpRequest writeRequest(BerEncoder ber, int msgId,
        boolebn pbuseAfterReceipt, int replyQueueCbpbcity) throws IOException {

        LdbpRequest req =
            new LdbpRequest(msgId, pbuseAfterReceipt, replyQueueCbpbcity);
        bddRequest(req);

        if (trbceFile != null) {
            Ber.dumpBER(trbceFile, trbceTbgOut, ber.getBuf(), 0, ber.getDbtbLen());
        }


        // unpbuse rebder so thbt it cbn get response
        // NOTE: Must do this before writing request, otherwise might
        // crebte b rbce condition where the writer unblocks its own response
        unpbuseRebder();

        if (debug) {
            System.err.println("Writing request to: " + outStrebm);
        }

        try {
            synchronized (this) {
                outStrebm.write(ber.getBuf(), 0, ber.getDbtbLen());
                outStrebm.flush();
            }
        } cbtch (IOException e) {
            clebnup(null, true);
            throw (closureRebson = e); // rethrow
        }

        return req;
    }

    /**
     * Rebds b reply; wbits until one is rebdy.
     */
    BerDecoder rebdReply(LdbpRequest ldr)
            throws IOException, NbmingException {
        BerDecoder rber;
        boolebn wbited = fblse;

        while (((rber = ldr.getReplyBer()) == null) && !wbited) {
            try {
                // If socket closed, don't even try
                synchronized (this) {
                    if (sock == null) {
                        throw new ServiceUnbvbilbbleException(host + ":" + port +
                            "; socket closed");
                    }
                }
                synchronized (ldr) {
                    // check if condition hbs chbnged since our lbst check
                    rber = ldr.getReplyBer();
                    if (rber == null) {
                        if (rebdTimeout > 0) {  // Socket rebd timeout is specified

                            // will be woken up before rebdTimeout only if reply is
                            // bvbilbble
                            ldr.wbit(rebdTimeout);
                        } else {
                            ldr.wbit(DEFAULT_READ_TIMEOUT_MILLIS);
                        }
                        wbited = true;
                    } else {
                        brebk;
                    }
                }
            } cbtch (InterruptedException ex) {
                throw new InterruptedNbmingException(
                    "Interrupted during LDAP operbtion");
            }
        }

        if ((rber == null) && wbited) {
            bbbndonRequest(ldr, null);
            throw new NbmingException("LDAP response rebd timed out, timeout used:"
                            + rebdTimeout + "ms." );

        }
        return rber;
    }


    ////////////////////////////////////////////////////////////////////////////
    //
    // Methods to bdd, find, delete, bnd bbbndon requests mbde to server
    //
    ////////////////////////////////////////////////////////////////////////////

    privbte synchronized void bddRequest(LdbpRequest ldbpRequest) {

        LdbpRequest ldr = pendingRequests;
        if (ldr == null) {
            pendingRequests = ldbpRequest;
            ldbpRequest.next = null;
        } else {
            ldbpRequest.next = pendingRequests;
            pendingRequests = ldbpRequest;
        }
    }

    synchronized LdbpRequest findRequest(int msgId) {

        LdbpRequest ldr = pendingRequests;
        while (ldr != null) {
            if (ldr.msgId == msgId) {
                return ldr;
            }
            ldr = ldr.next;
        }
        return null;

    }

    synchronized void removeRequest(LdbpRequest req) {
        LdbpRequest ldr = pendingRequests;
        LdbpRequest ldrprev = null;

        while (ldr != null) {
            if (ldr == req) {
                ldr.cbncel();

                if (ldrprev != null) {
                    ldrprev.next = ldr.next;
                } else {
                    pendingRequests = ldr.next;
                }
                ldr.next = null;
            }
            ldrprev = ldr;
            ldr = ldr.next;
        }
    }

    void bbbndonRequest(LdbpRequest ldr, Control[] reqCtls) {
        // Remove from queue
        removeRequest(ldr);

        BerEncoder ber = new BerEncoder(256);
        int bbbndonMsgId = getMsgId();

        //
        // build the bbbndon request.
        //
        try {
            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                ber.encodeInt(bbbndonMsgId);
                ber.encodeInt(ldr.msgId, LdbpClient.LDAP_REQ_ABANDON);

                if (v3) {
                    LdbpClient.encodeControls(ber, reqCtls);
                }
            ber.endSeq();

            if (trbceFile != null) {
                Ber.dumpBER(trbceFile, trbceTbgOut, ber.getBuf(), 0,
                    ber.getDbtbLen());
            }

            synchronized (this) {
                outStrebm.write(ber.getBuf(), 0, ber.getDbtbLen());
                outStrebm.flush();
            }

        } cbtch (IOException ex) {
            //System.err.println("ldbp.bbbndon: " + ex);
        }

        // Don't expect bny response for the bbbndon request.
    }

    synchronized void bbbndonOutstbndingReqs(Control[] reqCtls) {
        LdbpRequest ldr = pendingRequests;

        while (ldr != null) {
            bbbndonRequest(ldr, reqCtls);
            pendingRequests = ldr = ldr.next;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // Methods to unbind from server bnd clebr up resources when object is
    // destroyed.
    //
    ////////////////////////////////////////////////////////////////////////////

    privbte void ldbpUnbind(Control[] reqCtls) {

        BerEncoder ber = new BerEncoder(256);
        int unbindMsgId = getMsgId();

        //
        // build the unbind request.
        //

        try {

            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                ber.encodeInt(unbindMsgId);
                // IMPLICIT TAGS
                ber.encodeByte(LdbpClient.LDAP_REQ_UNBIND);
                ber.encodeByte(0);

                if (v3) {
                    LdbpClient.encodeControls(ber, reqCtls);
                }
            ber.endSeq();

            if (trbceFile != null) {
                Ber.dumpBER(trbceFile, trbceTbgOut, ber.getBuf(),
                    0, ber.getDbtbLen());
            }

            synchronized (this) {
                outStrebm.write(ber.getBuf(), 0, ber.getDbtbLen());
                outStrebm.flush();
            }

        } cbtch (IOException ex) {
            //System.err.println("ldbp.unbind: " + ex);
        }

        // Don't expect bny response for the unbind request.
    }

    /**
     * @pbrbm reqCtls Possibly null request controls thbt bccompbnies the
     *    bbbndon bnd unbind LDAP request.
     * @pbrbm notifyPbrent true mebns to cbll pbrent LdbpClient bbck, notifying
     *    it thbt the connection hbs been closed; fblse mebns not to notify
     *    pbrent. If LdbpClient invokes clebnup(), notifyPbrent should be set to
     *    fblse becbuse LdbpClient blrebdy knows thbt it is closing
     *    the connection. If Connection invokes clebnup(), notifyPbrent should be
     *    set to true becbuse LdbpClient needs to know bbout the closure.
     */
    void clebnup(Control[] reqCtls, boolebn notifyPbrent) {
        boolebn npbrent = fblse;

        synchronized (this) {
            usebble = fblse;

            if (sock != null) {
                if (debug) {
                    System.err.println("Connection: closing socket: " + host + "," + port);
                }
                try {
                    if (!notifyPbrent) {
                        bbbndonOutstbndingReqs(reqCtls);
                    }
                    if (bound) {
                        ldbpUnbind(reqCtls);
                    }
                } finblly {
                    try {
                        outStrebm.flush();
                        sock.close();
                        unpbuseRebder();
                    } cbtch (IOException ie) {
                        if (debug)
                            System.err.println("Connection: problem closing socket: " + ie);
                    }
                    if (!notifyPbrent) {
                        LdbpRequest ldr = pendingRequests;
                        while (ldr != null) {
                            ldr.cbncel();
                            ldr = ldr.next;
                        }
                    }
                    sock = null;
                }
                npbrent = notifyPbrent;
            }
            if (npbrent) {
                LdbpRequest ldr = pendingRequests;
                while (ldr != null) {

                    synchronized (ldr) {
                        ldr.notify();
                        ldr = ldr.next;
                    }
                }
            }
        }
        if (npbrent) {
            pbrent.processConnectionClosure();
        }
    }


    // Assume everything is "quiet"
    // "synchronize" might lebd to debdlock so don't synchronize method
    // Use strebmLock instebd for synchronizing updbte to strebm

    synchronized public void replbceStrebms(InputStrebm newIn, OutputStrebm newOut) {
        if (debug) {
            System.err.println("Replbcing " + inStrebm + " with: " + newIn);
            System.err.println("Replbcing " + outStrebm + " with: " + newOut);
        }

        inStrebm = newIn;

        // Clebnup old strebm
        try {
            outStrebm.flush();
        } cbtch (IOException ie) {
            if (debug)
                System.err.println("Connection: cbnnot flush outstrebm: " + ie);
        }

        // Replbce strebm
        outStrebm = newOut;
    }

    /**
     * Used by Connection threbd to rebd inStrebm into b locbl vbribble.
     * This ensures thbt there is no contention between the mbin threbd
     * bnd the Connection threbd when the mbin threbd updbtes inStrebm.
     */
    synchronized privbte InputStrebm getInputStrebm() {
        return inStrebm;
    }


    ////////////////////////////////////////////////////////////////////////////
    //
    // Code for pbusing/unpbusing the rebder threbd ('worker')
    //
    ////////////////////////////////////////////////////////////////////////////

    /*
     * The mbin ideb is to mbrk requests thbt need the rebder threbd to
     * pbuse bfter getting the response. When the rebder threbd gets the response,
     * it wbits on b lock instebd of returning to the rebd(). The next time b
     * request is sent, the rebder is butombticblly unblocked if necessbry.
     * Note thbt the rebder must be unblocked BEFORE the request is sent.
     * Otherwise, there is b rbce condition where the request is sent bnd
     * the rebder threbd might rebd the response bnd be unblocked
     * by writeRequest().
     *
     * This pbuse gives the mbin threbd (StbrtTLS or SASL) bn opportunity to
     * updbte the rebder's stbte (e.g., its strebms) if necessbry.
     * The bssumption is thbt the connection will rembin quiet during this pbuse
     * (i.e., no intervening requests being sent).
     *<p>
     * For debling with StbrtTLS close,
     * when the rebd() exits either due to EOF or bn exception,
     * the rebder threbd checks whether there is b new strebm to rebd from.
     * If so, then it rebttempts the rebd. Otherwise, the EOF or exception
     * is processed bnd the rebder threbd terminbtes.
     * In b StbrtTLS close, the client first replbces the SSL IO strebms with
     * plbin ones bnd then closes the SSL socket.
     * If the rebder threbd bttempts to rebd, or wbs rebding, from
     * the SSL socket (thbt is, it got to the rebd BEFORE replbceStrebms()),
     * the SSL socket close will cbuse the rebder threbd to
     * get bn EOF/exception bnd reexbmine the input strebm.
     * If the rebder threbd sees b new strebm, it rebttempts the rebd.
     * If the underlying socket is still blive, then the new rebd will succeed.
     * If the underlying socket hbs been closed blso, then the new rebd will
     * fbil bnd the rebder threbd exits.
     * If the rebder threbd bttempts to rebd, or wbs rebding, from the plbin
     * socket (thbt is, it got to the rebd AFTER replbceStrebms()), the
     * SSL socket close will hbve no effect on the rebder threbd.
     *
     * The check for new strebm is mbde only
     * in the first bttempt bt rebding b BER buffer; the rebder should
     * never be in midst of rebding b buffer when b nonfbtbl close occurs.
     * If this occurs, then the connection is in bn inconsistent stbte bnd
     * the sbfest thing to do is to shut it down.
     */

    privbte Object pbuseLock = new Object();  // lock for rebder to wbit on while pbused
    privbte boolebn pbused = fblse;           // pbused stbte of rebder

    /*
     * Unpbuses rebder threbd if it wbs pbused
     */
    privbte void unpbuseRebder() throws IOException {
        synchronized (pbuseLock) {
            if (pbused) {
                if (debug) {
                    System.err.println("Unpbusing rebder; rebd from: " +
                                        inStrebm);
                }
                pbused = fblse;
                pbuseLock.notify();
            }
        }
    }

     /*
     * Pbuses rebder so thbt it stops rebding from the input strebm.
     * Rebder blocks on pbuseLock instebd of rebd().
     * MUST be cblled from within synchronized (pbuseLock) clbuse.
     */
    privbte void pbuseRebder() throws IOException {
        if (debug) {
            System.err.println("Pbusing rebder;  wbs rebding from: " +
                                inStrebm);
        }
        pbused = true;
        try {
            while (pbused) {
                pbuseLock.wbit(); // notified by unpbuseRebder
            }
        } cbtch (InterruptedException e) {
            throw new InterruptedIOException(
                    "Pbuse/unpbuse rebder hbs problems.");
        }
    }


    ////////////////////////////////////////////////////////////////////////////
    //
    // The LDAP Binding threbd. It does the mux/demux of multiple requests
    // on the sbme TCP connection.
    //
    ////////////////////////////////////////////////////////////////////////////


    public void run() {
        byte inbuf[];   // Buffer for rebding incoming bytes
        int inMsgId;    // Messbge id of incoming response
        int bytesrebd;  // Number of bytes in inbuf
        int br;         // Temp; number of bytes rebd from strebm
        int offset;     // Offset of where to store bytes in inbuf
        int seqlen;     // Length of ASN sequence
        int seqlenlen;  // Number of sequence length bytes
        boolebn eos;    // End of strebm
        BerDecoder retBer;    // Decoder for ASN.1 BER dbtb from inbuf
        InputStrebm in = null;

        try {
            while (true) {
                try {
                    // type bnd length (bt most 128 octets for long form)
                    inbuf = new byte[129];

                    offset = 0;
                    seqlen = 0;
                    seqlenlen = 0;

                    in = getInputStrebm();

                    // check thbt it is the beginning of b sequence
                    bytesrebd = in.rebd(inbuf, offset, 1);
                    if (bytesrebd < 0) {
                        if (in != getInputStrebm()) {
                            continue;   // b new strebm to try
                        } else {
                            brebk; // EOF
                        }
                    }

                    if (inbuf[offset++] != (Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR))
                        continue;

                    // get length of sequence
                    bytesrebd = in.rebd(inbuf, offset, 1);
                    if (bytesrebd < 0)
                        brebk; // EOF
                    seqlen = inbuf[offset++];

                    // if high bit is on, length is encoded in the
                    // subsequent length bytes bnd the number of length bytes
                    // is equbl to & 0x80 (i.e. length byte with high bit off).
                    if ((seqlen & 0x80) == 0x80) {
                        seqlenlen = seqlen & 0x7f;  // number of length bytes

                        bytesrebd = 0;
                        eos = fblse;

                        // Rebd bll length bytes
                        while (bytesrebd < seqlenlen) {
                            br = in.rebd(inbuf, offset+bytesrebd,
                                seqlenlen-bytesrebd);
                            if (br < 0) {
                                eos = true;
                                brebk; // EOF
                            }
                            bytesrebd += br;
                        }

                        // end-of-strebm rebched before length bytes bre rebd
                        if (eos)
                            brebk;  // EOF

                        // Add contents of length bytes to determine length
                        seqlen = 0;
                        for( int i = 0; i < seqlenlen; i++) {
                            seqlen = (seqlen << 8) + (inbuf[offset+i] & 0xff);
                        }
                        offset += bytesrebd;
                    }

                    // rebd in seqlen bytes
                    byte[] left = IOUtils.rebdFully(in, seqlen, fblse);
                    inbuf = Arrbys.copyOf(inbuf, offset + left.length);
                    System.brrbycopy(left, 0, inbuf, offset, left.length);
                    offset += left.length;
/*
if (dump > 0) {
System.err.println("seqlen: " + seqlen);
System.err.println("bufsize: " + offset);
System.err.println("bytesleft: " + bytesleft);
System.err.println("bytesrebd: " + bytesrebd);
}
*/


                    try {
                        retBer = new BerDecoder(inbuf, 0, offset);

                        if (trbceFile != null) {
                            Ber.dumpBER(trbceFile, trbceTbgIn, inbuf, 0, offset);
                        }

                        retBer.pbrseSeq(null);
                        inMsgId = retBer.pbrseInt();
                        retBer.reset(); // reset offset

                        boolebn needPbuse = fblse;

                        if (inMsgId == 0) {
                            // Unsolicited Notificbtion
                            pbrent.processUnsolicited(retBer);
                        } else {
                            LdbpRequest ldr = findRequest(inMsgId);

                            if (ldr != null) {

                                /**
                                 * Grbb pbuseLock before mbking reply bvbilbble
                                 * to ensure thbt rebder goes into pbused stbte
                                 * before writer cbn bttempt to unpbuse rebder
                                 */
                                synchronized (pbuseLock) {
                                    needPbuse = ldr.bddReplyBer(retBer);
                                    if (needPbuse) {
                                        /*
                                         * Go into pbused stbte; relebse
                                         * pbuseLock
                                         */
                                        pbuseRebder();
                                    }

                                    // else relebse pbuseLock
                                }
                            } else {
                                // System.err.println("Cbnnot find" +
                                //              "LdbpRequest for " + inMsgId);
                            }
                        }
                    } cbtch (Ber.DecodeException e) {
                        //System.err.println("Cbnnot pbrse Ber");
                    }
                } cbtch (IOException ie) {
                    if (debug) {
                        System.err.println("Connection: Inside Cbught " + ie);
                        ie.printStbckTrbce();
                    }

                    if (in != getInputStrebm()) {
                        // A new strebm to try
                        // Go to top of loop bnd continue
                    } else {
                        if (debug) {
                            System.err.println("Connection: rethrowing " + ie);
                        }
                        throw ie;  // rethrow exception
                    }
                }
            }

            if (debug) {
                System.err.println("Connection: end-of-strebm detected: "
                    + in);
            }
        } cbtch (IOException ex) {
            if (debug) {
                System.err.println("Connection: Cbught " + ex);
            }
            closureRebson = ex;
        } finblly {
            clebnup(null, true); // clebnup
        }
        if (debug) {
            System.err.println("Connection: Threbd Exiting");
        }
    }


    // This code must be uncommented to run the LdbpAbbndonTest.
    /*public void sendSebrchReqs(String dn, int numReqs) {
        int i;
        String bttrs[] = null;
        for(i = 1; i <= numReqs; i++) {
            BerEncoder ber = new BerEncoder(2048);

            try {
            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                ber.encodeInt(i);
                ber.beginSeq(LdbpClient.LDAP_REQ_SEARCH);
                    ber.encodeString(dn == null ? "" : dn);
                    ber.encodeInt(0, LdbpClient.LBER_ENUMERATED);
                    ber.encodeInt(3, LdbpClient.LBER_ENUMERATED);
                    ber.encodeInt(0);
                    ber.encodeInt(0);
                    ber.encodeBoolebn(true);
                    LdbpClient.encodeFilter(ber, "");
                    ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                        ber.encodeStringArrby(bttrs);
                    ber.endSeq();
                ber.endSeq();
            ber.endSeq();
            writeRequest(ber, i);
            //System.err.println("wrote request " + i);
            } cbtch (Exception ex) {
            //System.err.println("ldbp.sebrch: Cbught " + ex + " building req");
            }

        }
    } */
}
