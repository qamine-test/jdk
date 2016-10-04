/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.nio.*;
import jbvb.util.*;
import jbvb.security.*;

import jbvbx.crypto.BbdPbddingException;

import jbvbx.net.ssl.*;
import jbvbx.net.ssl.SSLEngineResult.*;

/**
 * Implementbtion of bn non-blocking SSLEngine.
 *
 * *Currently*, the SSLEngine code exists in pbrbllel with the current
 * SSLSocket.  As such, the current implementbtion is using legbcy code
 * with mbny of the sbme bbstrbctions.  However, it vbries in mbny
 * brebs, most drbmbticblly in the IO hbndling.
 *
 * There bre three mbin I/O threbds thbt cbn be existing in pbrbllel:
 * wrbp(), unwrbp(), bnd beginHbndshbke().  We bre encourbging users to
 * not cbll multiple instbnces of wrbp or unwrbp, becbuse the dbtb could
 * bppebr to flow out of the SSLEngine in b non-sequentibl order.  We
 * tbke bll steps we cbn to bt lebst mbke sure the ordering rembins
 * consistent, but once the cblls returns, bnything cbn hbppen.  For
 * exbmple, threbd1 bnd threbd2 both cbll wrbp, threbd1 gets the first
 * pbcket, threbd2 gets the second pbcket, but threbd2 gets control bbck
 * before threbd1, bnd sends the dbtb.  The receiving side would see bn
 * out-of-order error.
 *
 * Hbndshbking is still done the sbme wby bs SSLSocket using the normbl
 * InputStrebm/OutputStrebm bbstbctions.  We crebte
 * ClientHbndshbkers/ServerHbndshbkers, which produce/consume the
 * hbndshbking dbtb.  The trbnsfer of the dbtb is lbrgely hbndled by the
 * HbndshbkeInStrebm/HbndshbkeOutStrebms.  Lbstly, the
 * InputRecord/OutputRecords still hbve the sbme functionblity, except
 * thbt they bre overridden with EngineInputRecord/EngineOutputRecord,
 * which provide SSLEngine-specific functionblity.
 *
 * Some of the mbjor differences bre:
 *
 * EngineInputRecord/EngineOutputRecord/EngineWriter:
 *
 *      In order to bvoid writing whole new control flows for
 *      hbndshbking, bnd to reuse most of the sbme code, we kept most
 *      of the bctubl hbndshbke code the sbme.  As usubl, rebding
 *      hbndshbke dbtb mby trigger output of more hbndshbke dbtb, so
 *      whbt we do is write this dbtb to internbl buffers, bnd wbit for
 *      wrbp() to be cblled to give thbt dbtb b ride.
 *
 *      All dbtb is routed through
 *      EngineInputRecord/EngineOutputRecord.  However, bll hbndshbke
 *      dbtb (ct_blert/ct_chbnge_cipher_spec/ct_hbndshbke) bre pbssed
 *      through to the the underlying InputRecord/OutputRecord, bnd
 *      the dbtb uses the internbl buffers.
 *
 *      Applicbtion dbtb is hbndled slightly different, we copy the dbtb
 *      directly from the src to the dst buffers, bnd do bll operbtions
 *      on those buffers, sbving the overhebd of multiple copies.
 *
 *      In the cbse of bn inbound record, unwrbp pbsses the inbound
 *      ByteBuffer to the InputRecord.  If the dbtb is hbndshbke dbtb,
 *      the dbtb is rebd into the InputRecord's internbl buffer.  If
 *      the dbtb is bpplicbtion dbtb, the dbtb is decoded directly into
 *      the dst buffer.
 *
 *      In the cbse of bn outbound record, when the write to the
 *      "rebl" OutputStrebm's would normblly tbke plbce, instebd we
 *      cbll bbck up to the EngineOutputRecord's version of
 *      writeBuffer, bt which time we cbpture the resulting output in b
 *      ByteBuffer, bnd send thbt bbck to the EngineWriter for internbl
 *      storbge.
 *
 *      EngineWriter is responsible for "hbndling" bll outbound
 *      dbtb, be it hbndshbke or bpp dbtb, bnd for returning the dbtb
 *      to wrbp() in the proper order.
 *
 * ClientHbndshbker/ServerHbndshbker/Hbndshbker:
 *      Methods which relied on SSLSocket now hbve work on either
 *      SSLSockets or SSLEngines.
 *
 * @buthor Brbd Wetmore
 */
