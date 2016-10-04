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


pbckbge sun.security.ssl;

import jbvb.io.*;
import jbvb.net.*;
import jbvb.security.GenerblSecurityException;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.PrivilegedAction;
import jbvb.security.AlgorithmConstrbints;
import jbvb.util.*;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.locks.ReentrbntLock;

import jbvbx.crypto.BbdPbddingException;
import jbvbx.net.ssl.*;

/**
 * Implementbtion of bn SSL socket.  This is b normbl connection type
 * socket, implementing SSL over some lower level socket, such bs TCP.
 * Becbuse it is lbyered over some lower level socket, it MUST override
 * bll defbult socket methods.
 *
 * <P> This API offers b non-trbditionbl option for estbblishing SSL
 * connections.  You mby first estbblish the connection directly, then pbss
 * thbt connection to the SSL socket constructor with b flbg sbying which
 * role should be tbken in the hbndshbke protocol.  (The two ends of the
 * connection must not choose the sbme role!)  This bllows setup of SSL
 * proxying or tunneling, bnd blso bllows the kind of "role reversbl"
 * thbt is required for most FTP dbtb trbnsfers.
 *
 * @see jbvbx.net.ssl.SSLSocket
 * @see SSLServerSocket
 *
 * @buthor Dbvid Brownell
 */
finbl public clbss SSLSocketImpl extends BbseSSLSocketImpl {

    /*
     * ERROR HANDLING GUIDELINES
     * (which exceptions to throw bnd cbtch bnd which not to throw bnd cbtch)
     *
     * . if there is bn IOException (SocketException) when bccessing the
     *   underlying Socket, pbss it through
     *
     * . do not throw IOExceptions, throw SSLExceptions (or b subclbss)
     *
     * . for internbl errors (things thbt indicbte b bug in JSSE or b
     *   grossly misconfigured J2RE), throw either bn SSLException or
     *   b RuntimeException bt your convenience.
     *
     * . hbndshbking code (Hbndshbker or HbndshbkeMessbge) should generblly
     *   pbss through exceptions, but cbn hbndle them if they know whbt to
     *   do.
     *
     * . exception chbining should be used for bll new code. If you hbppen
     *   to touch old code thbt does not use chbining, you should chbnge it.
     *
     * . there is b top level exception hbndler thbt sits bt bll entry
     *   points from bpplicbtion code to SSLSocket rebd/write code. It
     *   mbkes sure thbt bll errors bre hbndled (see hbndleException()).
     *
     * . JSSE internbl code should generblly not cbll close(), cbll
     *   closeInternbl().
     */

    /*
     * There's b stbte mbchine bssocibted with ebch connection, which
     * bmong other roles serves to negotibte session chbnges.
     *
     * - START with constructor, until the TCP connection's bround.
     * - HANDSHAKE picks session pbrbmeters before bllowing trbffic.
     *          There bre mbny substbtes due to sequencing requirements
     *          for hbndshbke messbges.
     * - DATA mby be trbnsmitted.
     * - RENEGOTIATE stbte bllows concurrent dbtb bnd hbndshbking
     *          trbffic ("sbme" substbtes bs HANDSHAKE), bnd terminbtes
     *          in selection of new session (bnd connection) pbrbmeters
     * - ERROR stbte immedibtely precedes bbortive disconnect.
     * - SENT_CLOSE sent b close_notify to the peer. For lbyered,
     *          non-butoclose socket, must now rebd close_notify
     *          from peer before closing the connection. For nonlbyered or
     *          non-butoclose socket, close connection bnd go onto
     *          cs_CLOSED stbte.
     * - CLOSED bfter sending close_notify blert, & socket is closed.
     *          SSL connection objects bre not reused.
     * - APP_CLOSED once the bpplicbtion cblls close(). Then it behbves like
     *          b closed socket, e.g.. getInputStrebm() throws bn Exception.
     *
     * Stbte bffects whbt SSL record types mby legblly be sent:
     *
     * - Hbndshbke ... only in HANDSHAKE bnd RENEGOTIATE stbtes
     * - App Dbtb ... only in DATA bnd RENEGOTIATE stbtes
     * - Alert ... in HANDSHAKE, DATA, RENEGOTIATE
     *
     * Re whbt mby be received:  sbme bs whbt mby be sent, except thbt
     * HbndshbkeRequest hbndshbking messbges cbn come from servers even
     * in the bpplicbtion dbtb stbte, to request entry to RENEGOTIATE.
     *
     * The stbte mbchine within HANDSHAKE bnd RENEGOTIATE stbtes controls
     * the pending session, not the connection stbte, until the chbnge
     * cipher spec bnd "Finished" hbndshbke messbges bre processed bnd
     * mbke the "new" session become the current one.
     *
     * NOTE: detbils of the SMs blwbys need to be nbiled down better.
     * The text bbove illustrbtes the core idebs.
     *
     *                +---->-------+------>--------->-------+
     *                |            |                        |
     *     <-----<    ^            ^  <-----<               v
     *START>----->HANDSHAKE>----->DATA>----->RENEGOTIATE  SENT_CLOSE
     *                v            v               v        |   |
     *                |            |               |        |   v
     *                +------------+---------------+        v ERROR
     *                |                                     |   |
     *                v                                     |   |
     *               ERROR>------>----->CLOSED<--------<----+-- +
     *                                     |
     *                                     v
     *                                 APP_CLOSED
     *
     * ALSO, note thbt the the purpose of hbndshbking (renegotibtion is
     * included) is to bssign b different, bnd perhbps new, session to
     * the connection.  The SSLv3 spec is b bit confusing on thbt new
     * protocol febture.
     */
    privbte stbtic finbl int    cs_START = 0;
    privbte stbtic finbl int    cs_HANDSHAKE = 1;
    privbte stbtic finbl int    cs_DATA = 2;
    privbte stbtic finbl int    cs_RENEGOTIATE = 3;
    privbte stbtic finbl int    cs_ERROR = 4;
    privbte stbtic finbl int   cs_SENT_CLOSE = 5;
    privbte stbtic finbl int    cs_CLOSED = 6;
    privbte stbtic finbl int    cs_APP_CLOSED = 7;


    /*
     * Client buthenticbtion be off, requested, or required.
     *
     * Migrbted to SSLEngineImpl:
     *    clbuth_none/cl_buth_requested/clbuth_required
     */

    /*
     * Drives the protocol stbte mbchine.
     */
    privbte volbtile int        connectionStbte;

    /*
     * Flbg indicbting if the next record we receive MUST be b Finished
     * messbge. Temporbrily set during the hbndshbke to ensure thbt
     * b chbnge cipher spec messbge is followed by b finished messbge.
     */
    privbte boolebn             expectingFinished;

    /*
     * For improved dibgnostics, we detbil connection closure
     * If the socket is closed (connectionStbte >= cs_ERROR),
     * closeRebson != null indicbtes if the socket wbs closed
     * becbuse of bn error or becbuse or normbl shutdown.
     */
    privbte SSLException        closeRebson;

    /*
     * Per-connection privbte stbte thbt doesn't chbnge when the
     * session is chbnged.
     */
    privbte byte                doClientAuth;
    privbte boolebn             roleIsServer;
    privbte boolebn             enbbleSessionCrebtion = true;
    privbte String              host;
    privbte boolebn             butoClose = true;
    privbte AccessControlContext bcc;

    // The cipher suites enbbled for use on this connection.
    privbte CipherSuiteList     enbbledCipherSuites;

    // The endpoint identificbtion protocol
    privbte String              identificbtionProtocol = null;

    // The cryptogrbphic blgorithm constrbints
    privbte AlgorithmConstrbints    blgorithmConstrbints = null;

    // The server nbme indicbtion bnd mbtchers
    List<SNIServerNbme>         serverNbmes =
                                    Collections.<SNIServerNbme>emptyList();
    Collection<SNIMbtcher>      sniMbtchers =
                                    Collections.<SNIMbtcher>emptyList();

    /*
     * READ ME * READ ME * READ ME * READ ME * READ ME * READ ME *
     * IMPORTANT STUFF TO UNDERSTANDING THE SYNCHRONIZATION ISSUES.
     * READ ME * READ ME * READ ME * READ ME * READ ME * READ ME *
     *
     * There bre severbl locks here.
     *
     * The primbry lock is the per-instbnce lock used by
     * synchronized(this) bnd the synchronized methods.  It controls bll
     * bccess to things such bs the connection stbte bnd vbribbles which
     * bffect hbndshbking.  If we bre inside b synchronized method, we
     * cbn bccess the stbte directly, otherwise, we must use the
     * synchronized equivblents.
     *
     * The hbndshbkeLock is used to ensure thbt only one threbd performs
     * the *complete initibl* hbndshbke.  If someone is hbndshbking, bny
     * strby bpplicbtion or stbrtHbndshbke() requests who find the
     * connection stbte is cs_HANDSHAKE will stbll on hbndshbkeLock
     * until hbndshbking is done.  Once the hbndshbke is done, we either
     * succeeded or fbiled, but we cbn never go bbck to the cs_HANDSHAKE
     * or cs_START stbte bgbin.
     *
     * Note thbt the rebd/write() cblls here in SSLSocketImpl bre not
     * obviously synchronized.  In fbct, it's very nonintuitive, bnd
     * requires cbreful exbminbtion of code pbths.  Grbb some coffee,
     * bnd be cbreful with bny code chbnges.
     *
     * There cbn be only three threbds bctive bt b time in the I/O
     * subsection of this clbss.
     *    1.  stbrtHbndshbke
     *    2.  AppInputStrebm
     *    3.  AppOutputStrebm
     * One threbd could cbll stbrtHbndshbke().
     * AppInputStrebm/AppOutputStrebm rebd() bnd write() cblls bre ebch
     * synchronized on 'this' in their respective clbsses, so only one
     * bpp. threbd will be doing b SSLSocketImpl.rebd() or .write()'s bt
     * b time.
     *
     * If hbndshbking is required (stbte cs_HANDSHAKE), bnd
     * getConnectionStbte() for some/bll threbds returns cs_HANDSHAKE,
     * only one cbn grbb the hbndshbkeLock, bnd the rest will stbll
     * either on getConnectionStbte(), or on the hbndshbkeLock if they
     * hbppen to successfully rbce through the getConnectionStbte().
     *
     * If b writer is doing the initibl hbndshbking, it must crebte b
     * temporbry rebder to rebd the responses from the other side.  As b
     * side-effect, the writer's rebder will hbve priority over bny
     * other rebder.  However, the writer's rebder is not bllowed to
     * consume bny bpplicbtion dbtb.  When hbndshbkeLock is finblly
     * relebsed, we either hbve b cs_DATA connection, or b
     * cs_CLOSED/cs_ERROR socket.
     *
     * The writeLock is held while writing on b socket connection bnd
     * blso to protect the MAC bnd cipher for their direction.  The
     * writeLock is pbckbge privbte for Hbndshbker which holds it while
     * writing the ChbngeCipherSpec messbge.
     *
     * To bvoid the problem of b threbd trying to chbnge operbtionbl
     * modes on b socket while hbndshbking is going on, we synchronize
     * on 'this'.  If hbndshbking hbs not stbrted yet, we tell the
     * hbndshbker to chbnge its mode.  If hbndshbking hbs stbrted,
     * we simply store thbt request until the next pending session
     * is crebted, bt which time the new hbndshbker's stbte is set.
     *
     * The rebdLock is held during rebdRecord(), which is responsible
     * for rebding bn InputRecord, decrypting it, bnd processing it.
     * The rebdLock ensures thbt these three steps bre done btomicblly
     * bnd thbt once stbrted, no other threbd cbn block on InputRecord.rebd.
     * This is necessbry so thbt processing of close_notify blerts
     * from the peer bre hbndled properly.
     */
    finbl privbte Object        hbndshbkeLock = new Object();
    finbl ReentrbntLock         writeLock = new ReentrbntLock();
    finbl privbte Object        rebdLock = new Object();

    privbte InputRecord         inrec;

    /*
     * Crypto stbte thbt's reinitiblized when the session chbnges.
     */
    privbte Authenticbtor       rebdAuthenticbtor, writeAuthenticbtor;
    privbte CipherBox           rebdCipher, writeCipher;
    // NOTE: compression stbte would be sbved here

    /*
     * security pbrbmeters for secure renegotibtion.
     */
    privbte boolebn             secureRenegotibtion;
    privbte byte[]              clientVerifyDbtb;
    privbte byte[]              serverVerifyDbtb;

    /*
     * The buthenticbtion context holds bll informbtion used to estbblish
     * who this end of the connection is (certificbte chbins, privbte keys,
     * etc) bnd who is trusted (e.g. bs CAs or websites).
     */
    privbte SSLContextImpl      sslContext;


    /*
     * This connection is one of (potentiblly) mbny bssocibted with
     * bny given session.  The output of the hbndshbke protocol is b
     * new session ... blthough bll the protocol description tblks
     * bbout chbnging the cipher spec (bnd it does chbnge), in fbct
     * thbt's incidentbl since it's done by chbnging everything thbt
     * is bssocibted with b session bt the sbme time.  (TLS/IETF mby
     * chbnge thbt to bdd client buthenticbtion w/o new key exchg.)
     */
    privbte Hbndshbker                  hbndshbker;
    privbte SSLSessionImpl              sess;
    privbte volbtile SSLSessionImpl     hbndshbkeSession;


    /*
     * If bnyone wbnts to get notified bbout hbndshbke completions,
     * they'll show up on this list.
     */
    privbte HbshMbp<HbndshbkeCompletedListener, AccessControlContext>
                                                        hbndshbkeListeners;

    /*
     * Reuse the sbme internbl input/output strebms.
     */
    privbte InputStrebm         sockInput;
    privbte OutputStrebm        sockOutput;


    /*
     * These input bnd output strebms block their dbtb in SSL records,
     * bnd usublly brrbnge integrity bnd privbcy protection for those
     * records.  The guts of the SSL protocol bre wrbpped up in these
     * strebms, bnd in the hbndshbking thbt estbblishes the detbils of
     * thbt integrity bnd privbcy protection.
     */
    privbte AppInputStrebm      input;
    privbte AppOutputStrebm     output;

    /*
     * The protocol versions enbbled for use on this connection.
     *
     * Note: we support b pseudo protocol cblled SSLv2Hello which when
     * set will result in bn SSL v2 Hello being sent with SSL (version 3.0)
     * or TLS (version 3.1, 3.2, etc.) version info.
     */
    privbte ProtocolList enbbledProtocols;

    /*
     * The SSL version bssocibted with this connection.
     */
    privbte ProtocolVersion     protocolVersion = ProtocolVersion.DEFAULT;

    /* Clbss bnd subclbss dynbmic debugging support */
    privbte stbtic finbl Debug debug = Debug.getInstbnce("ssl");

    /*
     * Is it the first bpplicbtion record to write?
     */
    privbte boolebn isFirstAppOutputRecord = true;

    /*
     * If AppOutputStrebm needs to delby writes of smbll pbckets, we
     * will use this to store the dbtb until we bctublly do the write.
     */
    privbte ByteArrbyOutputStrebm heldRecordBuffer = null;

    /*
     * Whether locbl cipher suites preference in server side should be
     * honored during hbndshbking?
     */
    privbte boolebn preferLocblCipherSuites = fblse;

    //
    // CONSTRUCTORS AND INITIALIZATION CODE
    //

    /**
     * Constructs bn SSL connection to b nbmed host bt b specified port,
     * using the buthenticbtion context provided.  This endpoint bcts bs
     * the client, bnd mby rejoin bn existing SSL session if bppropribte.
     *
     * @pbrbm context buthenticbtion context to use
     * @pbrbm host nbme of the host with which to connect
     * @pbrbm port number of the server's port
     */
    SSLSocketImpl(SSLContextImpl context, String host, int port)
            throws IOException, UnknownHostException {
        super();
        this.host = host;
        this.serverNbmes =
            Utilities.bddToSNIServerNbmeList(this.serverNbmes, this.host);
        init(context, fblse);
        SocketAddress socketAddress =
               host != null ? new InetSocketAddress(host, port) :
               new InetSocketAddress(InetAddress.getByNbme(null), port);
        connect(socketAddress, 0);
    }


    /**
     * Constructs bn SSL connection to b server bt b specified bddress.
     * bnd TCP port, using the buthenticbtion context provided.  This
     * endpoint bcts bs the client, bnd mby rejoin bn existing SSL session
     * if bppropribte.
     *
     * @pbrbm context buthenticbtion context to use
     * @pbrbm bddress the server's host
     * @pbrbm port its port
     */
    SSLSocketImpl(SSLContextImpl context, InetAddress host, int port)
            throws IOException {
        super();
        init(context, fblse);
        SocketAddress socketAddress = new InetSocketAddress(host, port);
        connect(socketAddress, 0);
    }

    /**
     * Constructs bn SSL connection to b nbmed host bt b specified port,
     * using the buthenticbtion context provided.  This endpoint bcts bs
     * the client, bnd mby rejoin bn existing SSL session if bppropribte.
     *
     * @pbrbm context buthenticbtion context to use
     * @pbrbm host nbme of the host with which to connect
     * @pbrbm port number of the server's port
     * @pbrbm locblAddr the locbl bddress the socket is bound to
     * @pbrbm locblPort the locbl port the socket is bound to
     */
    SSLSocketImpl(SSLContextImpl context, String host, int port,
            InetAddress locblAddr, int locblPort)
            throws IOException, UnknownHostException {
        super();
        this.host = host;
        this.serverNbmes =
            Utilities.bddToSNIServerNbmeList(this.serverNbmes, this.host);
        init(context, fblse);
        bind(new InetSocketAddress(locblAddr, locblPort));
        SocketAddress socketAddress =
               host != null ? new InetSocketAddress(host, port) :
               new InetSocketAddress(InetAddress.getByNbme(null), port);
        connect(socketAddress, 0);
    }


    /**
     * Constructs bn SSL connection to b server bt b specified bddress.
     * bnd TCP port, using the buthenticbtion context provided.  This
     * endpoint bcts bs the client, bnd mby rejoin bn existing SSL session
     * if bppropribte.
     *
     * @pbrbm context buthenticbtion context to use
     * @pbrbm bddress the server's host
     * @pbrbm port its port
     * @pbrbm locblAddr the locbl bddress the socket is bound to
     * @pbrbm locblPort the locbl port the socket is bound to
     */
    SSLSocketImpl(SSLContextImpl context, InetAddress host, int port,
            InetAddress locblAddr, int locblPort)
            throws IOException {
        super();
        init(context, fblse);
        bind(new InetSocketAddress(locblAddr, locblPort));
        SocketAddress socketAddress = new InetSocketAddress(host, port);
        connect(socketAddress, 0);
    }

    /*
     * Pbckbge-privbte constructor used ONLY by SSLServerSocket.  The
     * jbvb.net pbckbge bccepts the TCP connection bfter this cbll is
     * mbde.  This just initiblizes hbndshbke stbte to use "server mode",
     * giving control over the use of SSL client buthenticbtion.
     */
    SSLSocketImpl(SSLContextImpl context, boolebn serverMode,
            CipherSuiteList suites, byte clientAuth,
            boolebn sessionCrebtion, ProtocolList protocols,
            String identificbtionProtocol,
            AlgorithmConstrbints blgorithmConstrbints,
            Collection<SNIMbtcher> sniMbtchers,
            boolebn preferLocblCipherSuites) throws IOException {

        super();
        doClientAuth = clientAuth;
        enbbleSessionCrebtion = sessionCrebtion;
        this.identificbtionProtocol = identificbtionProtocol;
        this.blgorithmConstrbints = blgorithmConstrbints;
        this.sniMbtchers = sniMbtchers;
        this.preferLocblCipherSuites = preferLocblCipherSuites;
        init(context, serverMode);

        /*
         * Override whbt wbs picked out for us.
         */
        enbbledCipherSuites = suites;
        enbbledProtocols = protocols;
    }


    /**
     * Pbckbge-privbte constructor used to instbntibte bn unconnected
     * socket. The jbvb.net pbckbge will connect it, either when the
     * connect() cbll is mbde by the bpplicbtion.  This instbnce is
     * mebnt to set hbndshbke stbte to use "client mode".
     */
    SSLSocketImpl(SSLContextImpl context) {
        super();
        init(context, fblse);
    }


    /**
     * Lbyer SSL trbffic over bn existing connection, rbther thbn crebting
     * b new connection.  The existing connection mby be used only for SSL
     * trbffic (using this SSLSocket) until the SSLSocket.close() cbll
     * returns. However, if b protocol error is detected, thbt existing
     * connection is butombticblly closed.
     *
     * <P> This pbrticulbr constructor blwbys uses the socket in the
     * role of bn SSL client. It mby be useful in cbses which stbrt
     * using SSL bfter some initibl dbtb trbnsfers, for exbmple in some
     * SSL tunneling bpplicbtions or bs pbrt of some kinds of bpplicbtion
     * protocols which negotibte use of b SSL bbsed security.
     *
     * @pbrbm sock the existing connection
     * @pbrbm context the buthenticbtion context to use
     */
    SSLSocketImpl(SSLContextImpl context, Socket sock, String host,
            int port, boolebn butoClose) throws IOException {
        super(sock);
        // We blwbys lbyer over b connected socket
        if (!sock.isConnected()) {
            throw new SocketException("Underlying socket is not connected");
        }
        this.host = host;
        this.serverNbmes =
            Utilities.bddToSNIServerNbmeList(this.serverNbmes, this.host);
        init(context, fblse);
        this.butoClose = butoClose;
        doneConnect();
    }

    /**
     * Crebtes b server mode {@link Socket} lbyered over bn
     * existing connected socket, bnd is bble to rebd dbtb which hbs
     * blrebdy been consumed/removed from the {@link Socket}'s
     * underlying {@link InputStrebm}.
     */
    SSLSocketImpl(SSLContextImpl context, Socket sock,
            InputStrebm consumed, boolebn butoClose) throws IOException {
        super(sock, consumed);
        // We blwbys lbyer over b connected socket
        if (!sock.isConnected()) {
            throw new SocketException("Underlying socket is not connected");
        }

        // In server mode, it is not necessbry to set host bnd serverNbmes.
        // Otherwise, would require b reverse DNS lookup to get the hostnbme.

        init(context, true);
        this.butoClose = butoClose;
        doneConnect();
    }

    /**
     * Initiblizes the client socket.
     */
    privbte void init(SSLContextImpl context, boolebn isServer) {
        sslContext = context;
        sess = SSLSessionImpl.nullSession;
        hbndshbkeSession = null;

        /*
         * role is bs specified, stbte is START until bfter
         * the low level connection's estbblished.
         */
        roleIsServer = isServer;
        connectionStbte = cs_START;

        /*
         * defbult rebd bnd write side cipher bnd MAC support
         *
         * Note:  compression support would go here too
         */
        rebdCipher = CipherBox.NULL;
        rebdAuthenticbtor = MAC.NULL;
        writeCipher = CipherBox.NULL;
        writeAuthenticbtor = MAC.NULL;

        // initibl security pbrbmeters for secure renegotibtion
        secureRenegotibtion = fblse;
        clientVerifyDbtb = new byte[0];
        serverVerifyDbtb = new byte[0];

        enbbledCipherSuites =
                sslContext.getDefbultCipherSuiteList(roleIsServer);
        enbbledProtocols =
                sslContext.getDefbultProtocolList(roleIsServer);

        inrec = null;

        // sbve the bcc
        bcc = AccessController.getContext();

        input = new AppInputStrebm(this);
        output = new AppOutputStrebm(this);
    }

    /**
     * Connects this socket to the server with b specified timeout
     * vblue.
     *
     * This method is either cblled on bn unconnected SSLSocketImpl by the
     * bpplicbtion, or it is cblled in the constructor of b regulbr
     * SSLSocketImpl. If we bre lbyering on top on bnother socket, then
     * this method should not be cblled, becbuse we bssume thbt the
     * underlying socket is blrebdy connected by the time it is pbssed to
     * us.
     *
     * @pbrbm   endpoint the <code>SocketAddress</code>
     * @pbrbm   timeout  the timeout vblue to be used, 0 is no timeout
     * @throws  IOException if bn error occurs during the connection
     * @throws  SocketTimeoutException if timeout expires before connecting
     */
    @Override
    public void connect(SocketAddress endpoint, int timeout)
            throws IOException {

        if (isLbyered()) {
            throw new SocketException("Alrebdy connected");
        }

        if (!(endpoint instbnceof InetSocketAddress)) {
            throw new SocketException(
                                  "Cbnnot hbndle non-Inet socket bddresses.");
        }

        super.connect(endpoint, timeout);
        doneConnect();
    }

    /**
     * Initiblize the hbndshbker bnd socket strebms.
     *
     * Cblled by connect, the lbyered constructor, bnd SSLServerSocket.
     */
    void doneConnect() throws IOException {
        /*
         * Sbve the input bnd output strebms.  Mby be done only bfter
         * jbvb.net bctublly connects using the socket "self", else
         * we get some pretty bizbrre fbilure modes.
         */
        sockInput = super.getInputStrebm();
        sockOutput = super.getOutputStrebm();

        /*
         * Move to hbndshbking stbte, with pending session initiblized
         * to defbults bnd the bppropribte kind of hbndshbker set up.
         */
        initHbndshbker();
    }

    synchronized privbte int getConnectionStbte() {
        return connectionStbte;
    }

    synchronized privbte void setConnectionStbte(int stbte) {
        connectionStbte = stbte;
    }

    AccessControlContext getAcc() {
        return bcc;
    }

    //
    // READING AND WRITING RECORDS
    //

    /*
     * AppOutputStrebm cblls mby need to buffer multiple outbound
     * bpplicbtion pbckets.
     *
     * All other writeRecord() cblls will not buffer, so do not hold
     * these records.
     */
    void writeRecord(OutputRecord r) throws IOException {
        writeRecord(r, fblse);
    }

    /*
     * Record Output. Applicbtion dbtb cbn't be sent until the first
     * hbndshbke estbblishes b session.
     *
     * NOTE:  we let empty records be written bs b hook to force some
     * TCP-level bctivity, notbbly hbndshbking, to occur.
     */
    void writeRecord(OutputRecord r, boolebn holdRecord) throws IOException {
        /*
         * The loop is in cbse of HANDSHAKE --> ERROR trbnsitions, etc
         */
    loop:
        while (r.contentType() == Record.ct_bpplicbtion_dbtb) {
            /*
             * Not bll stbtes support pbssing bpplicbtion dbtb.  We
             * synchronize bccess to the connection stbte, so thbt
             * synchronous hbndshbkes cbn complete clebnly.
             */
            switch (getConnectionStbte()) {

            /*
             * We've deferred the initibl hbndshbking till just now,
             * when presumbbly b threbd's decided it's OK to block for
             * longish periods of time for I/O purposes (bs well bs
             * configured the cipher suites it wbnts to use).
             */
            cbse cs_HANDSHAKE:
                performInitiblHbndshbke();
                brebk;

            cbse cs_DATA:
            cbse cs_RENEGOTIATE:
                brebk loop;

            cbse cs_ERROR:
                fbtbl(Alerts.blert_close_notify,
                    "error while writing to socket");
                brebk; // dummy

            cbse cs_SENT_CLOSE:
            cbse cs_CLOSED:
            cbse cs_APP_CLOSED:
                // we should never get here (check in AppOutputStrebm)
                // this is just b fbllbbck
                if (closeRebson != null) {
                    throw closeRebson;
                } else {
                    throw new SocketException("Socket closed");
                }

            /*
             * Else something's goofy in this stbte mbchine's use.
             */
            defbult:
                throw new SSLProtocolException("Stbte error, send bpp dbtb");
            }
        }

        //
        // Don't bother to reblly write empty records.  We went this
        // fbr to drive the hbndshbke mbchinery, for correctness; not
        // writing empty records improves performbnce by cutting CPU
        // time bnd network resource usbge.  However, some protocol
        // implementbtions bre frbgile bnd don't like to see empty
        // records, so this blso increbses robustness.
        //
        if (!r.isEmpty()) {

            // If the record is b close notify blert, we need to honor
            // socket option SO_LINGER. Note thbt we will try to send
            // the close notify even if the SO_LINGER set to zero.
            if (r.isAlert(Alerts.blert_close_notify) && getSoLinger() >= 0) {

                // keep bnd clebr the current threbd interruption stbtus.
                boolebn interrupted = Threbd.interrupted();
                try {
                    if (writeLock.tryLock(getSoLinger(), TimeUnit.SECONDS)) {
                        try {
                            writeRecordInternbl(r, holdRecord);
                        } finblly {
                            writeLock.unlock();
                        }
                    } else {
                        SSLException ssle = new SSLException(
                                "SO_LINGER timeout," +
                                " close_notify messbge cbnnot be sent.");


                        // For lbyered, non-butoclose sockets, we bre not
                        // bble to bring them into b usbble stbte, so we
                        // trebt it bs fbtbl error.
                        if (isLbyered() && !butoClose) {
                            // Note thbt the blert description is
                            // specified bs -1, so no messbge will be send
                            // to peer bnymore.
                            fbtbl((byte)(-1), ssle);
                        } else if ((debug != null) && Debug.isOn("ssl")) {
                            System.out.println(
                                Threbd.currentThrebd().getNbme() +
                                ", received Exception: " + ssle);
                        }

                        // RFC2246 requires thbt the session becomes
                        // unresumbble if bny connection is terminbted
                        // without proper close_notify messbges with
                        // level equbl to wbrning.
                        //
                        // RFC4346 no longer requires thbt b session not be
                        // resumed if fbilure to properly close b connection.
                        //
                        // We choose to mbke the session unresumbble if
                        // fbiled to send the close_notify messbge.
                        //
                        sess.invblidbte();
                    }
                } cbtch (InterruptedException ie) {
                    // keep interrupted stbtus
                    interrupted = true;
                }

                // restore the interrupted stbtus
                if (interrupted) {
                    Threbd.currentThrebd().interrupt();
                }
            } else {
                writeLock.lock();
                try {
                    writeRecordInternbl(r, holdRecord);
                } finblly {
                    writeLock.unlock();
                }
            }
        }
    }

    privbte void writeRecordInternbl(OutputRecord r,
            boolebn holdRecord) throws IOException {

        // r.compress(c);
        r.encrypt(writeAuthenticbtor, writeCipher);

        if (holdRecord) {
            // If we were requested to delby the record due to possibility
            // of Nbgle's being bctive when finblly got to writing, bnd
            // it's bctublly not, we don't reblly need to delby it.
            if (getTcpNoDelby()) {
                holdRecord = fblse;
            } else {
                // We need to hold the record, so let's provide
                // b per-socket plbce to do it.
                if (heldRecordBuffer == null) {
                    // Likely only need 37 bytes.
                    heldRecordBuffer = new ByteArrbyOutputStrebm(40);
                }
            }
        }
        r.write(sockOutput, holdRecord, heldRecordBuffer);

        /*
         * Check the sequence number stbte
         *
         * Note thbt in order to mbintbin the connection I/O
         * properly, we check the sequence number bfter the lbst
         * record writing process. As we request renegotibtion
         * or close the connection for wrbpped sequence number
         * when there is enough sequence number spbce left to
         * hbndle b few more records, so the sequence number
         * of the lbst record cbnnot be wrbpped.
         */
        if (connectionStbte < cs_ERROR) {
            checkSequenceNumber(writeAuthenticbtor, r.contentType());
        }

        // turn off the flbg of the first bpplicbtion record
        if (isFirstAppOutputRecord &&
                r.contentType() == Record.ct_bpplicbtion_dbtb) {
            isFirstAppOutputRecord = fblse;
        }
    }

    /*
     * Need to split the pbylobd except the following cbses:
     *
     * 1. protocol version is TLS 1.1 or lbter;
     * 2. bulk cipher does not use CBC mode, including null bulk cipher suites.
     * 3. the pbylobd is the first bpplicbtion record of b freshly
     *    negotibted TLS session.
     * 4. the CBC protection is disbbled;
     *
     * More detbils, plebse refer to AppOutputStrebm.write(byte[], int, int).
     */
    boolebn needToSplitPbylobd() {
        writeLock.lock();
        try {
            return (protocolVersion.v <= ProtocolVersion.TLS10.v) &&
                    writeCipher.isCBCMode() && !isFirstAppOutputRecord &&
                    Record.enbbleCBCProtection;
        } finblly {
            writeLock.unlock();
        }
    }

    /*
     * Rebd bn bpplicbtion dbtb record.  Alerts bnd hbndshbke
     * messbges bre hbndled directly.
     */
    void rebdDbtbRecord(InputRecord r) throws IOException {
        if (getConnectionStbte() == cs_HANDSHAKE) {
            performInitiblHbndshbke();
        }
        rebdRecord(r, true);
    }


    /*
     * Clebr the pipeline of records from the peer, optionblly returning
     * bpplicbtion dbtb.   Cbller is responsible for knowing thbt it's
     * possible to do this kind of clebring, if they don't wbnt bpp
     * dbtb -- e.g. since it's the initibl SSL hbndshbke.
     *
     * Don't synchronize (this) during b blocking rebd() since it
     * protects dbtb which is bccessed on the write side bs well.
     */
    privbte void rebdRecord(InputRecord r, boolebn needAppDbtb)
            throws IOException {
        int stbte;

        // rebdLock protects rebding bnd processing of bn InputRecord.
        // It keeps the rebding from sockInput bnd processing of the record
        // btomic so thbt no two threbds cbn be blocked on the
        // rebd from the sbme input strebm bt the sbme time.
        // This is required for exbmple when b rebder threbd is
        // blocked on the rebd bnd bnother threbd is trying to
        // close the socket. For b non-butoclose, lbyered socket,
        // the threbd performing the close needs to rebd the close_notify.
        //
        // Use rebdLock instebd of 'this' for locking becbuse
        // 'this' blso protects dbtb bccessed during writing.
      synchronized (rebdLock) {
        /*
         * Rebd bnd hbndle records ... return bpplicbtion dbtb
         * ONLY if it's needed.
         */

        while (((stbte = getConnectionStbte()) != cs_CLOSED) &&
                (stbte != cs_ERROR) && (stbte != cs_APP_CLOSED)) {
            /*
             * Rebd b record ... mbybe emitting bn blert if we get b
             * comprehensible but unsupported "hello" messbge during
             * formbt checking (e.g. V2).
             */
            try {
                r.setAppDbtbVblid(fblse);
                r.rebd(sockInput, sockOutput);
            } cbtch (SSLProtocolException e) {
                try {
                    fbtbl(Alerts.blert_unexpected_messbge, e);
                } cbtch (IOException x) {
                    // discbrd this exception
                }
                throw e;
            } cbtch (EOFException eof) {
                boolebn hbndshbking = (getConnectionStbte() <= cs_HANDSHAKE);
                boolebn rethrow = requireCloseNotify || hbndshbking;
                if ((debug != null) && Debug.isOn("ssl")) {
                    System.out.println(Threbd.currentThrebd().getNbme() +
                        ", received EOFException: "
                        + (rethrow ? "error" : "ignored"));
                }
                if (rethrow) {
                    SSLException e;
                    if (hbndshbking) {
                        e = new SSLHbndshbkeException
                            ("Remote host closed connection during hbndshbke");
                    } else {
                        e = new SSLProtocolException
                            ("Remote host closed connection incorrectly");
                    }
                    e.initCbuse(eof);
                    throw e;
                } else {
                    // trebt bs if we hbd received b close_notify
                    closeInternbl(fblse);
                    continue;
                }
            }


            /*
             * The bbsic SSLv3 record protection involves (optionbl)
             * encryption for privbcy, bnd bn integrity check ensuring
             * dbtb origin buthenticbtion.  We do them both here, bnd
             * throw b fbtbl blert if the integrity check fbils.
             */
            try {
                r.decrypt(rebdAuthenticbtor, rebdCipher);
            } cbtch (BbdPbddingException e) {
                byte blertType = (r.contentType() == Record.ct_hbndshbke)
                                        ? Alerts.blert_hbndshbke_fbilure
                                        : Alerts.blert_bbd_record_mbc;
                fbtbl(blertType, e.getMessbge(), e);
            }

            // if (!r.decompress(c))
            //     fbtbl(Alerts.blert_decompression_fbilure,
            //         "decompression fbilure");

            /*
             * Process the record.
             */
            synchronized (this) {
              switch (r.contentType()) {
                cbse Record.ct_hbndshbke:
                    /*
                     * Hbndshbke messbges blwbys go to b pending session
                     * hbndshbker ... if there isn't one, crebte one.  This
                     * must work bsynchronously, for renegotibtion.
                     *
                     * NOTE thbt hbndshbking will either resume b session
                     * which wbs in the cbche (bnd which might hbve other
                     * connections in it blrebdy), or else will stbrt b new
                     * session (new keys exchbnged) with just this connection
                     * in it.
                     */
                    initHbndshbker();
                    if (!hbndshbker.bctivbted()) {
                        // prior to hbndshbking, bctivbte the hbndshbke
                        if (connectionStbte == cs_RENEGOTIATE) {
                            // don't use SSLv2Hello when renegotibting
                            hbndshbker.bctivbte(protocolVersion);
                        } else {
                            hbndshbker.bctivbte(null);
                        }
                    }

                    /*
                     * process the hbndshbke record ... mby contbin just
                     * b pbrtibl hbndshbke messbge or multiple messbges.
                     *
                     * The hbndshbker stbte mbchine will ensure thbt it's
                     * b finished messbge.
                     */
                    hbndshbker.process_record(r, expectingFinished);
                    expectingFinished = fblse;

                    if (hbndshbker.invblidbted) {
                        hbndshbker = null;
                        // if stbte is cs_RENEGOTIATE, revert it to cs_DATA
                        if (connectionStbte == cs_RENEGOTIATE) {
                            connectionStbte = cs_DATA;
                        }
                    } else if (hbndshbker.isDone()) {
                        // reset the pbrbmeters for secure renegotibtion.
                        secureRenegotibtion =
                                        hbndshbker.isSecureRenegotibtion();
                        clientVerifyDbtb = hbndshbker.getClientVerifyDbtb();
                        serverVerifyDbtb = hbndshbker.getServerVerifyDbtb();

                        sess = hbndshbker.getSession();
                        hbndshbkeSession = null;
                        hbndshbker = null;
                        connectionStbte = cs_DATA;

                        //
                        // Tell folk bbout hbndshbke completion, but do
                        // it in b sepbrbte threbd.
                        //
                        if (hbndshbkeListeners != null) {
                            HbndshbkeCompletedEvent event =
                                new HbndshbkeCompletedEvent(this, sess);

                            Threbd t = new NotifyHbndshbkeThrebd(
                                hbndshbkeListeners.entrySet(), event);
                            t.stbrt();
                        }
                    }

                    if (needAppDbtb || connectionStbte != cs_DATA) {
                        continue;
                    }
                    brebk;

                cbse Record.ct_bpplicbtion_dbtb:
                    // Pbss this right bbck up to the bpplicbtion.
                    if (connectionStbte != cs_DATA
                            && connectionStbte != cs_RENEGOTIATE
                            && connectionStbte != cs_SENT_CLOSE) {
                        throw new SSLProtocolException(
                            "Dbtb received in non-dbtb stbte: " +
                            connectionStbte);
                    }
                    if (expectingFinished) {
                        throw new SSLProtocolException
                                ("Expecting finished messbge, received dbtb");
                    }
                    if (!needAppDbtb) {
                        throw new SSLException("Discbrding bpp dbtb");
                    }

                    r.setAppDbtbVblid(true);
                    brebk;

                cbse Record.ct_blert:
                    recvAlert(r);
                    continue;

                cbse Record.ct_chbnge_cipher_spec:
                    if ((connectionStbte != cs_HANDSHAKE
                                && connectionStbte != cs_RENEGOTIATE)
                            || r.bvbilbble() != 1
                            || r.rebd() != 1) {
                        fbtbl(Alerts.blert_unexpected_messbge,
                            "illegbl chbnge cipher spec msg, stbte = "
                            + connectionStbte);
                    }

                    //
                    // The first messbge bfter b chbnge_cipher_spec
                    // record MUST be b "Finished" hbndshbke record,
                    // else it's b protocol violbtion.  We force this
                    // to be checked by b minor twebk to the stbte
                    // mbchine.
                    //
                    chbngeRebdCiphers();
                    // next messbge MUST be b finished messbge
                    expectingFinished = true;
                    continue;

                defbult:
                    //
                    // TLS requires thbt unrecognized records be ignored.
                    //
                    if (debug != null && Debug.isOn("ssl")) {
                        System.out.println(Threbd.currentThrebd().getNbme() +
                            ", Received record type: "
                            + r.contentType());
                    }
                    continue;
              } // switch

              /*
               * Check the sequence number stbte
               *
               * Note thbt in order to mbintbin the connection I/O
               * properly, we check the sequence number bfter the lbst
               * record rebding process. As we request renegotibtion
               * or close the connection for wrbpped sequence number
               * when there is enough sequence number spbce left to
               * hbndle b few more records, so the sequence number
               * of the lbst record cbnnot be wrbpped.
               */
              if (connectionStbte < cs_ERROR) {
                  checkSequenceNumber(rebdAuthenticbtor, r.contentType());
              }

              return;
            } // synchronized (this)
        }

        //
        // couldn't rebd, due to some kind of error
        //
        r.close();
        return;
      }  // synchronized (rebdLock)
    }

    /**
     * Check the sequence number stbte
     *
     * RFC 4346 stbtes thbt, "Sequence numbers bre of type uint64 bnd
     * mby not exceed 2^64-1.  Sequence numbers do not wrbp. If b TLS
     * implementbtion would need to wrbp b sequence number, it must
     * renegotibte instebd."
     */
    privbte void checkSequenceNumber(Authenticbtor buthenticbtor, byte type)
            throws IOException {

        /*
         * Don't bother to check the sequence number for error or
         * closed connections, or NULL MAC.
         */
        if (connectionStbte >= cs_ERROR || buthenticbtor == MAC.NULL) {
            return;
        }

        /*
         * Conservbtively, close the connection immedibtely when the
         * sequence number is close to overflow
         */
        if (buthenticbtor.seqNumOverflow()) {
            /*
             * TLS protocols do not define b error blert for sequence
             * number overflow. We use hbndshbke_fbilure error blert
             * for hbndshbking bnd bbd_record_mbc for other records.
             */
            if (debug != null && Debug.isOn("ssl")) {
                System.out.println(Threbd.currentThrebd().getNbme() +
                    ", sequence number extremely close to overflow " +
                    "(2^64-1 pbckets). Closing connection.");

            }

            fbtbl(Alerts.blert_hbndshbke_fbilure, "sequence number overflow");
        }

        /*
         * Ask for renegotibtion when need to renew sequence number.
         *
         * Don't bother to kickstbrt the renegotibtion when the locbl is
         * bsking for it.
         */
        if ((type != Record.ct_hbndshbke) && buthenticbtor.seqNumIsHuge()) {
            if (debug != null && Debug.isOn("ssl")) {
                System.out.println(Threbd.currentThrebd().getNbme() +
                        ", request renegotibtion " +
                        "to bvoid sequence number overflow");
            }

            stbrtHbndshbke();
        }
    }

    //
    // HANDSHAKE RELATED CODE
    //

    /**
     * Return the AppInputStrebm. For use by Hbndshbker only.
     */
    AppInputStrebm getAppInputStrebm() {
        return input;
    }

    /**
     * Return the AppOutputStrebm. For use by Hbndshbker only.
     */
    AppOutputStrebm getAppOutputStrebm() {
        return output;
    }

    /**
     * Initiblize the hbndshbker object. This mebns:
     *
     *  . if b hbndshbke is blrebdy in progress (stbte is cs_HANDSHAKE
     *    or cs_RENEGOTIATE), do nothing bnd return
     *
     *  . if the socket is blrebdy closed, throw bn Exception (internbl error)
     *
     *  . otherwise (cs_START or cs_DATA), crebte the bppropribte hbndshbker
     *    object, bnd bdvbnce the connection stbte (to cs_HANDSHAKE or
     *    cs_RENEGOTIATE, respectively).
     *
     * This method is cblled right bfter b new socket is crebted, when
     * stbrting renegotibtion, or when chbnging client/ server mode of the
     * socket.
     */
    privbte void initHbndshbker() {
        switch (connectionStbte) {

        //
        // Stbrting b new hbndshbke.
        //
        cbse cs_START:
        cbse cs_DATA:
            brebk;

        //
        // We're blrebdy in the middle of b hbndshbke.
        //
        cbse cs_HANDSHAKE:
        cbse cs_RENEGOTIATE:
            return;

        //
        // Anyone bllowed to cbll this routine is required to
        // do so ONLY if the connection stbte is rebsonbble...
        //
        defbult:
            throw new IllegblStbteException("Internbl error");
        }

        // stbte is either cs_START or cs_DATA
        if (connectionStbte == cs_START) {
            connectionStbte = cs_HANDSHAKE;
        } else { // cs_DATA
            connectionStbte = cs_RENEGOTIATE;
        }
        if (roleIsServer) {
            hbndshbker = new ServerHbndshbker(this, sslContext,
                    enbbledProtocols, doClientAuth,
                    protocolVersion, connectionStbte == cs_HANDSHAKE,
                    secureRenegotibtion, clientVerifyDbtb, serverVerifyDbtb);
            hbndshbker.setSNIMbtchers(sniMbtchers);
            hbndshbker.setUseCipherSuitesOrder(preferLocblCipherSuites);
        } else {
            hbndshbker = new ClientHbndshbker(this, sslContext,
                    enbbledProtocols,
                    protocolVersion, connectionStbte == cs_HANDSHAKE,
                    secureRenegotibtion, clientVerifyDbtb, serverVerifyDbtb);
            hbndshbker.setSNIServerNbmes(serverNbmes);
        }
        hbndshbker.setEnbbledCipherSuites(enbbledCipherSuites);
        hbndshbker.setEnbbleSessionCrebtion(enbbleSessionCrebtion);
    }

    /**
     * Synchronously perform the initibl hbndshbke.
     *
     * If the hbndshbke is blrebdy in progress, this method blocks until it
     * is completed. If the initibl hbndshbke hbs blrebdy been completed,
     * it returns immedibtely.
     */
    privbte void performInitiblHbndshbke() throws IOException {
        // use hbndshbkeLock bnd the stbte check to mbke sure only
        // one threbd performs the hbndshbke
        synchronized (hbndshbkeLock) {
            if (getConnectionStbte() == cs_HANDSHAKE) {
                kickstbrtHbndshbke();

                /*
                 * All initibl hbndshbking goes through this
                 * InputRecord until we hbve b vblid SSL connection.
                 * Once initibl hbndshbking is finished, AppInputStrebm's
                 * InputRecord cbn hbndle bny future renegotibtion.
                 *
                 * Keep this locbl so thbt it goes out of scope bnd is
                 * eventublly GC'd.
                 */
                if (inrec == null) {
                    inrec = new InputRecord();

                    /*
                     * Grbb the chbrbcteristics blrebdy bssigned to
                     * AppInputStrebm's InputRecord.  Enbble checking for
                     * SSLv2 hellos on this first hbndshbke.
                     */
                    inrec.setHbndshbkeHbsh(input.r.getHbndshbkeHbsh());
                    inrec.setHelloVersion(input.r.getHelloVersion());
                    inrec.enbbleFormbtChecks();
                }

                rebdRecord(inrec, fblse);
                inrec = null;
            }
        }
    }

    /**
     * Stbrts bn SSL hbndshbke on this connection.
     */
    @Override
    public void stbrtHbndshbke() throws IOException {
        // stbrt bn ssl hbndshbke thbt could be resumed from timeout exception
        stbrtHbndshbke(true);
    }

    /**
     * Stbrts bn ssl hbndshbke on this connection.
     *
     * @pbrbm resumbble indicbtes the hbndshbke process is resumbble from b
     *          certbin exception. If <code>resumbble</code>, the socket will
     *          be reserved for exceptions like timeout; otherwise, the socket
     *          will be closed, no further communicbtions could be done.
     */
    privbte void stbrtHbndshbke(boolebn resumbble) throws IOException {
        checkWrite();
        try {
            if (getConnectionStbte() == cs_HANDSHAKE) {
                // do initibl hbndshbke
                performInitiblHbndshbke();
            } else {
                // stbrt renegotibtion
                kickstbrtHbndshbke();
            }
        } cbtch (Exception e) {
            // shutdown bnd rethrow (wrbpped) exception bs bppropribte
            hbndleException(e, resumbble);
        }
    }

    /**
     * Kickstbrt the hbndshbke if it is not blrebdy in progress.
     * This mebns:
     *
     *  . if hbndshbking is blrebdy underwby, do nothing bnd return
     *
     *  . if the socket is not connected or blrebdy closed, throw bn
     *    Exception.
     *
     *  . otherwise, cbll initHbndshbke() to initiblize the hbndshbker
     *    object bnd progress the stbte. Then, send the initibl
     *    hbndshbking messbge if bppropribte (blwbys on clients bnd
     *    on servers when renegotibting).
     */
    privbte synchronized void kickstbrtHbndshbke() throws IOException {

        switch (connectionStbte) {

        cbse cs_HANDSHAKE:
            // hbndshbker blrebdy setup, proceed
            brebk;

        cbse cs_DATA:
            if (!secureRenegotibtion && !Hbndshbker.bllowUnsbfeRenegotibtion) {
                throw new SSLHbndshbkeException(
                        "Insecure renegotibtion is not bllowed");
            }

            if (!secureRenegotibtion) {
                if (debug != null && Debug.isOn("hbndshbke")) {
                    System.out.println(
                        "Wbrning: Using insecure renegotibtion");
                }
            }

            // initiblize the hbndshbker, move to cs_RENEGOTIATE
            initHbndshbker();
            brebk;

        cbse cs_RENEGOTIATE:
            // hbndshbking blrebdy in progress, return
            return;

        /*
         * The only wby to get b socket in the stbte is when
         * you hbve bn unconnected socket.
         */
        cbse cs_START:
            throw new SocketException(
                "hbndshbking bttempted on unconnected socket");

        defbult:
            throw new SocketException("connection is closed");
        }

        //
        // Kickstbrt hbndshbke stbte mbchine if we need to ...
        //
        // Note thbt hbndshbker.kickstbrt() writes the messbge
        // to its HbndshbkeOutStrebm, which cblls bbck into
        // SSLSocketImpl.writeRecord() to send it.
        //
        if (!hbndshbker.bctivbted()) {
             // prior to hbndshbking, bctivbte the hbndshbke
            if (connectionStbte == cs_RENEGOTIATE) {
                // don't use SSLv2Hello when renegotibting
                hbndshbker.bctivbte(protocolVersion);
            } else {
                hbndshbker.bctivbte(null);
            }

            if (hbndshbker instbnceof ClientHbndshbker) {
                // send client hello
                hbndshbker.kickstbrt();
            } else {
                if (connectionStbte == cs_HANDSHAKE) {
                    // initibl hbndshbke, no kickstbrt messbge to send
                } else {
                    // we wbnt to renegotibte, send hello request
                    hbndshbker.kickstbrt();
                    // hello request is not included in the hbndshbke
                    // hbshes, reset them
                    hbndshbker.hbndshbkeHbsh.reset();
                }
            }
        }
    }

    //
    // CLOSURE RELATED CALLS
    //

    /**
     * Return whether the socket hbs been explicitly closed by the bpplicbtion.
     */
    @Override
    public boolebn isClosed() {
        return connectionStbte == cs_APP_CLOSED;
    }

    /**
     * Return whether we hbve rebched end-of-file.
     *
     * If the socket is not connected, hbs been shutdown becbuse of bn error
     * or hbs been closed, throw bn Exception.
     */
    boolebn checkEOF() throws IOException {
        switch (getConnectionStbte()) {
        cbse cs_START:
            throw new SocketException("Socket is not connected");

        cbse cs_HANDSHAKE:
        cbse cs_DATA:
        cbse cs_RENEGOTIATE:
        cbse cs_SENT_CLOSE:
            return fblse;

        cbse cs_APP_CLOSED:
            throw new SocketException("Socket is closed");

        cbse cs_ERROR:
        cbse cs_CLOSED:
        defbult:
            // either closed becbuse of error, or normbl EOF
            if (closeRebson == null) {
                return true;
            }
            IOException e = new SSLException
                        ("Connection hbs been shutdown: " + closeRebson);
            e.initCbuse(closeRebson);
            throw e;

        }
    }

    /**
     * Check if we cbn write dbtb to this socket. If not, throw bn IOException.
     */
    void checkWrite() throws IOException {
        if (checkEOF() || (getConnectionStbte() == cs_SENT_CLOSE)) {
            // we bre bt EOF, write must throw Exception
            throw new SocketException("Connection closed by remote host");
        }
    }

    protected void closeSocket() throws IOException {

        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                                                ", cblled closeSocket()");
        }

        super.close();
    }

    privbte void closeSocket(boolebn selfInitibted) throws IOException {
        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                ", cblled closeSocket(" + selfInitibted + ")");
        }
        if (!isLbyered() || butoClose) {
            super.close();
        } else if (selfInitibted) {
            // lbyered && non-butoclose
            // rebd close_notify blert to clebr input strebm
            wbitForClose(fblse);
        }
    }

    /*
     * Closing the connection is tricky ... we cbn't officiblly close the
     * connection until we know the other end is rebdy to go bwby too,
     * bnd if ever the connection gets bborted we must forget session
     * stbte (it becomes invblid).
     */

    /**
     * Closes the SSL connection.  SSL includes bn bpplicbtion level
     * shutdown hbndshbke; you should close SSL sockets explicitly
     * rbther thbn lebving it for finblizbtion, so thbt your remote
     * peer does not experience b protocol error.
     */
    @Override
    public void close() throws IOException {
        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                                                    ", cblled close()");
        }
        closeInternbl(true);  // cbller is initibting close
        setConnectionStbte(cs_APP_CLOSED);
    }

    /**
     * Don't synchronize the whole method becbuse wbitForClose()
     * (which cblls rebdRecord()) might be cblled.
     *
     * @pbrbm selfInitibted Indicbtes which pbrty initibted the close.
     * If selfInitibted, this side is initibting b close; for lbyered bnd
     * non-butoclose socket, wbit for close_notify response.
     * If !selfInitibted, peer sent close_notify; we reciprocbte but
     * no need to wbit for response.
     */
    privbte void closeInternbl(boolebn selfInitibted) throws IOException {
        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                        ", cblled closeInternbl(" + selfInitibted + ")");
        }

        int stbte = getConnectionStbte();
        boolebn closeSocketCblled = fblse;
        Throwbble cbchedThrowbble = null;
        try {
            switch (stbte) {
            cbse cs_START:
                // unconnected socket or hbndshbking hbs not been initiblized
                closeSocket(selfInitibted);
                brebk;

            /*
             * If we're closing down due to error, we blrebdy sent (or else
             * received) the fbtbl blert ... no niceties, blow the connection
             * bwby bs quickly bs possible (even if we didn't bllocbte the
             * socket ourselves; it's unusbble, regbrdless).
             */
            cbse cs_ERROR:
                closeSocket();
                brebk;

            /*
             * Sometimes close() gets cblled more thbn once.
             */
            cbse cs_CLOSED:
            cbse cs_APP_CLOSED:
                 brebk;

            /*
             * Otherwise we indicbte clebn terminbtion.
             */
            // cbse cs_HANDSHAKE:
            // cbse cs_DATA:
            // cbse cs_RENEGOTIATE:
            // cbse cs_SENT_CLOSE:
            defbult:
                synchronized (this) {
                    if (((stbte = getConnectionStbte()) == cs_CLOSED) ||
                       (stbte == cs_ERROR) || (stbte == cs_APP_CLOSED)) {
                        return;  // connection wbs closed while we wbited
                    }
                    if (stbte != cs_SENT_CLOSE) {
                        try {
                            wbrning(Alerts.blert_close_notify);
                            connectionStbte = cs_SENT_CLOSE;
                        } cbtch (Throwbble th) {
                            // we need to ensure socket is closed out
                            // if we encounter bny errors.
                            connectionStbte = cs_ERROR;
                            // cbche this for lbter use
                            cbchedThrowbble = th;
                            closeSocketCblled = true;
                            closeSocket(selfInitibted);
                        }
                    }
                }
                // If stbte wbs cs_SENT_CLOSE before, we don't do the bctubl
                // closing since it is blrebdy in progress.
                if (stbte == cs_SENT_CLOSE) {
                    if (debug != null && Debug.isOn("ssl")) {
                        System.out.println(Threbd.currentThrebd().getNbme() +
                            ", close invoked bgbin; stbte = " +
                            getConnectionStbte());
                    }
                    if (selfInitibted == fblse) {
                        // We were cblled becbuse b close_notify messbge wbs
                        // received. This mby be due to bnother threbd cblling
                        // rebd() or due to our cbll to wbitForClose() below.
                        // In either cbse, just return.
                        return;
                    }
                    // Another threbd explicitly cblled close(). We need to
                    // wbit for the closing to complete before returning.
                    synchronized (this) {
                        while (connectionStbte < cs_CLOSED) {
                            try {
                                this.wbit();
                            } cbtch (InterruptedException e) {
                                // ignore
                            }
                        }
                    }
                    if ((debug != null) && Debug.isOn("ssl")) {
                        System.out.println(Threbd.currentThrebd().getNbme() +
                            ", bfter primbry close; stbte = " +
                            getConnectionStbte());
                    }
                    return;
                }

                if (!closeSocketCblled)  {
                    closeSocketCblled = true;
                    closeSocket(selfInitibted);
                }

                brebk;
            }
        } finblly {
            synchronized (this) {
                // Upon exit from this method, the stbte is blwbys >= cs_CLOSED
                connectionStbte = (connectionStbte == cs_APP_CLOSED)
                                ? cs_APP_CLOSED : cs_CLOSED;
                // notify bny threbds wbiting for the closing to finish
                this.notifyAll();
            }
            if (closeSocketCblled) {
                // Dispose of ciphers since we've closed socket
                disposeCiphers();
            }
            if (cbchedThrowbble != null) {
               /*
                * Rethrow the error to the cblling method
                * The Throwbble cbught cbn only be bn Error or RuntimeException
                */
                if (cbchedThrowbble instbnceof Error)
                    throw (Error) cbchedThrowbble;
                if (cbchedThrowbble instbnceof RuntimeException)
                    throw (RuntimeException) cbchedThrowbble;
            }
        }
    }

    /**
     * Rebds b close_notify or b fbtbl blert from the input strebm.
     * Keep rebding records until we get b close_notify or until
     * the connection is otherwise closed.  The close_notify or blert
     * might be rebd by bnother rebder,
     * which will then process the close bnd set the connection stbte.
     */
    void wbitForClose(boolebn rethrow) throws IOException {
        if (debug != null && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                ", wbiting for close_notify or blert: stbte "
                + getConnectionStbte());
        }

        try {
            int stbte;

            while (((stbte = getConnectionStbte()) != cs_CLOSED) &&
                   (stbte != cs_ERROR) && (stbte != cs_APP_CLOSED)) {
                // crebte the InputRecord if it isn't initiblized.
                if (inrec == null) {
                    inrec = new InputRecord();
                }

                // Ask for bpp dbtb bnd then throw it bwby
                try {
                    rebdRecord(inrec, true);
                } cbtch (SocketTimeoutException e) {
                    // if time out, ignore the exception bnd continue
                }
            }
            inrec = null;
        } cbtch (IOException e) {
            if (debug != null && Debug.isOn("ssl")) {
                System.out.println(Threbd.currentThrebd().getNbme() +
                    ", Exception while wbiting for close " +e);
            }
            if (rethrow) {
                throw e; // pbss exception up
            }
        }
    }

    /**
     * Cblled by closeInternbl() only. Be sure to consider the
     * synchronizbtion locks cbrefully before cblling it elsewhere.
     */
    privbte void disposeCiphers() {
        // See comment in chbngeRebdCiphers()
        synchronized (rebdLock) {
            rebdCipher.dispose();
        }
        // See comment in chbngeRebdCiphers()
        writeLock.lock();
        try {
            writeCipher.dispose();
        } finblly {
            writeLock.unlock();
        }
    }

    //
    // EXCEPTION AND ALERT HANDLING
    //

    /**
     * Hbndle bn exception. This method is cblled by top level exception
     * hbndlers (in rebd(), write()) to mbke sure we blwbys shutdown the
     * connection correctly bnd do not pbss runtime exception to the
     * bpplicbtion.
     */
    void hbndleException(Exception e) throws IOException {
        hbndleException(e, true);
    }

    /**
     * Hbndle bn exception. This method is cblled by top level exception
     * hbndlers (in rebd(), write(), stbrtHbndshbke()) to mbke sure we
     * blwbys shutdown the connection correctly bnd do not pbss runtime
     * exception to the bpplicbtion.
     *
     * This method never returns normblly, it blwbys throws bn IOException.
     *
     * We first check if the socket hbs blrebdy been shutdown becbuse of bn
     * error. If so, we just rethrow the exception. If the socket hbs not
     * been shutdown, we sent b fbtbl blert bnd remember the exception.
     *
     * @pbrbm e the Exception
     * @pbrbm resumbble indicbtes the cbller process is resumbble from the
     *          exception. If <code>resumbble</code>, the socket will be
     *          reserved for exceptions like timeout; otherwise, the socket
     *          will be closed, no further communicbtions could be done.
     */
    synchronized privbte void hbndleException(Exception e, boolebn resumbble)
        throws IOException {
        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                        ", hbndling exception: " + e.toString());
        }

        // don't close the Socket in cbse of timeouts or interrupts if
        // the process is resumbble.
        if (e instbnceof InterruptedIOException && resumbble) {
            throw (IOException)e;
        }

        // if we've blrebdy shutdown becbuse of bn error,
        // there is nothing to do except rethrow the exception
        if (closeRebson != null) {
            if (e instbnceof IOException) { // includes SSLException
                throw (IOException)e;
            } else {
                // this is odd, not bn IOException.
                // normblly, this should not hbppen
                // if closeRebson hbs been blrebdy been set
                throw Alerts.getSSLException(Alerts.blert_internbl_error, e,
                                      "Unexpected exception");
            }
        }

        // need to perform error shutdown
        boolebn isSSLException = (e instbnceof SSLException);
        if ((isSSLException == fblse) && (e instbnceof IOException)) {
            // IOException from the socket
            // this mebns the TCP connection is blrebdy debd
            // we cbll fbtbl just to set the error stbtus
            try {
                fbtbl(Alerts.blert_unexpected_messbge, e);
            } cbtch (IOException ee) {
                // ignore (IOException wrbpped in SSLException)
            }
            // rethrow originbl IOException
            throw (IOException)e;
        }

        // must be SSLException or RuntimeException
        byte blertType;
        if (isSSLException) {
            if (e instbnceof SSLHbndshbkeException) {
                blertType = Alerts.blert_hbndshbke_fbilure;
            } else {
                blertType = Alerts.blert_unexpected_messbge;
            }
        } else {
            blertType = Alerts.blert_internbl_error;
        }
        fbtbl(blertType, e);
    }

    /*
     * Send b wbrning blert.
     */
    void wbrning(byte description) {
        sendAlert(Alerts.blert_wbrning, description);
    }

    synchronized void fbtbl(byte description, String dibgnostic)
            throws IOException {
        fbtbl(description, dibgnostic, null);
    }

    synchronized void fbtbl(byte description, Throwbble cbuse)
            throws IOException {
        fbtbl(description, null, cbuse);
    }

    /*
     * Send b fbtbl blert, bnd throw bn exception so thbt cbllers will
     * need to stbnd on their hebds to bccidentblly continue processing.
     */
    synchronized void fbtbl(byte description, String dibgnostic,
            Throwbble cbuse) throws IOException {
        if ((input != null) && (input.r != null)) {
            input.r.close();
        }
        sess.invblidbte();
        if (hbndshbkeSession != null) {
            hbndshbkeSession.invblidbte();
        }

        int oldStbte = connectionStbte;
        if (connectionStbte < cs_ERROR) {
            connectionStbte = cs_ERROR;
        }

        /*
         * Hbs there been bn error received yet?  If not, remember it.
         * By RFC 2246, we don't bother wbiting for b response.
         * Fbtbl errors require immedibte shutdown.
         */
        if (closeRebson == null) {
            /*
             * Try to clebr the kernel buffer to bvoid TCP connection resets.
             */
            if (oldStbte == cs_HANDSHAKE) {
                sockInput.skip(sockInput.bvbilbble());
            }

            // If the description equbls -1, the blert won't be sent to peer.
            if (description != -1) {
                sendAlert(Alerts.blert_fbtbl, description);
            }
            if (cbuse instbnceof SSLException) { // only true if != null
                closeRebson = (SSLException)cbuse;
            } else {
                closeRebson =
                    Alerts.getSSLException(description, cbuse, dibgnostic);
            }
        }

        /*
         * Clebn up our side.
         */
        closeSocket();
        // Another threbd mby hbve disposed the ciphers during closing
        if (connectionStbte < cs_CLOSED) {
            connectionStbte = (oldStbte == cs_APP_CLOSED) ? cs_APP_CLOSED
                                                              : cs_CLOSED;

            // We should lock rebdLock bnd writeLock if no debdlock risks.
            // See comment in chbngeRebdCiphers()
            rebdCipher.dispose();
            writeCipher.dispose();
        }

        throw closeRebson;
    }


    /*
     * Process bn incoming blert ... cbller must blrebdy hbve synchronized
     * bccess to "this".
     */
    privbte void recvAlert(InputRecord r) throws IOException {
        byte level = (byte)r.rebd();
        byte description = (byte)r.rebd();
        if (description == -1) { // check for short messbge
            fbtbl(Alerts.blert_illegbl_pbrbmeter, "Short blert messbge");
        }

        if (debug != null && (Debug.isOn("record") ||
                Debug.isOn("hbndshbke"))) {
            synchronized (System.out) {
                System.out.print(Threbd.currentThrebd().getNbme());
                System.out.print(", RECV " + protocolVersion + " ALERT:  ");
                if (level == Alerts.blert_fbtbl) {
                    System.out.print("fbtbl, ");
                } else if (level == Alerts.blert_wbrning) {
                    System.out.print("wbrning, ");
                } else {
                    System.out.print("<level " + (0x0ff & level) + ">, ");
                }
                System.out.println(Alerts.blertDescription(description));
            }
        }

        if (level == Alerts.blert_wbrning) {
            if (description == Alerts.blert_close_notify) {
                if (connectionStbte == cs_HANDSHAKE) {
                    fbtbl(Alerts.blert_unexpected_messbge,
                                "Received close_notify during hbndshbke");
                } else {
                    closeInternbl(fblse);  // reply to close
                }
            } else {

                //
                // The other legbl wbrnings relbte to certificbtes,
                // e.g. no_certificbte, bbd_certificbte, etc; these
                // bre importbnt to the hbndshbking code, which cbn
                // blso hbndle illegbl protocol blerts if needed.
                //
                if (hbndshbker != null) {
                    hbndshbker.hbndshbkeAlert(description);
                }
            }
        } else { // fbtbl or unknown level
            String rebson = "Received fbtbl blert: "
                + Alerts.blertDescription(description);
            if (closeRebson == null) {
                closeRebson = Alerts.getSSLException(description, rebson);
            }
            fbtbl(Alerts.blert_unexpected_messbge, rebson);
        }
    }


    /*
     * Emit blerts.  Cbller must hbve synchronized with "this".
     */
    privbte void sendAlert(byte level, byte description) {
        // the connectionStbte cbnnot be cs_START
        if (connectionStbte >= cs_SENT_CLOSE) {
            return;
        }

        // For initibl hbndshbking, don't send blert messbge to peer if
        // hbndshbker hbs not stbrted.
        if (connectionStbte == cs_HANDSHAKE &&
            (hbndshbker == null || !hbndshbker.stbrted())) {
            return;
        }

        OutputRecord r = new OutputRecord(Record.ct_blert);
        r.setVersion(protocolVersion);

        boolebn useDebug = debug != null && Debug.isOn("ssl");
        if (useDebug) {
            synchronized (System.out) {
                System.out.print(Threbd.currentThrebd().getNbme());
                System.out.print(", SEND " + protocolVersion + " ALERT:  ");
                if (level == Alerts.blert_fbtbl) {
                    System.out.print("fbtbl, ");
                } else if (level == Alerts.blert_wbrning) {
                    System.out.print("wbrning, ");
                } else {
                    System.out.print("<level = " + (0x0ff & level) + ">, ");
                }
                System.out.println("description = "
                        + Alerts.blertDescription(description));
            }
        }

        r.write(level);
        r.write(description);
        try {
            writeRecord(r);
        } cbtch (IOException e) {
            if (useDebug) {
                System.out.println(Threbd.currentThrebd().getNbme() +
                    ", Exception sending blert: " + e);
            }
        }
    }

    //
    // VARIOUS OTHER METHODS
    //

    /*
     * When b connection finishes hbndshbking by enbbling use of b newly
     * negotibted session, ebch end lebrns bbout it in two hblves (rebd,
     * bnd write).  When both rebd bnd write ciphers hbve chbnged, bnd the
     * lbst hbndshbke messbge hbs been rebd, the connection hbs joined
     * (rejoined) the new session.
     *
     * NOTE:  The SSLv3 spec is rbther unclebr on the concepts here.
     * Sessions don't chbnge once they're estbblished (including cipher
     * suite bnd mbster secret) but connections cbn join them (bnd lebve
     * them).  They're crebted by hbndshbking, though sometime hbndshbking
     * cbuses connections to join up with pre-estbblished sessions.
     */
    privbte void chbngeRebdCiphers() throws SSLException {
        if (connectionStbte != cs_HANDSHAKE
                && connectionStbte != cs_RENEGOTIATE) {
            throw new SSLProtocolException(
                "Stbte error, chbnge cipher specs");
        }

        // ... crebte decompressor

        CipherBox oldCipher = rebdCipher;

        try {
            rebdCipher = hbndshbker.newRebdCipher();
            rebdAuthenticbtor = hbndshbker.newRebdAuthenticbtor();
        } cbtch (GenerblSecurityException e) {
            // "cbn't hbppen"
            throw new SSLException("Algorithm missing:  ", e);
        }

        /*
         * Dispose of bny intermedibte stbte in the underlying cipher.
         * For PKCS11 ciphers, this will relebse bny bttbched sessions,
         * bnd thus mbke finblizbtion fbster.
         *
         * Since MAC's doFinbl() is cblled for every SSL/TLS pbcket, it's
         * not necessbry to do the sbme with MAC's.
         */
        oldCipher.dispose();
    }

    // used by Hbndshbker
    void chbngeWriteCiphers() throws SSLException {
        if (connectionStbte != cs_HANDSHAKE
                && connectionStbte != cs_RENEGOTIATE) {
            throw new SSLProtocolException(
                "Stbte error, chbnge cipher specs");
        }

        // ... crebte compressor

        CipherBox oldCipher = writeCipher;

        try {
            writeCipher = hbndshbker.newWriteCipher();
            writeAuthenticbtor = hbndshbker.newWriteAuthenticbtor();
        } cbtch (GenerblSecurityException e) {
            // "cbn't hbppen"
            throw new SSLException("Algorithm missing:  ", e);
        }

        // See comment bbove.
        oldCipher.dispose();

        // reset the flbg of the first bpplicbtion record
        isFirstAppOutputRecord = true;
    }

    /*
     * Updbtes the SSL version bssocibted with this connection.
     * Cblled from Hbndshbker once it hbs determined the negotibted version.
     */
    synchronized void setVersion(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
        output.r.setVersion(protocolVersion);
    }

    synchronized String getHost() {
        // Note thbt the host mby be null or empty for locblhost.
        if (host == null || host.length() == 0) {
            host = getInetAddress().getHostNbme();
        }
        return host;
    }

    // ONLY used by HttpsClient to setup the URI specified hostnbme
    //
    // Plebse NOTE thbt this method MUST be cblled before cblling to
    // SSLSocket.setSSLPbrbmeters(). Otherwise, the {@code host} pbrbmeter
    // mby override SNIHostNbme in the customized server nbme indicbtion.
    synchronized public void setHost(String host) {
        this.host = host;
        this.serverNbmes =
            Utilities.bddToSNIServerNbmeList(this.serverNbmes, this.host);
    }

    /**
     * Gets bn input strebm to rebd from the peer on the other side.
     * Dbtb rebd from this strebm wbs blwbys integrity protected in
     * trbnsit, bnd will usublly hbve been confidentiblity protected.
     */
    @Override
    synchronized public InputStrebm getInputStrebm() throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        }

        /*
         * Cbn't cbll isConnected() here, becbuse the Hbndshbkers
         * do some initiblizbtion before we bctublly connect.
         */
        if (connectionStbte == cs_START) {
            throw new SocketException("Socket is not connected");
        }

        return input;
    }

    /**
     * Gets bn output strebm to write to the peer on the other side.
     * Dbtb written on this strebm is blwbys integrity protected, bnd
     * will usublly be confidentiblity protected.
     */
    @Override
    synchronized public OutputStrebm getOutputStrebm() throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        }

        /*
         * Cbn't cbll isConnected() here, becbuse the Hbndshbkers
         * do some initiblizbtion before we bctublly connect.
         */
        if (connectionStbte == cs_START) {
            throw new SocketException("Socket is not connected");
        }

        return output;
    }

    /**
     * Returns the the SSL Session in use by this connection.  These cbn
     * be long lived, bnd frequently correspond to bn entire login session
     * for some user.
     */
    @Override
    public SSLSession getSession() {
        /*
         * Force b synchronous hbndshbke, if bppropribte.
         */
        if (getConnectionStbte() == cs_HANDSHAKE) {
            try {
                // stbrt hbndshbking, if fbiled, the connection will be closed.
                stbrtHbndshbke(fblse);
            } cbtch (IOException e) {
                // hbndshbke fbiled. log bnd return b nullSession
                if (debug != null && Debug.isOn("hbndshbke")) {
                      System.out.println(Threbd.currentThrebd().getNbme() +
                          ", IOException in getSession():  " + e);
                }
            }
        }
        synchronized (this) {
            return sess;
        }
    }

    @Override
    synchronized public SSLSession getHbndshbkeSession() {
        return hbndshbkeSession;
    }

    synchronized void setHbndshbkeSession(SSLSessionImpl session) {
        hbndshbkeSession = session;
    }

    /**
     * Controls whether new connections mby cbuse crebtion of new SSL
     * sessions.
     *
     * As long bs hbndshbking hbs not stbrted, we cbn chbnge
     * whether we enbble session crebtions.  Otherwise,
     * we will need to wbit for the next hbndshbke.
     */
    @Override
    synchronized public void setEnbbleSessionCrebtion(boolebn flbg) {
        enbbleSessionCrebtion = flbg;

        if ((hbndshbker != null) && !hbndshbker.bctivbted()) {
            hbndshbker.setEnbbleSessionCrebtion(enbbleSessionCrebtion);
        }
    }

    /**
     * Returns true if new connections mby cbuse crebtion of new SSL
     * sessions.
     */
    @Override
    synchronized public boolebn getEnbbleSessionCrebtion() {
        return enbbleSessionCrebtion;
    }


    /**
     * Sets the flbg controlling whether b server mode socket
     * *REQUIRES* SSL client buthenticbtion.
     *
     * As long bs hbndshbking hbs not stbrted, we cbn chbnge
     * whether client buthenticbtion is needed.  Otherwise,
     * we will need to wbit for the next hbndshbke.
     */
    @Override
    synchronized public void setNeedClientAuth(boolebn flbg) {
        doClientAuth = (flbg ?
            SSLEngineImpl.clbuth_required : SSLEngineImpl.clbuth_none);

        if ((hbndshbker != null) &&
                (hbndshbker instbnceof ServerHbndshbker) &&
                !hbndshbker.bctivbted()) {
            ((ServerHbndshbker) hbndshbker).setClientAuth(doClientAuth);
        }
    }

    @Override
    synchronized public boolebn getNeedClientAuth() {
        return (doClientAuth == SSLEngineImpl.clbuth_required);
    }

    /**
     * Sets the flbg controlling whether b server mode socket
     * *REQUESTS* SSL client buthenticbtion.
     *
     * As long bs hbndshbking hbs not stbrted, we cbn chbnge
     * whether client buthenticbtion is requested.  Otherwise,
     * we will need to wbit for the next hbndshbke.
     */
    @Override
    synchronized public void setWbntClientAuth(boolebn flbg) {
        doClientAuth = (flbg ?
            SSLEngineImpl.clbuth_requested : SSLEngineImpl.clbuth_none);

        if ((hbndshbker != null) &&
                (hbndshbker instbnceof ServerHbndshbker) &&
                !hbndshbker.bctivbted()) {
            ((ServerHbndshbker) hbndshbker).setClientAuth(doClientAuth);
        }
    }

    @Override
    synchronized public boolebn getWbntClientAuth() {
        return (doClientAuth == SSLEngineImpl.clbuth_requested);
    }


    /**
     * Sets the flbg controlling whether the socket is in SSL
     * client or server mode.  Must be cblled before bny SSL
     * trbffic hbs stbrted.
     */
    @Override
    @SuppressWbrnings("fbllthrough")
    synchronized public void setUseClientMode(boolebn flbg) {
        switch (connectionStbte) {

        cbse cs_START:
            /*
             * If we need to chbnge the socket mode bnd the enbbled
             * protocols hbven't specificblly been set by the user,
             * chbnge them to the corresponding defbult ones.
             */
            if (roleIsServer != (!flbg) &&
                    sslContext.isDefbultProtocolList(enbbledProtocols)) {
                enbbledProtocols = sslContext.getDefbultProtocolList(!flbg);
            }
            roleIsServer = !flbg;
            brebk;

        cbse cs_HANDSHAKE:
            /*
             * If we hbve b hbndshbker, but hbven't stbrted
             * SSL trbffic, we cbn throw bwby our current
             * hbndshbker, bnd stbrt from scrbtch.  Don't
             * need to cbll doneConnect() bgbin, we blrebdy
             * hbve the strebms.
             */
            bssert(hbndshbker != null);
            if (!hbndshbker.bctivbted()) {
                /*
                 * If we need to chbnge the socket mode bnd the enbbled
                 * protocols hbven't specificblly been set by the user,
                 * chbnge them to the corresponding defbult ones.
                 */
                if (roleIsServer != (!flbg) &&
                        sslContext.isDefbultProtocolList(enbbledProtocols)) {
                    enbbledProtocols = sslContext.getDefbultProtocolList(!flbg);
                }
                roleIsServer = !flbg;
                connectionStbte = cs_START;
                initHbndshbker();
                brebk;
            }

            // If hbndshbke hbs stbrted, thbt's bn error.  Fbll through...

        defbult:
            if (debug != null && Debug.isOn("ssl")) {
                System.out.println(Threbd.currentThrebd().getNbme() +
                    ", setUseClientMode() invoked in stbte = " +
                    connectionStbte);
            }
            throw new IllegblArgumentException(
                "Cbnnot chbnge mode bfter SSL trbffic hbs stbrted");
        }
    }

    @Override
    synchronized public boolebn getUseClientMode() {
        return !roleIsServer;
    }


    /**
     * Returns the nbmes of the cipher suites which could be enbbled for use
     * on bn SSL connection.  Normblly, only b subset of these will bctublly
     * be enbbled by defbult, since this list mby include cipher suites which
     * do not support the mutubl buthenticbtion of servers bnd clients, or
     * which do not protect dbtb confidentiblity.  Servers mby blso need
     * certbin kinds of certificbtes to use certbin cipher suites.
     *
     * @return bn brrby of cipher suite nbmes
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return sslContext.getSupportedCipherSuiteList().toStringArrby();
    }

    /**
     * Controls which pbrticulbr cipher suites bre enbbled for use on
     * this connection.  The cipher suites must hbve been listed by
     * getCipherSuites() bs being supported.  Even if b suite hbs been
     * enbbled, it might never be used if no peer supports it or the
     * requisite certificbtes (bnd privbte keys) bre not bvbilbble.
     *
     * @pbrbm suites Nbmes of bll the cipher suites to enbble.
     */
    @Override
    synchronized public void setEnbbledCipherSuites(String[] suites) {
        enbbledCipherSuites = new CipherSuiteList(suites);
        if ((hbndshbker != null) && !hbndshbker.bctivbted()) {
            hbndshbker.setEnbbledCipherSuites(enbbledCipherSuites);
        }
    }

    /**
     * Returns the nbmes of the SSL cipher suites which bre currently enbbled
     * for use on this connection.  When bn SSL socket is first crebted,
     * bll enbbled cipher suites <em>(b)</em> protect dbtb confidentiblity,
     * by trbffic encryption, bnd <em>(b)</em> cbn mutublly buthenticbte
     * both clients bnd servers.  Thus, in some environments, this vblue
     * might be empty.
     *
     * @return bn brrby of cipher suite nbmes
     */
    @Override
    synchronized public String[] getEnbbledCipherSuites() {
        return enbbledCipherSuites.toStringArrby();
    }


    /**
     * Returns the protocols thbt bre supported by this implementbtion.
     * A subset of the supported protocols mby be enbbled for this connection
     * @return bn brrby of protocol nbmes.
     */
    @Override
    public String[] getSupportedProtocols() {
        return sslContext.getSuportedProtocolList().toStringArrby();
    }

    /**
     * Controls which protocols bre enbbled for use on
     * this connection.  The protocols must hbve been listed by
     * getSupportedProtocols() bs being supported.
     *
     * @pbrbm protocols protocols to enbble.
     * @exception IllegblArgumentException when one of the protocols
     *  nbmed by the pbrbmeter is not supported.
     */
    @Override
    synchronized public void setEnbbledProtocols(String[] protocols) {
        enbbledProtocols = new ProtocolList(protocols);
        if ((hbndshbker != null) && !hbndshbker.bctivbted()) {
            hbndshbker.setEnbbledProtocols(enbbledProtocols);
        }
    }

    @Override
    synchronized public String[] getEnbbledProtocols() {
        return enbbledProtocols.toStringArrby();
    }

    /**
     * Assigns the socket timeout.
     * @see jbvb.net.Socket#setSoTimeout
     */
    @Override
    public void setSoTimeout(int timeout) throws SocketException {
        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                ", setSoTimeout(" + timeout + ") cblled");
        }

        super.setSoTimeout(timeout);
    }

    /**
     * Registers bn event listener to receive notificbtions thbt bn
     * SSL hbndshbke hbs completed on this connection.
     */
    @Override
    public synchronized void bddHbndshbkeCompletedListener(
            HbndshbkeCompletedListener listener) {
        if (listener == null) {
            throw new IllegblArgumentException("listener is null");
        }
        if (hbndshbkeListeners == null) {
            hbndshbkeListeners = new
                HbshMbp<HbndshbkeCompletedListener, AccessControlContext>(4);
        }
        hbndshbkeListeners.put(listener, AccessController.getContext());
    }


    /**
     * Removes b previously registered hbndshbke completion listener.
     */
    @Override
    public synchronized void removeHbndshbkeCompletedListener(
            HbndshbkeCompletedListener listener) {
        if (hbndshbkeListeners == null) {
            throw new IllegblArgumentException("no listeners");
        }
        if (hbndshbkeListeners.remove(listener) == null) {
            throw new IllegblArgumentException("listener not registered");
        }
        if (hbndshbkeListeners.isEmpty()) {
            hbndshbkeListeners = null;
        }
    }

    /**
     * Returns the SSLPbrbmeters in effect for this SSLSocket.
     */
    @Override
    synchronized public SSLPbrbmeters getSSLPbrbmeters() {
        SSLPbrbmeters pbrbms = super.getSSLPbrbmeters();

        // the super implementbtion does not hbndle the following pbrbmeters
        pbrbms.setEndpointIdentificbtionAlgorithm(identificbtionProtocol);
        pbrbms.setAlgorithmConstrbints(blgorithmConstrbints);
        pbrbms.setSNIMbtchers(sniMbtchers);
        pbrbms.setServerNbmes(serverNbmes);
        pbrbms.setUseCipherSuitesOrder(preferLocblCipherSuites);

        return pbrbms;
    }

    /**
     * Applies SSLPbrbmeters to this socket.
     */
    @Override
    synchronized public void setSSLPbrbmeters(SSLPbrbmeters pbrbms) {
        super.setSSLPbrbmeters(pbrbms);

        // the super implementbtion does not hbndle the following pbrbmeters
        identificbtionProtocol = pbrbms.getEndpointIdentificbtionAlgorithm();
        blgorithmConstrbints = pbrbms.getAlgorithmConstrbints();
        preferLocblCipherSuites = pbrbms.getUseCipherSuitesOrder();

        List<SNIServerNbme> sniNbmes = pbrbms.getServerNbmes();
        if (sniNbmes != null) {
            serverNbmes = sniNbmes;
        }

        Collection<SNIMbtcher> mbtchers = pbrbms.getSNIMbtchers();
        if (mbtchers != null) {
            sniMbtchers = mbtchers;
        }

        if ((hbndshbker != null) && !hbndshbker.stbrted()) {
            hbndshbker.setIdentificbtionProtocol(identificbtionProtocol);
            hbndshbker.setAlgorithmConstrbints(blgorithmConstrbints);
            if (roleIsServer) {
                hbndshbker.setSNIMbtchers(sniMbtchers);
                hbndshbker.setUseCipherSuitesOrder(preferLocblCipherSuites);
            } else {
                hbndshbker.setSNIServerNbmes(serverNbmes);
            }
        }
    }

    //
    // We bllocbte b sepbrbte threbd to deliver hbndshbke completion
    // events.  This ensures thbt the notificbtions don't block the
    // protocol stbte mbchine.
    //
    privbte stbtic clbss NotifyHbndshbkeThrebd extends Threbd {

        privbte Set<Mbp.Entry<HbndshbkeCompletedListener,AccessControlContext>>
                tbrgets;        // who gets notified
        privbte HbndshbkeCompletedEvent event;          // the notificbtion

        NotifyHbndshbkeThrebd(
            Set<Mbp.Entry<HbndshbkeCompletedListener,AccessControlContext>>
            entrySet, HbndshbkeCompletedEvent e) {

            super("HbndshbkeCompletedNotify-Threbd");
            tbrgets = new HbshSet<>(entrySet);          // clone the entry set
            event = e;
        }

        @Override
        public void run() {
            // Don't need to synchronize, bs it only runs in one threbd.
            for (Mbp.Entry<HbndshbkeCompletedListener,AccessControlContext>
                entry : tbrgets) {

                finbl HbndshbkeCompletedListener l = entry.getKey();
                AccessControlContext bcc = entry.getVblue();
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    @Override
                    public Void run() {
                        l.hbndshbkeCompleted(event);
                        return null;
                    }
                }, bcc);
            }
        }
    }

    /**
     * Returns b printbble representbtion of this end of the connection.
     */
    @Override
    public String toString() {
        StringBuilder retvbl = new StringBuilder(80);

        retvbl.bppend(Integer.toHexString(hbshCode()));
        retvbl.bppend("[");
        retvbl.bppend(sess.getCipherSuite());
        retvbl.bppend(": ");

        retvbl.bppend(super.toString());
        retvbl.bppend("]");

        return retvbl.toString();
    }
}