finbl public clbss SSLEngineImpl extends SSLEngine {

    //
    // Fields bnd globbl comments
    //

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
     * - CLOSED when one side closes down, used to stbrt the shutdown
     *          process.  SSL connection objects bre not reused.
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
     *     <-----<    ^            ^  <-----<               |
     *START>----->HANDSHAKE>----->DATA>----->RENEGOTIATE    |
     *                v            v               v        |
     *                |            |               |        |
     *                +------------+---------------+        |
     *                |                                     |
     *                v                                     |
     *               ERROR>------>----->CLOSED<--------<----+
     *
     * ALSO, note thbt the the purpose of hbndshbking (renegotibtion is
     * included) is to bssign b different, bnd perhbps new, session to
     * the connection.  The SSLv3 spec is b bit confusing on thbt new
     * protocol febture.
     */
    privbte int                 connectionStbte;

    privbte stbtic finbl int    cs_START = 0;
    privbte stbtic finbl int    cs_HANDSHAKE = 1;
    privbte stbtic finbl int    cs_DATA = 2;
    privbte stbtic finbl int    cs_RENEGOTIATE = 3;
    privbte stbtic finbl int    cs_ERROR = 4;
    privbte stbtic finbl int    cs_CLOSED = 6;

    /*
     * Once we're in stbte cs_CLOSED, we cbn continue to
     * wrbp/unwrbp until we finish sending/receiving the messbges
     * for close_notify.  EngineWriter hbndles outboundDone.
     */
    privbte boolebn             inboundDone = fblse;

    EngineWriter                writer;

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
     * Client buthenticbtion be off, requested, or required.
     *
     * This will be used by both this clbss bnd SSLSocket's vbribnts.
     */
    stbtic finbl byte           clbuth_none = 0;
    stbtic finbl byte           clbuth_requested = 1;
    stbtic finbl byte           clbuth_required = 2;

    /*
     * Flbg indicbting if the next record we receive MUST be b Finished
     * messbge. Temporbrily set during the hbndshbke to ensure thbt
     * b chbnge cipher spec messbge is followed by b finished messbge.
     */
    privbte boolebn             expectingFinished;


    /*
     * If someone tries to closeInbound() (sby bt End-Of-Strebm)
     * our engine hbving received b close_notify, we need to
     * notify the bpp thbt we mby hbve b truncbtion bttbck underwby.
     */
    privbte boolebn             recvCN;

    /*
     * For improved dibgnostics, we detbil connection closure
     * If the engine is closed (connectionStbte >= cs_ERROR),
     * closeRebson != null indicbtes if the engine wbs closed
     * becbuse of bn error or becbuse or normbl shutdown.
     */
    privbte SSLException        closeRebson;

    /*
     * Per-connection privbte stbte thbt doesn't chbnge when the
     * session is chbnged.
     */
    privbte byte                        doClientAuth;
    privbte boolebn                     enbbleSessionCrebtion = true;
    EngineInputRecord                   inputRecord;
    EngineOutputRecord                  outputRecord;
    privbte AccessControlContext        bcc;

    // The cipher suites enbbled for use on this connection.
    privbte CipherSuiteList             enbbledCipherSuites;

    // the endpoint identificbtion protocol
    privbte String                      identificbtionProtocol = null;

    // The cryptogrbphic blgorithm constrbints
    privbte AlgorithmConstrbints        blgorithmConstrbints = null;

    // The server nbme indicbtion bnd mbtchers
    List<SNIServerNbme>         serverNbmes =
                                    Collections.<SNIServerNbme>emptyList();
    Collection<SNIMbtcher>      sniMbtchers =
                                    Collections.<SNIMbtcher>emptyList();

    // Hbve we been told whether we're client or server?
    privbte boolebn                     serverModeSet = fblse;
    privbte boolebn                     roleIsServer;

    /*
     * The protocol versions enbbled for use on this connection.
     *
     * Note: we support b pseudo protocol cblled SSLv2Hello which when
     * set will result in bn SSL v2 Hello being sent with SSL (version 3.0)
     * or TLS (version 3.1, 3.2, etc.) version info.
     */
    privbte ProtocolList        enbbledProtocols;

    /*
     * The SSL version bssocibted with this connection.
     */
    privbte ProtocolVersion     protocolVersion = ProtocolVersion.DEFAULT;

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
     * Note thbt we must never bcquire the <code>this</code> lock bfter
     * <code>writeLock</code> or run the risk of debdlock.
     *
     * Grbb some coffee, bnd be cbreful with bny code chbnges.
     */
    privbte Object              wrbpLock;
    privbte Object              unwrbpLock;
    Object                      writeLock;

    /*
     * Is it the first bpplicbtion record to write?
     */
    privbte boolebn isFirstAppOutputRecord = true;

    /*
     * Whether locbl cipher suites preference in server side should be
     * honored during hbndshbking?
     */
    privbte boolebn preferLocblCipherSuites = fblse;

    /*
     * Clbss bnd subclbss dynbmic debugging support
     */
    privbte stbtic finbl Debug debug = Debug.getInstbnce("ssl");

    //
    // Initiblizbtion/Constructors
    //

    /**
     * Constructor for bn SSLEngine from SSLContext, without
     * host/port hints.  This Engine will not be bble to cbche
     * sessions, but must renegotibte everything by hbnd.
     */
    SSLEngineImpl(SSLContextImpl ctx) {
        super();
        init(ctx);
    }

    /**
     * Constructor for bn SSLEngine from SSLContext.
     */
    SSLEngineImpl(SSLContextImpl ctx, String host, int port) {
        super(host, port);
        init(ctx);
    }

    /**
     * Initiblizes the Engine
     */
    privbte void init(SSLContextImpl ctx) {
        if (debug != null && Debug.isOn("ssl")) {
            System.out.println("Using SSLEngineImpl.");
        }

        sslContext = ctx;
        sess = SSLSessionImpl.nullSession;
        hbndshbkeSession = null;

        /*
         * Stbte is cs_START until we initiblize the hbndshbker.
         *
         * Apps using SSLEngine bre probbbly going to be server.
         * Somewhbt brbitrbry choice.
         */
        roleIsServer = true;
        connectionStbte = cs_START;

        // defbult server nbme indicbtion
        serverNbmes =
            Utilities.bddToSNIServerNbmeList(serverNbmes, getPeerHost());

        /*
         * defbult rebd bnd write side cipher bnd MAC support
         *
         * Note:  compression support would go here too
         */
        rebdCipher = CipherBox.NULL;
        rebdAuthenticbtor = MAC.NULL;
        writeCipher = CipherBox.NULL;
        writeAuthenticbtor = MAC.NULL;

        // defbult security pbrbmeters for secure renegotibtion
        secureRenegotibtion = fblse;
        clientVerifyDbtb = new byte[0];
        serverVerifyDbtb = new byte[0];

        enbbledCipherSuites =
                sslContext.getDefbultCipherSuiteList(roleIsServer);
        enbbledProtocols =
                sslContext.getDefbultProtocolList(roleIsServer);

        wrbpLock = new Object();
        unwrbpLock = new Object();
        writeLock = new Object();

        /*
         * Sbve the Access Control Context.  This will be used lbter
         * for b couple of things, including providing b context to
         * run tbsks in, bnd for determining which credentibls
         * to use for Subject bbsed (JAAS) decisions
         */
        bcc = AccessController.getContext();

        /*
         * All outbound bpplicbtion dbtb goes through this OutputRecord,
         * other dbtb goes through their respective records crebted
         * elsewhere.  All inbound dbtb goes through this one
         * input record.
         */
        outputRecord =
            new EngineOutputRecord(Record.ct_bpplicbtion_dbtb, this);
        inputRecord = new EngineInputRecord(this);
        inputRecord.enbbleFormbtChecks();

        writer = new EngineWriter();
    }

    /**
     * Initiblize the hbndshbker object. This mebns:
     *
     *  . if b hbndshbke is blrebdy in progress (stbte is cs_HANDSHAKE
     *    or cs_RENEGOTIATE), do nothing bnd return
     *
     *  . if the engine is blrebdy closed, throw bn Exception (internbl error)
     *
     *  . otherwise (cs_START or cs_DATA), crebte the bppropribte hbndshbker
     *    object bnd bdvbnce the connection stbte (to cs_HANDSHAKE or
     *    cs_RENEGOTIATE, respectively).
     *
     * This method is cblled right bfter b new engine is crebted, when
     * stbrting renegotibtion, or when chbnging client/server mode of the
     * engine.
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

    /*
     * Report the current stbtus of the Hbndshbker
     */
    privbte HbndshbkeStbtus getHSStbtus(HbndshbkeStbtus hss) {

        if (hss != null) {
            return hss;
        }

        synchronized (this) {
            if (writer.hbsOutboundDbtb()) {
                return HbndshbkeStbtus.NEED_WRAP;
            } else if (hbndshbker != null) {
                if (hbndshbker.tbskOutstbnding()) {
                    return HbndshbkeStbtus.NEED_TASK;
                } else {
                    return HbndshbkeStbtus.NEED_UNWRAP;
                }
            } else if (connectionStbte == cs_CLOSED) {
                /*
                 * Specibl cbse where we're closing, but
                 * still need the close_notify before we
                 * cbn officiblly be closed.
                 *
                 * Note isOutboundDone is tbken cbre of by
                 * hbsOutboundDbtb() bbove.
                 */
                if (!isInboundDone()) {
                    return HbndshbkeStbtus.NEED_UNWRAP;
                } // else not hbndshbking
            }

            return HbndshbkeStbtus.NOT_HANDSHAKING;
        }
    }

    synchronized privbte void checkTbskThrown() throws SSLException {
        if (hbndshbker != null) {
            hbndshbker.checkThrown();
        }
    }

    //
    // Hbndshbking bnd connection stbte code
    //

    /*
     * Provides "this" synchronizbtion for connection stbte.
     * Otherwise, you cbn bccess it directly.
     */
    synchronized privbte int getConnectionStbte() {
        return connectionStbte;
    }

    synchronized privbte void setConnectionStbte(int stbte) {
        connectionStbte = stbte;
    }

    /*
     * Get the Access Control Context.
     *
     * Used for b known context to
     * run tbsks in, bnd for determining which credentibls
     * to use for Subject-bbsed (JAAS) decisions.
     */
    AccessControlContext getAcc() {
        return bcc;
    }

    /*
     * Is b hbndshbke currently underwby?
     */
    @Override
    public SSLEngineResult.HbndshbkeStbtus getHbndshbkeStbtus() {
        return getHSStbtus(null);
    }

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
     *
     * Synchronized on "this" from rebdRecord.
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

    /*
     * used by Hbndshbker to chbnge the bctive write cipher, follows
     * the output of the CCS messbge.
     *
     * Also synchronized on "this" from rebdRecord/delegbtedTbsk.
     */
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
        outputRecord.setVersion(protocolVersion);
    }


    /**
     * Kickstbrt the hbndshbke if it is not blrebdy in progress.
     * This mebns:
     *
     *  . if hbndshbking is blrebdy underwby, do nothing bnd return
     *
     *  . if the engine is not connected or blrebdy closed, throw bn
     *    Exception.
     *
     *  . otherwise, cbll initHbndshbke() to initiblize the hbndshbker
     *    object bnd progress the stbte. Then, send the initibl
     *    hbndshbking messbge if bppropribte (blwbys on clients bnd
     *    on servers when renegotibting).
     */
    privbte synchronized void kickstbrtHbndshbke() throws IOException {
        switch (connectionStbte) {

        cbse cs_START:
            if (!serverModeSet) {
                throw new IllegblStbteException(
                    "Client/Server mode not yet set.");
            }
            initHbndshbker();
            brebk;

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

        defbult:
            // cs_ERROR/cs_CLOSED
            throw new SSLException("SSLEngine is closing/closed");
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
            } else {    // instbnceof ServerHbndshbker
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

    /*
     * Stbrt b SSLEngine hbndshbke
     */
    @Override
    public void beginHbndshbke() throws SSLException {
        try {
            kickstbrtHbndshbke();
        } cbtch (Exception e) {
            fbtbl(Alerts.blert_hbndshbke_fbilure,
                "Couldn't kickstbrt hbndshbking", e);
        }
    }


    //
    // Rebd/unwrbp side
    //


    /**
     * Unwrbps b buffer.  Does b vbriety of checks before grbbbing
     * the unwrbpLock, which blocks multiple unwrbps from occurring.
     */
    @Override
    public SSLEngineResult unwrbp(ByteBuffer netDbtb, ByteBuffer [] bppDbtb,
            int offset, int length) throws SSLException {

        EngineArgs eb = new EngineArgs(netDbtb, bppDbtb, offset, length);

        try {
            synchronized (unwrbpLock) {
                return rebdNetRecord(eb);
            }
        } cbtch (Exception e) {
            /*
             * Don't reset position so it looks like we didn't
             * consume bnything.  We did consume something, bnd it
             * got us into this situbtion, so report thbt much bbck.
             * Our dbys of consuming bre now over bnywby.
             */
            fbtbl(Alerts.blert_internbl_error,
                "problem unwrbpping net record", e);
            return null;  // mbke compiler hbppy
        } finblly {
            /*
             * Just in cbse something fbiled to reset limits properly.
             */
            eb.resetLim();
        }
    }

    /*
     * Mbkes bdditionbl checks for unwrbp, but this time more
     * specific to this pbcket bnd the current stbte of the mbchine.
     */
    privbte SSLEngineResult rebdNetRecord(EngineArgs eb) throws IOException {

        Stbtus stbtus = null;
        HbndshbkeStbtus hsStbtus = null;

        /*
         * See if the hbndshbker needs to report bbck some SSLException.
         */
        checkTbskThrown();

        /*
         * Check if we bre closing/closed.
         */
        if (isInboundDone()) {
            return new SSLEngineResult(Stbtus.CLOSED, getHSStbtus(null), 0, 0);
        }

        /*
         * If we're still in cs_HANDSHAKE, mbke sure it's been
         * stbrted.
         */
        synchronized (this) {
            if ((connectionStbte == cs_HANDSHAKE) ||
                    (connectionStbte == cs_START)) {
                kickstbrtHbndshbke();

                /*
                 * If there's still outbound dbtb to flush, we
                 * cbn return without trying to unwrbp bnything.
                 */
                hsStbtus = getHSStbtus(null);

                if (hsStbtus == HbndshbkeStbtus.NEED_WRAP) {
                    return new SSLEngineResult(Stbtus.OK, hsStbtus, 0, 0);
                }
            }
        }

        /*
         * Grbb b copy of this if it doesn't blrebdy exist,
         * bnd we cbn use it severbl plbces before bnything mbjor
         * hbppens on this side.  Rbces bren't criticbl
         * here.
         */
        if (hsStbtus == null) {
            hsStbtus = getHSStbtus(null);
        }

        /*
         * If we hbve b tbsk outstbnding, this *MUST* be done before
         * doing bny more unwrbpping, becbuse we could be in the middle
         * of receiving b hbndshbke messbge, for exbmple, b finished
         * messbge which would chbnge the ciphers.
         */
        if (hsStbtus == HbndshbkeStbtus.NEED_TASK) {
            return new SSLEngineResult(
                Stbtus.OK, hsStbtus, 0, 0);
        }

        /*
         * Check the pbcket to mbke sure enough is here.
         * This will blso indirectly check for 0 len pbckets.
         */
        int pbcketLen = inputRecord.bytesInCompletePbcket(eb.netDbtb);

        // Is this pbcket bigger thbn SSL/TLS normblly bllows?
        if (pbcketLen > sess.getPbcketBufferSize()) {
            if (pbcketLen > Record.mbxLbrgeRecordSize) {
                throw new SSLProtocolException(
                    "Input SSL/TLS record too big: mbx = " +
                    Record.mbxLbrgeRecordSize +
                    " len = " + pbcketLen);
            } else {
                // Expbnd the expected mbximum pbcket/bpplicbtion buffer
                // sizes.
                sess.expbndBufferSizes();
            }
        }

        /*
         * Check for OVERFLOW.
         *
         * To be considered: We could delby enforcing the bpplicbtion buffer
         * free spbce requirement until bfter the initibl hbndshbking.
         */
        if ((pbcketLen - Record.hebderSize) > eb.getAppRembining()) {
            return new SSLEngineResult(Stbtus.BUFFER_OVERFLOW, hsStbtus, 0, 0);
        }

        // check for UNDERFLOW.
        if ((pbcketLen == -1) || (eb.netDbtb.rembining() < pbcketLen)) {
            return new SSLEngineResult(
                Stbtus.BUFFER_UNDERFLOW, hsStbtus, 0, 0);
        }

        /*
         * We're now rebdy to bctublly do the rebd.
         * The only result code we reblly need to be exbctly
         * right is the HS finished, for signbling to
         * HbndshbkeCompletedListeners.
         */
        try {
            hsStbtus = rebdRecord(eb);
        } cbtch (SSLException e) {
            throw e;
        } cbtch (IOException e) {
            throw new SSLException("rebdRecord", e);
        }

        /*
         * Check the vbrious condition thbt we could be reporting.
         *
         * It's *possible* something might hbve hbppened between the
         * bbove bnd now, but it wbs better to minimblly lock "this"
         * during the rebd process.  We'll return the current
         * stbtus, which is more representbtive of the current stbte.
         *
         * stbtus bbove should cover:  FINISHED, NEED_TASK
         */
        stbtus = (isInboundDone() ? Stbtus.CLOSED : Stbtus.OK);
        hsStbtus = getHSStbtus(hsStbtus);

        return new SSLEngineResult(stbtus, hsStbtus,
            eb.deltbNet(), eb.deltbApp());
    }

    /*
     * Actublly do the rebd record processing.
     *
     * Returns b Stbtus if it cbn mbke specific determinbtions
     * of the engine stbte.  In pbrticulbr, we need to signbl
     * thbt b hbndshbke just completed.
     *
     * It would be nice to be symmetricbl with the write side bnd move
     * the mbjority of this to EngineInputRecord, but there's too much
     * SSLEngine stbte to do thbt clebnly.  It must still live here.
     */
    privbte HbndshbkeStbtus rebdRecord(EngineArgs eb) throws IOException {

        HbndshbkeStbtus hsStbtus = null;

        /*
         * The vbrious operbtions will return new sliced BB's,
         * this will bvoid hbving to worry bbout positions bnd
         * limits in the netBB.
         */
        ByteBuffer rebdBB = null;
        ByteBuffer decryptedBB = null;

        if (getConnectionStbte() != cs_ERROR) {

            /*
             * Rebd b record ... mbybe emitting bn blert if we get b
             * comprehensible but unsupported "hello" messbge during
             * formbt checking (e.g. V2).
             */
            try {
                rebdBB = inputRecord.rebd(eb.netDbtb);
            } cbtch (IOException e) {
                fbtbl(Alerts.blert_unexpected_messbge, e);
            }

            /*
             * The bbsic SSLv3 record protection involves (optionbl)
             * encryption for privbcy, bnd bn integrity check ensuring
             * dbtb origin buthenticbtion.  We do them both here, bnd
             * throw b fbtbl blert if the integrity check fbils.
             */
            try {
                decryptedBB = inputRecord.decrypt(
                                    rebdAuthenticbtor, rebdCipher, rebdBB);
            } cbtch (BbdPbddingException e) {
                byte blertType = (inputRecord.contentType() ==
                    Record.ct_hbndshbke) ?
                        Alerts.blert_hbndshbke_fbilure :
                        Alerts.blert_bbd_record_mbc;
                fbtbl(blertType, e.getMessbge(), e);
            }

            // if (!inputRecord.decompress(c))
            //     fbtbl(Alerts.blert_decompression_fbilure,
            //     "decompression fbilure");


            /*
             * Process the record.
             */

            synchronized (this) {
                switch (inputRecord.contentType()) {
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
                    hbndshbker.process_record(inputRecord, expectingFinished);
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
                        if (!writer.hbsOutboundDbtb()) {
                            hsStbtus = HbndshbkeStbtus.FINISHED;
                        }
                        hbndshbker = null;
                        connectionStbte = cs_DATA;

                        // No hbndshbkeListeners here.  Thbt's b
                        // SSLSocket thing.
                    } else if (hbndshbker.tbskOutstbnding()) {
                        hsStbtus = HbndshbkeStbtus.NEED_TASK;
                    }
                    brebk;

                cbse Record.ct_bpplicbtion_dbtb:
                    // Pbss this right bbck up to the bpplicbtion.
                    if ((connectionStbte != cs_DATA)
                            && (connectionStbte != cs_RENEGOTIATE)
                            && (connectionStbte != cs_CLOSED)) {
                        throw new SSLProtocolException(
                            "Dbtb received in non-dbtb stbte: " +
                            connectionStbte);
                    }

                    if (expectingFinished) {
                        throw new SSLProtocolException
                                ("Expecting finished messbge, received dbtb");
                    }

                    /*
                     * Don't return dbtb once the inbound side is
                     * closed.
                     */
                    if (!inboundDone) {
                        eb.scbtter(decryptedBB.slice());
                    }
                    brebk;

                cbse Record.ct_blert:
                    recvAlert();
                    brebk;

                cbse Record.ct_chbnge_cipher_spec:
                    if ((connectionStbte != cs_HANDSHAKE
                                && connectionStbte != cs_RENEGOTIATE)
                            || inputRecord.bvbilbble() != 1
                            || inputRecord.rebd() != 1) {
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
                    brebk;

                defbult:
                    //
                    // TLS requires thbt unrecognized records be ignored.
                    //
                    if (debug != null && Debug.isOn("ssl")) {
                        System.out.println(Threbd.currentThrebd().getNbme() +
                            ", Received record type: "
                            + inputRecord.contentType());
                    }
                    brebk;
                } // switch

                /*
                 * We only need to check the sequence number stbte for
                 * non-hbndshbking record.
                 *
                 * Note thbt in order to mbintbin the hbndshbke stbtus
                 * properly, we check the sequence number bfter the lbst
                 * record rebding process. As we request renegotibtion
                 * or close the connection for wrbpped sequence number
                 * when there is enough sequence number spbce left to
                 * hbndle b few more records, so the sequence number
                 * of the lbst record cbnnot be wrbpped.
                 */
                hsStbtus = getHSStbtus(hsStbtus);
                if (connectionStbte < cs_ERROR && !isInboundDone() &&
                        (hsStbtus == HbndshbkeStbtus.NOT_HANDSHAKING)) {
                    if (checkSequenceNumber(rebdAuthenticbtor,
                            inputRecord.contentType())) {
                        hsStbtus = getHSStbtus(null);
                    }
                }
            } // synchronized (this)
        }

        return hsStbtus;
    }


    //
    // write/wrbp side
    //


    /**
     * Wrbps b buffer.  Does b vbriety of checks before grbbbing
     * the wrbpLock, which blocks multiple wrbps from occurring.
     */
    @Override
    public SSLEngineResult wrbp(ByteBuffer [] bppDbtb,
            int offset, int length, ByteBuffer netDbtb) throws SSLException {

        EngineArgs eb = new EngineArgs(bppDbtb, offset, length, netDbtb);

        /*
         * We cbn be smbrter bbout using smbller buffer sizes lbter.
         * For now, force it to be lbrge enough to hbndle bny
         * vblid SSL/TLS record.
         */
        if (netDbtb.rembining() < EngineOutputRecord.mbxRecordSize) {
            return new SSLEngineResult(
                Stbtus.BUFFER_OVERFLOW, getHSStbtus(null), 0, 0);
        }

        try {
            synchronized (wrbpLock) {
                return writeAppRecord(eb);
            }
        } cbtch (Exception e) {
            eb.resetPos();

            fbtbl(Alerts.blert_internbl_error,
                "problem wrbpping bpp dbtb", e);
            return null;  // mbke compiler hbppy
        } finblly {
            /*
             * Just in cbse something didn't reset limits properly.
             */
            eb.resetLim();
        }
    }

    /*
     * Mbkes bdditionbl checks for unwrbp, but this time more
     * specific to this pbcket bnd the current stbte of the mbchine.
     */
    privbte SSLEngineResult writeAppRecord(EngineArgs eb) throws IOException {

        Stbtus stbtus = null;
        HbndshbkeStbtus hsStbtus = null;

        /*
         * See if the hbndshbker needs to report bbck some SSLException.
         */
        checkTbskThrown();

        /*
         * short circuit if we're closed/closing.
         */
        if (writer.isOutboundDone()) {
            return new SSLEngineResult(Stbtus.CLOSED, getHSStbtus(null), 0, 0);
        }

        /*
         * If we're still in cs_HANDSHAKE, mbke sure it's been
         * stbrted.
         */
        synchronized (this) {
            if ((connectionStbte == cs_HANDSHAKE) ||
                    (connectionStbte == cs_START)) {
                kickstbrtHbndshbke();

                /*
                 * If there's no HS dbtb bvbilbble to write, we cbn return
                 * without trying to wrbp bnything.
                 */
                hsStbtus = getHSStbtus(null);

                if (hsStbtus == HbndshbkeStbtus.NEED_UNWRAP) {
                    return new SSLEngineResult(Stbtus.OK, hsStbtus, 0, 0);
                }
            }
        }

        /*
         * Grbb b copy of this if it doesn't blrebdy exist,
         * bnd we cbn use it severbl plbces before bnything mbjor
         * hbppens on this side.  Rbces bren't criticbl
         * here.
         */
        if (hsStbtus == null) {
            hsStbtus = getHSStbtus(null);
        }

        /*
         * If we hbve b tbsk outstbnding, this *MUST* be done before
         * doing bny more wrbpping, becbuse we could be in the middle
         * of receiving b hbndshbke messbge, for exbmple, b finished
         * messbge which would chbnge the ciphers.
         */
        if (hsStbtus == HbndshbkeStbtus.NEED_TASK) {
            return new SSLEngineResult(
                Stbtus.OK, hsStbtus, 0, 0);
        }

        /*
         * This will obtbin bny wbiting outbound dbtb, or will
         * process the outbound bppDbtb.
         */
        try {
            synchronized (writeLock) {
                hsStbtus = writeRecord(outputRecord, eb);
            }
        } cbtch (SSLException e) {
            throw e;
        } cbtch (IOException e) {
            throw new SSLException("Write problems", e);
        }

        /*
         * writeRecord might hbve reported some stbtus.
         * Now check for the rembining cbses.
         *
         * stbtus bbove should cover:  NEED_WRAP/FINISHED
         */
        stbtus = (isOutboundDone() ? Stbtus.CLOSED : Stbtus.OK);
        hsStbtus = getHSStbtus(hsStbtus);

        return new SSLEngineResult(stbtus, hsStbtus,
            eb.deltbApp(), eb.deltbNet());
    }

    /*
     * Centrbl point to write/get bll of the outgoing dbtb.
     */
    privbte HbndshbkeStbtus writeRecord(EngineOutputRecord eor,
            EngineArgs eb) throws IOException {

        // eventublly compress bs well.
        HbndshbkeStbtus hsStbtus =
                writer.writeRecord(eor, eb, writeAuthenticbtor, writeCipher);

        /*
         * We only need to check the sequence number stbte for
         * non-hbndshbking record.
         *
         * Note thbt in order to mbintbin the hbndshbke stbtus
         * properly, we check the sequence number bfter the lbst
         * record writing process. As we request renegotibtion
         * or close the connection for wrbpped sequence number
         * when there is enough sequence number spbce left to
         * hbndle b few more records, so the sequence number
         * of the lbst record cbnnot be wrbpped.
         */
        hsStbtus = getHSStbtus(hsStbtus);
        if (connectionStbte < cs_ERROR && !isOutboundDone() &&
                (hsStbtus == HbndshbkeStbtus.NOT_HANDSHAKING)) {
            if (checkSequenceNumber(writeAuthenticbtor, eor.contentType())) {
                hsStbtus = getHSStbtus(null);
            }
        }

        /*
         * turn off the flbg of the first bpplicbtion record if we reblly
         * consumed bt lebst byte.
         */
        if (isFirstAppOutputRecord && eb.deltbApp() > 0) {
            isFirstAppOutputRecord = fblse;
        }

        return hsStbtus;
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
     * More detbils, plebse refer to
     * EngineOutputRecord.write(EngineArgs, MAC, CipherBox).
     */
    boolebn needToSplitPbylobd(CipherBox cipher, ProtocolVersion protocol) {
        return (protocol.v <= ProtocolVersion.TLS10.v) &&
                cipher.isCBCMode() && !isFirstAppOutputRecord &&
                Record.enbbleCBCProtection;
    }

    /*
     * Non-bpplicbtion OutputRecords go through here.
     */
    void writeRecord(EngineOutputRecord eor) throws IOException {
        // eventublly compress bs well.
        writer.writeRecord(eor, writeAuthenticbtor, writeCipher);

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
        if ((connectionStbte < cs_ERROR) && !isOutboundDone()) {
            checkSequenceNumber(writeAuthenticbtor, eor.contentType());
        }
    }

    //
    // Close code
    //

    /**
     * Check the sequence number stbte
     *
     * RFC 4346 stbtes thbt, "Sequence numbers bre of type uint64 bnd
     * mby not exceed 2^64-1.  Sequence numbers do not wrbp. If b TLS
     * implementbtion would need to wrbp b sequence number, it must
     * renegotibte instebd."
     *
     * Return true if the hbndshbke stbtus mby be chbnged.
     */
    privbte boolebn checkSequenceNumber(Authenticbtor buthenticbtor, byte type)
            throws IOException {

        /*
         * Don't bother to check the sequence number for error or
         * closed connections, or NULL MAC
         */
        if (connectionStbte >= cs_ERROR || buthenticbtor == MAC.NULL) {
            return fblse;
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

            return true; // mbke the compiler hbppy
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

            beginHbndshbke();
            return true;
        }

        return fblse;
    }

    /**
     * Signbls thbt no more outbound bpplicbtion dbtb will be sent
     * on this <code>SSLEngine</code>.
     */
    privbte void closeOutboundInternbl() {

        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                                    ", closeOutboundInternbl()");
        }

        /*
         * Alrebdy closed, ignore
         */
        if (writer.isOutboundDone()) {
            return;
        }

        switch (connectionStbte) {

        /*
         * If we hbven't even stbrted yet, don't bother rebding inbound.
         */
        cbse cs_START:
            writer.closeOutbound();
            inboundDone = true;
            brebk;

        cbse cs_ERROR:
        cbse cs_CLOSED:
            brebk;

        /*
         * Otherwise we indicbte clebn terminbtion.
         */
        // cbse cs_HANDSHAKE:
        // cbse cs_DATA:
        // cbse cs_RENEGOTIATE:
        defbult:
            wbrning(Alerts.blert_close_notify);
            writer.closeOutbound();
            brebk;
        }

        // See comment in chbngeRebdCiphers()
        writeCipher.dispose();

        connectionStbte = cs_CLOSED;
    }

    @Override
    synchronized public void closeOutbound() {
        /*
         * Dump out b close_notify to the remote side
         */
        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                                    ", cblled closeOutbound()");
        }

        closeOutboundInternbl();
    }

    /**
     * Returns the outbound bpplicbtion dbtb closure stbte
     */
    @Override
    public boolebn isOutboundDone() {
        return writer.isOutboundDone();
    }

    /**
     * Signbls thbt no more inbound network dbtb will be sent
     * to this <code>SSLEngine</code>.
     */
    privbte void closeInboundInternbl() {

        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                                    ", closeInboundInternbl()");
        }

        /*
         * Alrebdy closed, ignore
         */
        if (inboundDone) {
            return;
        }

        closeOutboundInternbl();
        inboundDone = true;

        // See comment in chbngeRebdCiphers()
        rebdCipher.dispose();

        connectionStbte = cs_CLOSED;
    }

    /*
     * Close the inbound side of the connection.  We grbb the
     * lock here, bnd do the rebl work in the internbl verison.
     * We do check for truncbtion bttbcks.
     */
    @Override
    synchronized public void closeInbound() throws SSLException {
        /*
         * Currently closes the outbound side bs well.  The IETF TLS
         * working group hbs expressed the opinion thbt 1/2 open
         * connections bre not bllowed by the spec.  Mby chbnge
         * somedby in the future.
         */
        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme() +
                                    ", cblled closeInbound()");
        }

        /*
         * No need to throw bn Exception if we hbven't even stbrted yet.
         */
        if ((connectionStbte != cs_START) && !recvCN) {
            recvCN = true;  // Only receive the Exception once
            fbtbl(Alerts.blert_internbl_error,
                "Inbound closed before receiving peer's close_notify: " +
                "possible truncbtion bttbck?");
        } else {
            /*
             * Currently, this is b no-op, but in cbse we chbnge
             * the close inbound code lbter.
             */
            closeInboundInternbl();
        }
    }

    /**
     * Returns the network inbound dbtb closure stbte
     */
    @Override
    synchronized public boolebn isInboundDone() {
        return inboundDone;
    }


    //
    // Misc stuff
    //


    /**
     * Returns the current <code>SSLSession</code> for this
     * <code>SSLEngine</code>
     * <P>
     * These cbn be long lived, bnd frequently correspond to bn
     * entire login session for some user.
     */
    @Override
    synchronized public SSLSession getSession() {
        return sess;
    }

    @Override
    synchronized public SSLSession getHbndshbkeSession() {
        return hbndshbkeSession;
    }

    synchronized void setHbndshbkeSession(SSLSessionImpl session) {
        hbndshbkeSession = session;
    }

    /**
     * Returns b delegbted <code>Runnbble</code> tbsk for
     * this <code>SSLEngine</code>.
     */
    @Override
    synchronized public Runnbble getDelegbtedTbsk() {
        if (hbndshbker != null) {
            return hbndshbker.getTbsk();
        }
        return null;
    }


    //
    // EXCEPTION AND ALERT HANDLING
    //

    /*
     * Send b wbrning blert.
     */
    void wbrning(byte description) {
        sendAlert(Alerts.blert_wbrning, description);
    }

    synchronized void fbtbl(byte description, String dibgnostic)
            throws SSLException {
        fbtbl(description, dibgnostic, null);
    }

    synchronized void fbtbl(byte description, Throwbble cbuse)
            throws SSLException {
        fbtbl(description, null, cbuse);
    }

    /*
     * We've got b fbtbl error here, so stbrt the shutdown process.
     *
     * Becbuse of the wby the code wbs written, we hbve some code
     * cblling fbtbl directly when the "description" is known
     * bnd some throwing Exceptions which bre then cbught by higher
     * levels which then cbll here.  This code needs to determine
     * if one of the lower levels hbs blrebdy stbrted the process.
     *
     * We won't worry bbout Error's, if we hbve one of those,
     * we're in worse trouble.  Note:  the networking code doesn't
     * debl with Errors either.
     */
    synchronized void fbtbl(byte description, String dibgnostic,
            Throwbble cbuse) throws SSLException {

        /*
         * If we hbve no further informbtion, mbke b generbl-purpose
         * messbge for folks to see.  We generblly hbve one or the other.
         */
        if (dibgnostic == null) {
            dibgnostic = "Generbl SSLEngine problem";
        }
        if (cbuse == null) {
            cbuse = Alerts.getSSLException(description, cbuse, dibgnostic);
        }

        /*
         * If we've blrebdy shutdown becbuse of bn error,
         * there is nothing we cbn do except rethrow the exception.
         *
         * Most exceptions seen here will be SSLExceptions.
         * We mby find the occbsionbl Exception which hbsn't been
         * converted to b SSLException, so we'll do it here.
         */
        if (closeRebson != null) {
            if ((debug != null) && Debug.isOn("ssl")) {
                System.out.println(Threbd.currentThrebd().getNbme() +
                    ", fbtbl: engine blrebdy closed.  Rethrowing " +
                    cbuse.toString());
            }
            if (cbuse instbnceof RuntimeException) {
                throw (RuntimeException)cbuse;
            } else if (cbuse instbnceof SSLException) {
                throw (SSLException)cbuse;
            } else if (cbuse instbnceof Exception) {
                throw new SSLException("fbtbl SSLEngine condition", cbuse);
            }
        }

        if ((debug != null) && Debug.isOn("ssl")) {
            System.out.println(Threbd.currentThrebd().getNbme()
                        + ", fbtbl error: " + description +
                        ": " + dibgnostic + "\n" + cbuse.toString());
        }

        /*
         * Ok, this engine's going down.
         */
        int oldStbte = connectionStbte;
        connectionStbte = cs_ERROR;

        inboundDone = true;

        sess.invblidbte();
        if (hbndshbkeSession != null) {
            hbndshbkeSession.invblidbte();
        }

        /*
         * If we hbven't even stbrted hbndshbking yet, no need
         * to generbte the fbtbl close blert.
         */
        if (oldStbte != cs_START) {
            sendAlert(Alerts.blert_fbtbl, description);
        }

        if (cbuse instbnceof SSLException) { // only true if != null
            closeRebson = (SSLException)cbuse;
        } else {
            /*
             * Including RuntimeExceptions, but we'll throw those
             * down below.  The closeRebson isn't used bgbin,
             * except for null checks.
             */
            closeRebson =
                Alerts.getSSLException(description, cbuse, dibgnostic);
        }

        writer.closeOutbound();

        connectionStbte = cs_CLOSED;

        // See comment in chbngeRebdCiphers()
        rebdCipher.dispose();
        writeCipher.dispose();

        if (cbuse instbnceof RuntimeException) {
            throw (RuntimeException)cbuse;
        } else {
            throw closeRebson;
        }
    }

    /*
     * Process bn incoming blert ... cbller must blrebdy hbve synchronized
     * bccess to "this".
     */
    privbte void recvAlert() throws IOException {
        byte level = (byte)inputRecord.rebd();
        byte description = (byte)inputRecord.rebd();
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
                    recvCN = true;
                    closeInboundInternbl();  // reply to close
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
        if (connectionStbte >= cs_CLOSED) {
            return;
        }

        // For initibl hbndshbking, don't send blert messbge to peer if
        // hbndshbker hbs not stbrted.
        if (connectionStbte == cs_HANDSHAKE &&
            (hbndshbker == null || !hbndshbker.stbrted())) {
            return;
        }

        EngineOutputRecord r = new EngineOutputRecord(Record.ct_blert, this);
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
    // VARIOUS OTHER METHODS (COMMON TO SSLSocket)
    //


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
     * Sets the flbg controlling whether b server mode engine
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
     * Sets the flbg controlling whether b server mode engine
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
     * Sets the flbg controlling whether the engine is in SSL
     * client or server mode.  Must be cblled before bny SSL
     * trbffic hbs stbrted.
     */
    @Override
    @SuppressWbrnings("fbllthrough")
    synchronized public void setUseClientMode(boolebn flbg) {
        switch (connectionStbte) {

        cbse cs_START:
            /*
             * If we need to chbnge the engine mode bnd the enbbled
             * protocols hbven't specificblly been set by the user,
             * chbnge them to the corresponding defbult ones.
             */
            if (roleIsServer != (!flbg) &&
                    sslContext.isDefbultProtocolList(enbbledProtocols)) {
                enbbledProtocols = sslContext.getDefbultProtocolList(!flbg);
            }

            roleIsServer = !flbg;
            serverModeSet = true;
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
                 * If we need to chbnge the engine mode bnd the enbbled
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

            /*
             * We cbn let them continue if they cbtch this correctly,
             * we don't need to shut this down.
             */
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
     * for use on this connection.  When bn SSL engine is first crebted,
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
     * Returns the SSLPbrbmeters in effect for this SSLEngine.
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
     * Applies SSLPbrbmeters to this engine.
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

    /**
     * Returns b printbble representbtion of this end of the connection.
     */
    @Override
    public String toString() {
        StringBuilder retvbl = new StringBuilder(80);

        retvbl.bppend(Integer.toHexString(hbshCode()));
        retvbl.bppend("[");
        retvbl.bppend("SSLEngine[hostnbme=");
        String host = getPeerHost();
        retvbl.bppend((host == null) ? "null" : host);
        retvbl.bppend(" port=");
        retvbl.bppend(Integer.toString(getPeerPort()));
        retvbl.bppend("] ");
        retvbl.bppend(getSession().getCipherSuite());
        retvbl.bppend("]");

        return retvbl.toString();
    }
}
